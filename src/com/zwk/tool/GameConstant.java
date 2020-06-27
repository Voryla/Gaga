package com.zwk.tool;

import com.zwk.client.LoginClient;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

public class GameConstant {
	// 窗体大小
	public static final Integer WINDOW_WIDTH = 1300;
	public static final Integer WINDOW_HEIGHT = 800;

	// 提示框大小
	public static final Integer PROMPT_BOX_WIDTH = 352;
	public static final Integer PROMPT_BOX_HEIGHT = 176;
	// 注册/修改密码按钮url
	public static final String URL_REGISTER = "http://localhost:8080/GagaServer/GagaRegister.html";
	public static final String URL_UPDATE_PWD = "http://localhost:8080/GagaServer/GagaUpdatePwd.html";
	// 确认/取消按钮图片路径
	public static final String BT_IMAGE_EXIT_NO1 = LoginClient.class.getResource("/com/zwk/images/bt_exit_no1.png").getPath().replace("%20", " ");
	public static final String BT_IMAGE_EXIT_NO2 = LoginClient.class.getResource("/com/zwk/images/bt_exit_no2.png").getPath().replace("%20", " ");
	public static final String BT_IMAGE_EXIT_YES1 = LoginClient.class.getResource("/com/zwk/images/bt_exit_yes1.png").getPath().replace("%20", " ");
	public static final String BT_IMAGE_EXIT_YES2 = LoginClient.class.getResource("/com/zwk/images/bt_exit_yes2.png").getPath().replace("%20", " ");
	// 登陆游戏按钮图片路径
	public static final String BT_IMAGE_LOGIN = LoginClient.class.getResource("/com/zwk/images/bt_login.png").getPath().replace("%20", " ");
	// 退出提示框图片路径
	public static final String PANEL_IMAGE_EXIT = LoginClient.class.getResource("/com/zwk/images/tip.png").getPath().replace("%20", " ");
	// 登陆成功提示框图片路径
	public static final String PANEL_IMAGE_LOGIN_FAILED = LoginClient.class.getResource("/com/zwk/images/landingFailed.png").getPath().replace("%20", " ");
	// 登陆失败提示框图片路径
	public static final String PANEL_IMAGE_LOGIN_SUCCESS = LoginClient.class.getResource("/com/zwk/images/landingSuccessfully.png").getPath().replace("%20", " ");
	// 登陆客户端右上角退出按钮图片路径
	public static final String BT_IMAGE_EXIT1 = LoginClient.class.getResource("/com/zwk/images/bt_close1.png").getPath().replace("%20", " ");
	public static final String BT_IMAGE_EXIT2 = LoginClient.class.getResource("/com/zwk/images/bt_close2.png").getPath().replace("%20", " ");
	public static final String LOGIN_CLIENT_IMAGE_BACKGROUND = LoginClient.class.getResource("/com/zwk/images/backGround.jpg").getPath().replace("%20", " ");
	// 光标image，因为一直要使用所以提前加载
	public static final ImageIcon CURSOR_IMAGE1 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/gameUI/cursor1.png").getPath().replace("%20", " "));
	public static final ImageIcon CURSOR_IMAGE2 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/gameUI/cursor2.png").getPath().replace("%20", " "));

	// 游戏常量
	// 技能栏 宽648 高88
	public static final ImageIcon SKILL_BAR = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/gameUI/skillBar.png").getPath().replace("%20", " "));
	// 血条
	public static final ImageIcon HP_IMAGE = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/gameUI/hp.png").getPath().replace("%20", " "));
	// 蓝条
	public static final ImageIcon MP_IMAGE = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/gameUI/mp.png").getPath().replace("%20", " "));

	public static final Integer GAME_WINDOW_WIDTH = 800;
	public static final Integer GAME_WINDOW_HEIGHT = 600;
	public static final Integer GAME_SKILL_BAR_WIDTH = 640;
	public static final Integer GAME_SKILL_BAR_HEIGHT = 88;

	// 赛利亚房间界面
	// 0,0,954,560
	public static final ImageIcon CELIA_ROOM_IMAGE_BACK = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/roomBack.png").getPath());
	public static final ImageIcon CELIA_ROOM_IMAGE_DECORATE0 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/decorate1.png").getPath());
	public static final ImageIcon CELIA_ROOM_IMAGE_DECORATE1 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/decorate2.png").getPath());
	public static final ImageIcon CELIA_ROOM_IMAGE_DECORATE2 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/decorate3.png").getPath());
	// 363,367,239,101
	public static final ImageIcon CELIA_ROOM_IMAGE_COLUMN1 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/column1.png").getPath());
	// 330,340,305,104
	public static final ImageIcon CELIA_ROOM_IMAGE_COLUMN2 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/column2.png").getPath());
	// 赛利亚房间出口
	public static final Map<Integer, ImageIcon> MAP_HOUSE_WAVE;
	public static final Integer MAP_HOUSE_WAVE_WIDTH = 80;
	public static final Integer MAP_HOUSE_WAVE_HEIGHT = 135;
	public static final Integer MAP_HOUSE_WAVE_X = 440;
	public static final Integer MAP_HOUSE_WAVE_Y = 300;

	static {
		MAP_HOUSE_WAVE = new HashMap();
		MAP_HOUSE_WAVE.put(0, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/houseWave0.png").getPath()));
		MAP_HOUSE_WAVE.put(1, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/houseWave1.png").getPath()));
		MAP_HOUSE_WAVE.put(2, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/houseWave2.png").getPath()));
		MAP_HOUSE_WAVE.put(3, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/houseWave3.png").getPath()));
		MAP_HOUSE_WAVE.put(4, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/houseWave4.png").getPath()));
		MAP_HOUSE_WAVE.put(5, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/houseWave5.png").getPath()));
		MAP_HOUSE_WAVE.put(6, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/houseWave6.png").getPath()));
		MAP_HOUSE_WAVE.put(7, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/houseWave7.png").getPath()));
		MAP_HOUSE_WAVE.put(8, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/houseWave8.png").getPath()));
	}

	// 生物左边界线
	public static final Integer BIOLOGY_BORDER_LINE_LEFT = 0;
	// 生物右边界线
	public static final Integer BIOLOGY_BORDER_LINE_RIGHT = 40 + GAME_WINDOW_WIDTH - SwordManConstant.SWORD_MAN_WIDTH;
	// 生物上边界线
	public static final Integer BIOLOGY_BORDER_LINE_UP = 230;
	// 生物下边界线
	public static final Integer BIOLOGY_BORDER_LINE_DOWN = 358;

	// 消耗品
	public static final Integer CONSUMABLE_WIDTH = 30;
	public static final Integer CONSUMABLE_HEIGHT = 30;
	// 消耗品图片1
	public static final ImageIcon IMAGE_CONSUMABLE_1 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/consumables/1.png").getPath());
	public static final ImageIcon IMAGE_CONSUMABLE_2 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/consumables/2.png").getPath());
	public static final ImageIcon IMAGE_CONSUMABLE_3 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/consumables/3.png").getPath());
	// 消耗品按键提示
	public static final ImageIcon IMAGE_CONSUMABLE_1_key = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/consumables/6.png").getPath());
	public static final ImageIcon IMAGE_CONSUMABLE_2_key = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/consumables/7.png").getPath());
	public static final ImageIcon IMAGE_CONSUMABLE_3_key = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/consumables/8.png").getPath());
	public static final ImageIcon IMAGE_CONSUMABLE_4_key = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/consumables/9.png").getPath());
	public static final ImageIcon IMAGE_CONSUMABLE_5_key = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/consumables/10.png").getPath());
	public static final ImageIcon IMAGE_CONSUMABLE_6_key = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/consumables/11.png").getPath());

	// 等级图片
	public static final ImageIcon IMAGE_GRADE = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/grade/11.png").getPath());
	public static final ImageIcon IMAGE_GRADE_0 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/grade/0.png").getPath());
	public static final ImageIcon IMAGE_GRADE_1 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/grade/1.png").getPath());
	public static final ImageIcon IMAGE_GRADE_2 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/grade/2.png").getPath());
	public static final ImageIcon IMAGE_GRADE_3 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/grade/3.png").getPath());
	public static final ImageIcon IMAGE_GRADE_4 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/grade/4.png").getPath());
	public static final ImageIcon IMAGE_GRADE_5 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/grade/5.png").getPath());
	public static final ImageIcon IMAGE_GRADE_6 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/grade/6.png").getPath());
	public static final ImageIcon IMAGE_GRADE_7 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/grade/7.png").getPath());
	public static final ImageIcon IMAGE_GRADE_8 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/grade/8.png").getPath());
	public static final ImageIcon IMAGE_GRADE_9 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/grade/9.png").getPath());

	// 技能键位图片
	public static final ImageIcon IMAGE_KEY_ICON_A = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/skillKey/89.png").getPath());
	public static final ImageIcon IMAGE_KEY_ICON_S = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/skillKey/90.png").getPath());
	public static final ImageIcon IMAGE_KEY_ICON_D = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/skillKey/91.png").getPath());
	public static final ImageIcon IMAGE_KEY_ICON_F = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/skillKey/92.png").getPath());
	public static final ImageIcon IMAGE_KEY_ICON_G = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/skillKey/93.png").getPath());
	public static final ImageIcon IMAGE_KEY_ICON_H = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/skillKey/94.png").getPath());
	// 因为要涉及到图片的循环切换，现将图片防止Map中
	public static final Map<Integer, ImageIcon> CD_IMAGES;
	// 技能及消耗品冷却时间图片
	public static final ImageIcon IMAGE_CD_0 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordManSkillIcon/CD0.png").getPath());
	public static final ImageIcon IMAGE_CD_1 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordManSkillIcon/CD1.png").getPath());
	public static final ImageIcon IMAGE_CD_2 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordManSkillIcon/CD2.png").getPath());
	public static final ImageIcon IMAGE_CD_3 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordManSkillIcon/CD3.png").getPath());
	public static final ImageIcon IMAGE_CD_4 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordManSkillIcon/CD4.png").getPath());
	public static final ImageIcon IMAGE_CD_5 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordManSkillIcon/CD5.png").getPath());
	public static final ImageIcon IMAGE_CD_6 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordManSkillIcon/CD6.png").getPath());
	public static final ImageIcon IMAGE_CD_7 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordManSkillIcon/CD7.png").getPath());
	public static final ImageIcon IMAGE_CD_8 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordManSkillIcon/CD8.png").getPath());
	public static final ImageIcon IMAGE_CD_9 = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/swordManSkillIcon/CD9.png").getPath());

	static {
		CD_IMAGES = new HashMap<>();
		CD_IMAGES.put(0, IMAGE_CD_0);
		CD_IMAGES.put(1, IMAGE_CD_1);
		CD_IMAGES.put(2, IMAGE_CD_2);
		CD_IMAGES.put(3, IMAGE_CD_3);
		CD_IMAGES.put(4, IMAGE_CD_4);
		CD_IMAGES.put(5, IMAGE_CD_5);
		CD_IMAGES.put(6, IMAGE_CD_6);
		CD_IMAGES.put(7, IMAGE_CD_7);
		CD_IMAGES.put(8, IMAGE_CD_8);
		CD_IMAGES.put(9, IMAGE_CD_9);
	}
	// 战斗地图
	public static final ImageIcon COMBAT_MAP_BACK = new ImageIcon(LoginClient.class.getResource("/com/zwk/images/combatMap/map.png").getPath());

	// 中下方结束游戏/返回城镇按钮
	public static final ImageIcon BT_IMAGE_BACK_HOME1=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/home1.png").getPath());
	public static final ImageIcon BT_IMAGE_BACK_HOME2=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/home2.png").getPath());
	public static final ImageIcon BT_IMAGE_BACK_HOME3=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/home3.png").getPath());
	public static final ImageIcon BT_IMAGE__ENDING_GAME1=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/ending1.png").getPath());
	public static final ImageIcon BT_IMAGE__ENDING_GAME2=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/ending2.png").getPath());
	public static final ImageIcon BT_IMAGE__ENDING_GAME3=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/ending3.png").getPath());
	// 怪物头像用以血条怪物左侧显示
	public static final ImageIcon MONSTER_IMAGE_ICON=new ImageIcon(LoginClient.class.getResource("/com/zwk/images/monster/monsterIcon.png").getPath());

}
