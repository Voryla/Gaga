package com.zwk.game;

import com.zwk.tool.SwordManConstant;
import com.zwk.tool.SwordManSkillConstant;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 绘制技能以及碰撞检测
 */
public class DoSkill extends JLabel {
	private ImageIcon image_skill;
	private static volatile Boolean readCDA = true, readCDS = true, readCDD = true, readCDF = true, readCDG = true, readCDH = true;
	private int x, y;

	public DoSkill() {
		this.setOpaque(false);
		this.setVisible(false);
		this.setSize(200, 200);
	}

	public static void setReadCDA(Boolean readCDA) {
		DoSkill.readCDA = readCDA;
	}

	public static void setReadCDS(Boolean readCDS) {
		DoSkill.readCDS = readCDS;
	}

	public static void setReadCDD(Boolean readCDD) {
		DoSkill.readCDD = readCDD;
	}

	public static void setReadCDF(Boolean readCDF) {
		DoSkill.readCDF = readCDF;
	}

	public static void setReadCDG(Boolean readCDG) {
		DoSkill.readCDG = readCDG;
	}

	public static void setReadCDH(Boolean readCDH) {
		DoSkill.readCDH = readCDH;
	}

	public void paintSkill(boolean flag_Left_Right, int keyCode) {

		switch (keyCode) {
			case KeyEvent.VK_A: {
				if (readCDA) {
					new Thread(() -> {
						readCDA = false;
						x = flag_Left_Right ? SwordManConstant.swordManX + 70 : SwordManConstant.swordManX - 150;
						y = SwordManConstant.SWORD_MAN_HEIGHT + 90;
						try {
							Thread.sleep(3 * 1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						readCDA = true;
					}).start();
					image_skill = flag_Left_Right ? SwordManSkillConstant.HARD_ATTACK_BLADE : SwordManSkillConstant.HARD_ATTACK_BLADE_LEFT;
				} else
					image_skill = null;

				break;
			}
			case KeyEvent.VK_S: {

				new Thread(() -> {
					synchronized (readCDD) {
						if (readCDS) {
							readCDS = false;
							x = flag_Left_Right ? SwordManConstant.swordManX + 70 : SwordManConstant.swordManX - 150;
							y = SwordManConstant.SWORD_MAN_HEIGHT + 90;
							image_skill = flag_Left_Right ? SwordManSkillConstant.HARD_ATTACK_BLADE : SwordManSkillConstant.HARD_ATTACK_BLADE_LEFT;
							paintSkill();
							try {
								Thread.sleep(400);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							this.setVisible(false);
							try {
								Thread.sleep(2600);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							readCDS = true;
						} else {
							image_skill = null;
						}
					}
				}).start();

				break;
			}
			case KeyEvent.VK_D: {
//				if (readCDD) {
//					new Thread(() -> {
//						readCDD = false;
//						x = flag_Left_Right ? SwordManConstant.swordManX+70 : SwordManConstant.swordManX-150;
//						y = SwordManConstant.SWORD_MAN_HEIGHT + 90;
//						try {
//							Thread.sleep(SwordManSkillConstant.HARD_ATTACK_BLADE_CD * 1000);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//						readCDD = true;
//					}).start();
//					//image_skill=flag_Left_Right?SwordManSkillConstant.LIGHT_HADOUKEN_HADOU:SwordManSkillConstant.LIGHT_HADOUKEN_HADOU_LEFT;
//
//				} else
//					image_skill = null;
				break;
			}
			case KeyEvent.VK_F: {

				break;
			}
			case KeyEvent.VK_G: {

				break;
			}
		}
	}
	private void paintSkill(){
		new Thread(() -> {
			if (image_skill == null) return;
			this.setVisible(true);
			this.setLocation(x, y);
			this.setIcon(image_skill);
		}).start();
	}
	public static Boolean getReadCDA() {
		return readCDA;
	}

	public static Boolean getReadCDS() {
		return readCDS;
	}

	public static Boolean getReadCDD() {
		return readCDD;
	}

	public static Boolean getReadCDF() {
		return readCDF;
	}

	public static Boolean getReadCDG() {
		return readCDG;
	}

	public static Boolean getReadCDH() {
		return readCDH;
	}
}
