package com.zwk.game;

import com.zwk.tool.GameConstant;
import java.awt.Label;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 战斗地图
 */
public class GagaCombatMap implements GagaHowDoMap {
	public  boolean flagPaint=false;
	private JFrame mainJFrame;
	public GagaCombatMap(JFrame jFrame){
		this.mainJFrame=jFrame;
	}
	private JLabel lb_back;
	@Override
	public void paint() {
		lb_back = new JLabel(GameConstant.COMBAT_MAP_BACK);
		lb_back.setBounds(0, 0, GameConstant.CELIA_ROOM_IMAGE_BACK.getIconWidth(), GameConstant.CELIA_ROOM_IMAGE_BACK.getIconHeight());
		mainJFrame.getContentPane().add(lb_back);
		flagPaint=true;
	}
	public void setVisible(boolean flag){
		lb_back.setVisible(flag);
	}
}