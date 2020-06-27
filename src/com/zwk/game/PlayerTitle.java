package com.zwk.game;

import com.zwk.tool.GameConstant;
import com.zwk.tool.SwordManConstant;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PlayerTitle extends JLabel {
	private ImageIcon image_title;
	public PlayerTitle(){
		this.setSize(SwordManConstant.TITLE_GOTHIC_WIDTH,SwordManConstant.TITLE_GOTHIC_HEIGHT);
	}
	public void paintTitle(TitleEnum titleEnum){
		switch (titleEnum){
			case TITLE_GOTHIC:
				image_title=SwordManConstant.TITLE_GOTHIC;
				break;
			case TITLE_GOLD:
				break;
		}
		this.setIcon(image_title);
		this.setLocation(SwordManConstant.swordManX-40,SwordManConstant.swordManY-SwordManConstant.TITLE_GOTHIC_HEIGHT);
	}
}