package com.zwk.tool;

import com.zwk.client.LoginClient;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

public class NPCConstant {
	// 赛利亚 53 113
	public static final Integer SAI_LI_YA_WIDTH=53;
	public static final Integer SAI_LI_YA_HEIGHT=113;
	public static final Map<Integer, ImageIcon> NPC_SAI_LI_YA;
	public static final Integer SAI_LI_YA_X=(GameConstant.GAME_WINDOW_WIDTH-64)/2;
	public static final Integer SAI_LI_YA_Y=240;
	static {
		NPC_SAI_LI_YA = new HashMap<>();
		NPC_SAI_LI_YA.put(0, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/seria0.png").getPath()));
		NPC_SAI_LI_YA.put(1, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/seria1.png").getPath()));
		NPC_SAI_LI_YA.put(2, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/seria2.png").getPath()));
		NPC_SAI_LI_YA.put(3, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/seria3.png").getPath()));
		NPC_SAI_LI_YA.put(4, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/seria4.png").getPath()));
		NPC_SAI_LI_YA.put(5, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/seria5.png").getPath()));
		NPC_SAI_LI_YA.put(6, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/seria6.png").getPath()));
		NPC_SAI_LI_YA.put(7, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/seria7.png").getPath()));
		NPC_SAI_LI_YA.put(8, new ImageIcon(LoginClient.class.getResource("/com/zwk/images/imageRoom/seria8.png").getPath()));
	}
}
