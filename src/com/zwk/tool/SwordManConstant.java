package com.zwk.tool;

import com.zwk.client.LoginClient;
import javax.swing.ImageIcon;

public class SwordManConstant {
	// 人物宽度
	public static final Integer SWORD_MAN_WIDTH = 180;
	// 人物高度
	public static final Integer SWORD_MAN_HEIGHT = 160;
	// 人物所处窗口的X坐标
	public static Integer swordManX = (GameConstant.GAME_WINDOW_WIDTH - SWORD_MAN_WIDTH) / 2;
	// 人物所处窗口的Y坐标
	public static Integer swordManY = (GameConstant.GAME_WINDOW_HEIGHT - SWORD_MAN_HEIGHT) / 2 + 30;
	public static Integer hp=9000;
	public static final Double ALL_HP=9000.0;
	public static Integer mp;
	public static Integer speed = 2;
	public static Integer runSpeed=speed+2;
	public static Integer hurt;
	public static Integer defense;
	// 疲劳值
	public static Integer fatigueValues=156;
	// 城镇中鬼剑士面向右停止移动时动画
	public static final ImageIcon SWORD_MAN_HOUSE_STAY_RIGHT_IMAGE = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/stayRight.gif").getPath());
//	public static final Map<Integer, ImageIcon> SWORD_MAN_HOUSE_STAY_RIGHT_ANIMATION;
//
//	static {
//		SWORD_MAN_HOUSE_STAY_RIGHT_ANIMATION = new HashMap<>();
//		SWORD_MAN_HOUSE_STAY_RIGHT_ANIMATION.put(0, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/houseStay0.png").getPath()));
//		SWORD_MAN_HOUSE_STAY_RIGHT_ANIMATION.put(1, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/houseStay1.png").getPath()));
//		SWORD_MAN_HOUSE_STAY_RIGHT_ANIMATION.put(2, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/houseStay2.png").getPath()));
//		SWORD_MAN_HOUSE_STAY_RIGHT_ANIMATION.put(3, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/houseStay3.png").getPath()));
//	}

	// 城镇中鬼剑士面向左停止移动时动画
	public static final ImageIcon SWORD_MAN_HOUSE_STAY_LEFT_IMAGE = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/stayLeft.gif").getPath());
//	public static final Map<Integer, ImageIcon> SWORD_MAN_HOUSE_STAY_LEFT_ANIMATION;
//
//	static {
//		SWORD_MAN_HOUSE_STAY_LEFT_ANIMATION = new HashMap<>();
//		SWORD_MAN_HOUSE_STAY_LEFT_ANIMATION.put(0, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/houseStayLeft0.png").getPath()));
//		SWORD_MAN_HOUSE_STAY_LEFT_ANIMATION.put(1, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/houseStayLeft1.png").getPath()));
//		SWORD_MAN_HOUSE_STAY_LEFT_ANIMATION.put(2, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/houseStayLeft2.png").getPath()));
//		SWORD_MAN_HOUSE_STAY_LEFT_ANIMATION.put(3, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/houseStayLeft3.png").getPath()));
//	}

	// 鬼剑士向右时动画
	public static final ImageIcon SWORD_MAN_WALK_RIGHT_IMAGE = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/walkRight.gif").getPath());

//	public static final Map<Integer, ImageIcon> SWORD_MAN_WALK_RIGHT_ANIMATION;
//
//	static {
//		SWORD_MAN_WALK_RIGHT_ANIMATION = new HashMap<>();
//		SWORD_MAN_WALK_RIGHT_ANIMATION.put(0, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/walk0.png").getPath()));
//		SWORD_MAN_WALK_RIGHT_ANIMATION.put(1, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/walk1.png").getPath()));
//		SWORD_MAN_WALK_RIGHT_ANIMATION.put(2, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/walk2.png").getPath()));
//		SWORD_MAN_WALK_RIGHT_ANIMATION.put(3, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/walk3.png").getPath()));
//		SWORD_MAN_WALK_RIGHT_ANIMATION.put(4, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/walk4.png").getPath()));
//		SWORD_MAN_WALK_RIGHT_ANIMATION.put(5, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/walk5.png").getPath()));
//		SWORD_MAN_WALK_RIGHT_ANIMATION.put(6, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/walk6.png").getPath()));
//		SWORD_MAN_WALK_RIGHT_ANIMATION.put(7, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/walk7.png").getPath()));
//	}

	// 鬼剑士向左时动画
	public static final ImageIcon SWORD_MAN_WALK_LEFT_IMAGE = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/walkLeft.gif").getPath());

	// 地下城中奔跑
	public static final ImageIcon SWORD_MAN_RUN_RIGHT_IMAGE=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/rightRun.gif").getPath());
	public static final ImageIcon SWORD_MAN_RUN_LEFT_IMAGE=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/leftRun.gif").getPath());

	/*************************************************太刀**********************************************************/
	/*************************************************白剑**********************************************************/
	// 剑的属性
	// 右持剑
	public static final ImageIcon SWORD_IMAGE_RIGHT=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/sword/sword_right.png").getPath());
	// 左持剑
	public static final ImageIcon SWORD_IMAGE_LEFT=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/sword/sword_left.png").getPath());
	// 奔跑右持剑
	public static final ImageIcon SWORD_IMAGE_RIGHT_RUN=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/sword/sword_run_right.png").getPath());
	public static final ImageIcon SWORD_IMAGE_LEFT_RUN=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/sword/sword_run_left.png").getPath());

	/*************************************************称号动画**********************************************************/
	/*************************************************哥特**********************************************************/
	public static final ImageIcon TITLE_GOTHIC=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/title/title_gothic.gif").getPath());
	public static final Integer TITLE_GOTHIC_WIDTH=186;
	public static final Integer TITLE_GOTHIC_HEIGHT=35;
	// 停下时觉醒特效显示控件的宽高
//	public static final Integer STAY_SPECIAL_EFFECT_WIDTH=181;
//	public static final Integer STAY_SPECIAL_EFFECT_HEIGHT=158;
	// 觉醒动画
//	public static final ImageIcon SWORD_MAN_STAY_SPECIAL_EFFECT_LIFT_IMAGE=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/specialEffects/staySpecialEffects_Left.gif").getPath());
//	public static final ImageIcon SWORD_MAN_STAY_SPECIAL_EFFECT_RIGHT_IMAGE=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/specialEffects/staySpecialEffects_Right.gif").getPath());


//	public static final Map<Integer, ImageIcon> SWORD_MAN_WALK_LEFT_ANIMATION;
//
//	static {
//		SWORD_MAN_WALK_LEFT_ANIMATION = new HashMap<>();
//		SWORD_MAN_WALK_LEFT_ANIMATION.put(0, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/walkLeft0.png").getPath()));
//		SWORD_MAN_WALK_LEFT_ANIMATION.put(1, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/walkLeft1.png").getPath()));
//		SWORD_MAN_WALK_LEFT_ANIMATION.put(2, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/walkLeft2.png").getPath()));
//		SWORD_MAN_WALK_LEFT_ANIMATION.put(3, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/walkLeft3.png").getPath()));
//		SWORD_MAN_WALK_LEFT_ANIMATION.put(4, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/walkLeft4.png").getPath()));
//		SWORD_MAN_WALK_LEFT_ANIMATION.put(5, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/walkLeft5.png").getPath()));
//		SWORD_MAN_WALK_LEFT_ANIMATION.put(6, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/walkLeft6.png").getPath()));
//		SWORD_MAN_WALK_LEFT_ANIMATION.put(7, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordMan/walkLeft7.png").getPath()));
//	}
}
