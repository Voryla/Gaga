package com.zwk.tool;

import com.zwk.client.LoginClient;
import javax.swing.ImageIcon;

/**
 * 鬼剑士技能图标以及技能动画
 */
public class SwordManSkillConstant {
	/*****************************************************技能图标 1图为可使用状态，2图为冷却状态*******************************************************/
	// 上挑
	public static final ImageIcon PICK_UP1 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordManSkillIcon/94.png").getPath());
	public static final ImageIcon PICK_UP2 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordManSkillIcon/95.png").getPath());
	// 鬼斩
	public static final ImageIcon G_Z_1 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordManSkillIcon/10.png"));
	public static final ImageIcon G_Z_2 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordManSkillIcon/11.png"));
	// 裂波斩
	public static final ImageIcon L_B_Z_1 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordManSkillIcon/6.png"));
	public static final ImageIcon L_B_Z_2 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordManSkillIcon/7.png"));
	/*****************************************************技能动画*******************************************************/
	// 人物普通攻击动作
	public static final ImageIcon ATTACK1 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/sword/doSkill/attack1.gif").getPath());
	public static final ImageIcon ATTACK2 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/sword/doSkill/attack2.gif").getPath());
	public static final ImageIcon ATTACK3 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/sword/doSkill/attack3.gif").getPath());
	public static final ImageIcon ATTACK1_LEFT = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/sword/doSkill/attack1_left.gif").getPath());
	public static final ImageIcon ATTACK2_LEFT = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/sword/doSkill/attack2_left.gif").getPath());
	public static final ImageIcon ATTACK3_LEFT = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/sword/doSkill/attack3_left.gif").getPath());
	// 技能---鬼斩
	public static final Integer HARD_ATTACK_BLADE_CD=3;
	public static final ImageIcon  HARD_ATTACK_BLADE=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/sword/doSkill/hardattackblade.gif").getPath());
	public static final ImageIcon  HARD_ATTACK_BLADE_LEFT=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/sword/doSkill/hardattackblade_left.gif").getPath());
	public static final ImageIcon  HARD_ATTACK_BLADE_MAN=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/sword/doSkill/hardattackblade_man.gif").getPath());
	public static final ImageIcon  HARD_ATTACK_BLADE_MAN_LEFT=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/sword/doSkill/hardattackblade_man_left.gif").getPath());
	// 技能---裂波斩
	public static final Integer LIGHT_HADOUKEN_HADOU_CD=5;
	public static final ImageIcon LIGHT_HADOUKEN_HADOU=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/sword/doSkill/light_hadouken_hadou.gif").getPath());
	public static final ImageIcon LIGHT_HADOUKEN_HADOU_LEFT=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/sword/doSkill/light_hadouken_hadou_left.gif").getPath());

	// 受击
	public static final ImageIcon SWORD_MAN_HIT=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/sword/doSkill/swordManHit.gif").getPath());
	public static final ImageIcon SWORD_MAN_DEATH=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/sword/doSkill/swordManDeath.gif").getPath());

}
