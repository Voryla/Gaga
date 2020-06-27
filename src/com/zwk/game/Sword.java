package com.zwk.game;

import com.zwk.tool.SwordManConstant;
import com.zwk.tool.SwordManSkillConstant;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

// 剑类
public class Sword extends JLabel {
	private ImageIcon image_Sword;
	private ImageIcon image_swordSkill;

	public Sword() {
		this.setSize(100, 180);
	}

	/**
	 * 根据人物方向 run状态 和sword名称绘制不同的剑, 因为剑的大小和位置不同暂且这样针对每一种剑更换位置，目前只做一种剑
	 *
	 * @param flag_Left_Right 人物面朝的方向
	 * @param flag_RUN        人物run状态
	 * @param direction       人物方向
	 * @param swordName       剑的名字
	 */
	public void paintSword(boolean flag_Left_Right, boolean flag_RUN, Direction direction, SwordsEnum swordName) {
		this.setSize(100, 180);
		switch (swordName) {
			case whiteSword:
				if (direction == Direction.STOP) {
					image_Sword = flag_Left_Right ? SwordManConstant.SWORD_IMAGE_RIGHT : SwordManConstant.SWORD_IMAGE_LEFT;
					if (flag_Left_Right) {
						this.setLocation(SwordManConstant.swordManX + 35, SwordManConstant.swordManY + 30);
					} else {
						this.setLocation(SwordManConstant.swordManX, SwordManConstant.swordManY + 30);
					}
				}
				// 当人物状态为Run时再根据左右标识更换动画 92 55
				else if (flag_RUN) {
					image_Sword = flag_Left_Right ? SwordManConstant.SWORD_IMAGE_RIGHT_RUN : SwordManConstant.SWORD_IMAGE_LEFT_RUN;
					if (flag_Left_Right) {
						this.setLocation(SwordManConstant.swordManX - 15, SwordManConstant.swordManY + 22);
						this.setLocation(SwordManConstant.swordManX - 25, SwordManConstant.swordManY + 22);
					} else {
						this.setLocation(SwordManConstant.swordManX + 55, SwordManConstant.swordManY + 20);
						this.setLocation(SwordManConstant.swordManX + 65, SwordManConstant.swordManY + 20);
					}
				}
				// 此时人物状态为走动，根据左右标识更换动画
				else {
					image_Sword = flag_Left_Right ? SwordManConstant.SWORD_IMAGE_RIGHT : SwordManConstant.SWORD_IMAGE_LEFT;
					if (flag_Left_Right) {
						this.setLocation(SwordManConstant.swordManX + 35, SwordManConstant.swordManY + 30);
					} else {
						this.setLocation(SwordManConstant.swordManX - 21, SwordManConstant.swordManY + 30);
					}
				}
				break;
			case holySword:
				break;
		}
		this.setIcon(image_Sword);
	}
}
