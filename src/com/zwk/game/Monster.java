package com.zwk.game;

import com.zwk.tool.MonsterConstant;
import com.zwk.tool.SwordManConstant;
import java.util.Map;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Monster extends Biology {
	private Random random = new Random();
	private MonsterEnum monsterEnum;
	public  boolean flagPaint = false;
	private Map<Integer, ImageIcon> map_monster_images;
	// 控制左右
	private boolean flag_Right_Left = false;

	public enum MonsterStatus {walk, attack, stop, hit, death, skillHit}

	public MonsterStatus monsterStatus = MonsterStatus.stop;
	private ImageIcon monsterImage;

	public Monster(JFrame mainJFrame, MonsterEnum monsterEnum) {
		super(mainJFrame);
		this.monsterEnum = monsterEnum;
		switch (monsterEnum) {
			case kinggoldenhorn:

				break;
			case kingsilverhorn: {
				speed = 10;
				hp = 200000;
				injuryValue = 700;
				width = 400;
				height = 150;
				map_monster_images = MonsterConstant.MONSTER_IMAGE_KINGSILVERHORN;
				break;
			}
		}
		lb_biology = new JLabel(map_monster_images.get(2));
		x = 200;
		y = 300;
		lb_biology.setBounds(x, y, width, height);
	}

	@Override
	public void paint() {
		mainJFrame.getLayeredPane().add(lb_biology);
		findPlayer();
		refreshSelf();
		flagPaint = true;
	}

	// 刷新自己的状态
	private void refreshSelf() {
		new Thread(() -> {
			while (true) {
//				if (hp < 0) {
//					lb_biology.setIcon(map_monster_images.get(8));
//					continue;
//				}
				if (monsterStatus.equals(MonsterStatus.death)) {
					lb_biology.setIcon(map_monster_images.get(8));
					continue;
				}
				if (GagaGame.houseVisible) continue;
				lb_biology.setIcon(monsterImage);
				lb_biology.setLocation(x, y);
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 判断人物是否被攻击
				if (monsterStatus.equals(MonsterStatus.attack)) {
					if (collisionDetection()) {
						if (SwordManConstant.hp > 0) {
							int number = random.nextInt(injuryValue - injuryValue / 2) + injuryValue / 2;
							SwordManConstant.hp -= number;
							GamePlayer.playerState = GamePlayer.PlayerState.hit;
							GagaGame.gamePlayer.setHitValueJText(number);
						} else {
							GamePlayer.playerState = GamePlayer.PlayerState.death;
						}
					}
				}
			}
		}).start();
	}

	// 追踪人物
	private void findPlayer() {
		new Thread(() -> {
			while (true) {
				if (monsterImage != null && monsterImage.equals(map_monster_images.get(8))) continue;
				if (GagaGame.houseVisible) continue;
				if (monsterStatus.equals(MonsterStatus.death)) {
					monsterImage = map_monster_images.get(8);
					continue;
				} else if (monsterStatus.equals(MonsterStatus.hit)) {
					monsterImage = map_monster_images.get(7);
					continue;
				} else if (monsterStatus.equals(MonsterStatus.skillHit)) {
					monsterStatus.equals(9);
				}
				if (collisionDetection()) {
					if (monsterStatus == MonsterStatus.attack) continue;
					monsterImage = flag_Right_Left ? map_monster_images.get(5) : map_monster_images.get(6);
					monsterStatus = MonsterStatus.attack;
					continue;
				} else {
					monsterImage = flag_Right_Left ? map_monster_images.get(3) : map_monster_images.get(4);
					monsterStatus = MonsterStatus.walk;
				}
				if (SwordManConstant.swordManX + 150 > x + width) {
					flag_Right_Left = true;
					x += speed;
				} else {
					flag_Right_Left = false;
					x -= speed;
				}
				if (SwordManConstant.swordManY > y) {
					y += speed;
				} else {
					y -= speed;
				}
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	// 碰撞检测
	private boolean collisionDetection() {
		int monsterX = lb_biology.getX() + width;
		int monsterY = lb_biology.getY() + height;
		boolean flag = false;
		if (monsterX > SwordManConstant.swordManX + 100 && monsterX < SwordManConstant.swordManX + SwordManConstant.SWORD_MAN_WIDTH
				&& monsterY - height + height / 2 > SwordManConstant.swordManY && monsterY < SwordManConstant.swordManY + SwordManConstant.SWORD_MAN_WIDTH) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public void setVisible(boolean flag) {
		lb_biology.setVisible(flag);
	}

	@Override
	public void setHitValueJText(int value) {
		new Thread(() -> {
			if (hitValueJText != null) {
				hitValueJText.setText("-" + value + "");
				hitValueJText.setLocation(x + 100, y);
				hitValueJText.repaint();
				hitValueJText.setVisible(true);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {

				}
				hitValueJText.setVisible(false);
			}
		}).start();

	}
}
