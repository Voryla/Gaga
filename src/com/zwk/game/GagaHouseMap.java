package com.zwk.game;

import com.zwk.tool.GameConstant;
import com.zwk.tool.GameConstant;
import com.zwk.tool.NPCConstant;
import com.zwk.tool.SwordManConstant;
import com.zwk.tool.WindowTool;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GagaHouseMap implements GagaHowDoMap {
	JPanel jPanel = new JPanel();
	private Integer x = 0;
	private Integer y = 0;
	private Integer width = 0;
	private Integer height = 0;
	private JFrame jFrame = null;
	private JLabel lb_back;
	private JLabel lb_column1;
	private JLabel lb_column2;
	private JLabel lb_houseWave;
	private JLabel lb_saiLiYa;
	private boolean visible=true;
	public GagaHouseMap(JFrame jFrame) {
		this.jFrame = jFrame;
	}


	@Override
	public void paint() {
		jPanel.setOpaque(false);
		jPanel.setLayout(null);
		jPanel.setBounds(-90, 90, GameConstant.GAME_WINDOW_WIDTH, GameConstant.GAME_WINDOW_HEIGHT);
		// 赛利亚房间背景图片
		lb_back = new JLabel(GameConstant.CELIA_ROOM_IMAGE_BACK);
		lb_back.setBounds(-80, 0, GameConstant.CELIA_ROOM_IMAGE_BACK.getIconWidth(), GameConstant.CELIA_ROOM_IMAGE_BACK.getIconHeight());
		// 门口柱子
		lb_column1 = new JLabel(GameConstant.CELIA_ROOM_IMAGE_COLUMN1);
		lb_column1.setBounds(363, 322, 239, 101);
		// 门口柱子背景
		lb_column2 = new JLabel(GameConstant.CELIA_ROOM_IMAGE_COLUMN2);
		lb_column2.setBounds(330, 295, 305, 104);


		// 赛利亚房间出口
		lb_houseWave = new JLabel();
		lb_houseWave.setBounds(GameConstant.MAP_HOUSE_WAVE_X, GameConstant.MAP_HOUSE_WAVE_Y, GameConstant.MAP_HOUSE_WAVE_WIDTH, GameConstant.MAP_HOUSE_WAVE_HEIGHT);
		// 出口动画
		new Thread(() -> {
			while (true) {
				if(!GagaGame.houseVisible)continue;
				GameConstant.MAP_HOUSE_WAVE.forEach((integer, imageIcon) -> {
					imageIcon = WindowTool.getScaleImageIcon(imageIcon, GameConstant.MAP_HOUSE_WAVE_WIDTH, GameConstant.MAP_HOUSE_WAVE_HEIGHT);
					try {
						Thread.sleep(150);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					lb_houseWave.setIcon(imageIcon);
				});

			}
		}).start();
//		new Thread(()->{
//			while (true){
//				if (SwordManConstant.swordManX < SwordManConstant.SWORD_MAN_BORDER_LINE_LEFT)
//				jFrame.getContentPane().setLocation(lb_back.getX()+100,0);
//				try{
//					Thread.sleep(20);
//				}catch (InterruptedException e){
//					e.printStackTrace();
//				}
//			}
//		}).start();
		jPanel.add(lb_column1);
		jPanel.add(lb_column2);
		jPanel.add(lb_houseWave);
		jFrame.getLayeredPane().add(jPanel);
		jFrame.getContentPane().add(lb_back);
		// 碰撞检测
	}

	public void setVisible(boolean flag) {
		visible=flag;
		jPanel.setVisible(flag);
		lb_back.setVisible(flag);
		lb_column1.setVisible(flag);
		lb_column2.setVisible(flag);
		lb_houseWave.setVisible(flag);
		lb_saiLiYa.setVisible(flag);
	}

	public void printSAILIYA() {
		// 赛利亚NPC
		lb_saiLiYa = new JLabel();
		lb_saiLiYa.setBounds(NPCConstant.SAI_LI_YA_X, NPCConstant.SAI_LI_YA_Y, NPCConstant.SAI_LI_YA_WIDTH, NPCConstant.SAI_LI_YA_HEIGHT);
		jFrame.getLayeredPane().add(lb_saiLiYa);
		// 赛利亚动画
		new Thread(() -> {
			while (true) {
				if(!GagaGame.houseVisible)continue;
				NPCConstant.NPC_SAI_LI_YA.forEach((integer, imageIcon) -> {
					imageIcon = WindowTool.getScaleImageIcon(imageIcon, NPCConstant.SAI_LI_YA_WIDTH, NPCConstant.SAI_LI_YA_HEIGHT);
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					lb_saiLiYa.setIcon(imageIcon);
				});

			}
		}).start();
	}
}
