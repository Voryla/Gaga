package com.zwk.game;

import com.zwk.tool.GameConstant;
import com.zwk.tool.SwordManConstant;
import com.zwk.tool.WindowTool;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

public class GagaGame extends JFrame {
	public static GagaHouseMap gagaHouseMap;
	public static GagaCombatMap gagaCombatMap;
	public static SkillBar skillBar;
	public static volatile boolean houseVisible = true;
	private JLabel lb_endingGame = new JLabel(GameConstant.BT_IMAGE__ENDING_GAME1);
	public static Monster monster;
	public static GamePlayer gamePlayer;
	private static JLabel lb_monsterIcon;
	private static JProgressBar jProgressBar;

	public GagaGame() {
		monster = new Monster(this, MonsterEnum.kingsilverhorn);
		initMonsterHpBar();
		lb_endingGame.setBounds(340, 525, 100, 20);
		this.getLayeredPane().add(lb_endingGame);

		// 设置窗体显示位置，及大小
		this.setBounds((WindowTool.getScreenWidth() - GameConstant.GAME_WINDOW_WIDTH) / 2,
				(WindowTool.getScreenHeight() - GameConstant.GAME_WINDOW_HEIGHT) / 2, GameConstant.GAME_WINDOW_WIDTH,
				GameConstant.GAME_WINDOW_HEIGHT);
		// 设置光标
		WindowTool.changeCursor(this);
		// 设置关闭事件
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// 去除顶部标题栏
		//this.setUndecorated(true);
		//this.setResizable(false);
		initUI();
		// 设置显示
		this.setVisible(true);
		// 地下城
		gagaCombatMap = new GagaCombatMap(this);
		// 碰撞检测
		collisionDetection();
		// 退出按钮
		addMouseListener();

	}

	private void initMonsterHpBar() {
		// 怪物hp进度条
		lb_monsterIcon = new JLabel(GameConstant.MONSTER_IMAGE_ICON);
		lb_monsterIcon.setBounds(48, 70, 50, 60);
		jProgressBar = new JProgressBar();
		jProgressBar.setMaximum(monster.hp);
		jProgressBar.setMinimum(0);
		jProgressBar.setBackground(Color.BLACK);
		jProgressBar.setForeground(Color.red);
		jProgressBar.setSize(600, 40);
		jProgressBar.setLocation(100, 80);
		jProgressBar.setValue(monster.hp);
		jProgressBar.setOpaque(false);
		lb_monsterIcon.setVisible(false);
		jProgressBar.setVisible(false);
		this.getLayeredPane().add(jProgressBar);
		this.getLayeredPane().add(lb_monsterIcon);
		new Thread(() -> {
			while (true) {
				if (!houseVisible) {
					jProgressBar.setValue(monster.hp);
				}
			}
		}).start();
	}

	public static void setMonsterHpBarVisible(boolean flag) {
		lb_monsterIcon.setVisible(flag);
		jProgressBar.setVisible(flag);
	}

	// 初始化ui
	private void initUI() {
		gagaHouseMap = new GagaHouseMap(this);

		//因为jFrame 先添加的控件在顶层显示
		// 先添加技能板，为了使 人物不重叠在技能板上
		skillBar = new SkillBar(this);
		skillBar.paint();
		// 再添加地图 及门口装饰 让人物不重叠在其上
		gagaHouseMap.paint();
		// 放置人物
		gamePlayer = new GamePlayer(this);
		gamePlayer.paint();
		// 再防止NPC等装饰品 使人物可以重叠其上
		gagaHouseMap.printSAILIYA();
	}

	// 碰撞检测 人物进入地下城
	private void collisionDetection() {

		new Thread(() -> {
			while (true) {
				if (!houseVisible) continue;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int x = SwordManConstant.swordManX + SwordManConstant.SWORD_MAN_WIDTH / 2 + 70;
				int y = SwordManConstant.swordManY + SwordManConstant.SWORD_MAN_HEIGHT / 2 - 100;
				// 判断人物是否触碰到进入地下城的光圈
				if (x > GameConstant.MAP_HOUSE_WAVE_X && x < GameConstant.MAP_HOUSE_WAVE_X + GameConstant.MAP_HOUSE_WAVE_WIDTH
						&& y > GameConstant.MAP_HOUSE_WAVE_Y && y < GameConstant.MAP_HOUSE_WAVE_Y + GameConstant.MAP_HOUSE_WAVE_HEIGHT) {
					if (monster.monsterStatus.equals(Monster.MonsterStatus.death)) {
						monster.hp = 2000 * 5;
						monster.monsterStatus = Monster.MonsterStatus.walk;
						gagaHouseMap.setVisible(false);
						houseVisible = false;
						setMonsterHpBarVisible(true);
						gagaCombatMap.setVisible(true);
						monster.setVisible(true);
						this.repaint();
					} else {
						// 如果第一次进入地下城，绘制怪物和地图
						if(!gagaCombatMap.flagPaint) {
							gagaCombatMap.paint();
							monster.paint();
						}else {// 非第一次进入地下城，即显示地下城和怪物，隐藏house地图
							skillBar.repaint();
							houseVisible = false;
							monster.setVisible(true);
							gagaCombatMap.setVisible(true);
							setMonsterHpBarVisible(true);
							this.repaint();
						}
					}
					lb_endingGame.setIcon(GameConstant.BT_IMAGE_BACK_HOME1);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					gagaHouseMap.setVisible(houseVisible);

				}
			}
		}).start();
	}


	public static void main(String[] args) {
		new GagaGame();
	}

	// 退出游戏/返回城镇按钮
	private void addMouseListener() {

		lb_endingGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (houseVisible) {
					lb_endingGame.setIcon(GameConstant.BT_IMAGE__ENDING_GAME2);
				} else lb_endingGame.setIcon(GameConstant.BT_IMAGE_BACK_HOME2);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (houseVisible) {
					lb_endingGame.setIcon(GameConstant.BT_IMAGE__ENDING_GAME1);
				} else lb_endingGame.setIcon(GameConstant.BT_IMAGE_BACK_HOME1);

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (houseVisible) {
					lb_endingGame.setIcon(GameConstant.BT_IMAGE__ENDING_GAME3);
					System.exit(-1);
				} else {
					lb_endingGame.setIcon(GameConstant.BT_IMAGE_BACK_HOME3);
					gagaHouseMap.setVisible(true);
					houseVisible = true;
					setMonsterHpBarVisible(false);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					gagaCombatMap.setVisible(false);
					monster.setVisible(false);
					// 为防止完家所处的位置正好进入关卡 所以重置一下玩家位置
					SwordManConstant.swordManX = (GameConstant.GAME_WINDOW_WIDTH - SwordManConstant.SWORD_MAN_WIDTH) / 2;
					// 人物所处窗口的Y坐标
					SwordManConstant.swordManY = (GameConstant.GAME_WINDOW_HEIGHT - SwordManConstant.SWORD_MAN_HEIGHT) / 2 + 30;
				}
			}
		});
	}


}
