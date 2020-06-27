package com.zwk.game;

import com.zwk.tool.GameConstant;
import com.zwk.tool.SwordManSkillConstant;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 鬼剑士右下方技能栏
 */
public class SwordSkillBar extends JPanel {
	private JLabel lb_skill1;
	private JLabel lb_skill2;
	private JLabel lb_skill3;
	private JFrame mainJFrame;
	private JLabel lb_skillCDA;
	private JLabel lb_skillCDS;
	private JLabel lb_skillCDD;
	// 用来标记技能进入冷却时间
	private static boolean A = true, S = true, D = true, F = true, G = true, H = true;
	// 用以标记释放的技能
	private Integer CD = 0;
	// 标记了按下了哪个技能键
	private SkillKeyEnum skillKeyEnum;
	// 当一个技能释放时，该技能释放完毕时才可以释放其他技能，否则技能动画将十分混乱
	private boolean canUseSkill=true;

	public SwordSkillBar(JFrame mainJFrame) {
		this.mainJFrame = mainJFrame;
		lb_skillCDA = new JLabel(GameConstant.IMAGE_CD_0);
		lb_skillCDA.setVisible(false);
		this.add(lb_skillCDA);
		lb_skillCDS = new JLabel(GameConstant.IMAGE_CD_0);
		lb_skillCDS.setVisible(false);
		this.add(lb_skillCDS);
		lb_skillCDD = new JLabel(GameConstant.IMAGE_CD_0);
		lb_skillCDD.setVisible(false);
		this.add(lb_skillCDD);
		this.setOpaque(false);
		this.setLayout(null);
		this.setBounds(450, 519, 180, 30);
		// 添加技能图标
		lb_skill1 = new JLabel(SwordManSkillConstant.PICK_UP1);
		lb_skill1.setBounds(0, 1, GameConstant.CONSUMABLE_WIDTH, GameConstant.CONSUMABLE_HEIGHT);
		this.add(lb_skill1);
		lb_skill2 = new JLabel(SwordManSkillConstant.G_Z_1);
		lb_skill2.setBounds(30, 1, GameConstant.CONSUMABLE_WIDTH, GameConstant.CONSUMABLE_HEIGHT);
		this.add(lb_skill2);
		lb_skill3 = new JLabel(SwordManSkillConstant.L_B_Z_1);
		lb_skill3.setBounds(60, 1, GameConstant.CONSUMABLE_WIDTH, GameConstant.CONSUMABLE_HEIGHT);
		this.add(lb_skill3);
		// 添加监听事件
		addSkillKeyListener();
	}

	private void paint(Integer flag) {

	}
	// 添加按键控制
	private void addSkillKeyListener() {
		mainJFrame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// 当无法释放技能标识为false时不接收技能按键
				if(!canUseSkill)return;
				switch (e.getKeyCode()) {
					case KeyEvent.VK_A: {
						if (A) {
							A = false;
							skillKeyEnum = SkillKeyEnum.A;
							readCD(skillKeyEnum);
						}
						break;
					}
					case KeyEvent.VK_S: {
						if (S) {
							S = false;
							skillKeyEnum = SkillKeyEnum.S;
							readCD(skillKeyEnum);
						}
						break;
					}
					case KeyEvent.VK_D: {
						if (D) {
							D = false;
							skillKeyEnum = SkillKeyEnum.D;
							readCD(skillKeyEnum);
						}
					}
					break;
					case KeyEvent.VK_F:
						break;
					case KeyEvent.VK_G:
						break;
					case KeyEvent.VK_H:
						break;
				}
			}
		});
	}

	// 技能使用后冷却时间
	public void readCD(SkillKeyEnum skillKeyEnum) {
		switch (skillKeyEnum) {
			case A: {
				CD = 2;
				paintCD(lb_skill1, lb_skillCDA, SwordManSkillConstant.PICK_UP2);
			}
			break;
			case S:
				CD = 3;
				paintCD(lb_skill2, lb_skillCDS, SwordManSkillConstant.G_Z_2);
				break;
			case D:
				CD = 5;
				paintCD(lb_skill3, lb_skillCDD, SwordManSkillConstant.L_B_Z_2);
				break;
			case F:
				break;
			case G:
				break;
			case H:
				break;
		}

	}

	// 绘制冷却
	private void paintCD(JLabel lb_skill, JLabel lb_skillCD, ImageIcon icon) {

		new Thread(() -> {
			synchronized (lb_skillCD) {
				usedSkill();
				ImageIcon currentImage = (ImageIcon) lb_skill.getIcon();
				lb_skill.setIcon(icon);
				lb_skillCD.setIcon(GameConstant.CD_IMAGES.get(CD));
				lb_skillCD.setBounds(lb_skill.getX(), lb_skill.getY(), GameConstant.CONSUMABLE_WIDTH, GameConstant.CONSUMABLE_HEIGHT);
				lb_skillCD.setVisible(true);
				for (int i = CD ; i >= 0; i--) {
					try {
						Thread.sleep(1000);
						lb_skillCD.setIcon(GameConstant.CD_IMAGES.get(i));
					} catch (InterruptedException e) {
					}
				}
				lb_skillCD.setVisible(false);
				lb_skill.setIcon(currentImage);
				if (lb_skill.equals(lb_skill1)) A = true;
				if (lb_skill.equals(lb_skill2)) S = true;
				if (lb_skill.equals(lb_skill3)) D = true;
			}
		}).start();
	}
	// 一个技能按下后0.5s内将无法释放技能，这0.5s是播放这个技能动画用的
	private void usedSkill() {
		new Thread(()->{
			canUseSkill=false;
			if (A) {
				lb_skill1.setIcon(SwordManSkillConstant.PICK_UP2);
			}
			if (S) {
				lb_skill2.setIcon(SwordManSkillConstant.G_Z_2);
			}
			if (D) {
				lb_skill3.setIcon(SwordManSkillConstant.L_B_Z_2);

			}
			try{
				Thread.sleep(500);
			}catch (InterruptedException e){

			}
			if (A) {
				lb_skill1.setIcon(SwordManSkillConstant.PICK_UP1);
			}
			if (S) {

				lb_skill2.setIcon(SwordManSkillConstant.G_Z_1);
			}
			if (D) {
				lb_skill3.setIcon(SwordManSkillConstant.L_B_Z_1);

			}
			canUseSkill=true;
		}).start();

	}
	// 技能的枚举类
	private enum SkillKeyEnum {
		A, S, D, F, G, H;
	}
}
