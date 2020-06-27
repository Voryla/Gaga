package com.zwk.main;

import com.zwk.tool.GameConstant;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/*
地图测试
 */
public class TestMap extends JFrame {
	ImageIcon j=new ImageIcon(GameConstant.PANEL_IMAGE_EXIT);
	public TestMap(){
		this.setSize(800,600);
		JPanel jPanel=new JPanel();
		JLabel jLabel=new JLabel(j);

		jPanel.add(jLabel);
		jPanel.setBounds(0,0,1000,1000);
		jLabel.setBounds(0,0,j.getIconWidth(),j.getIconHeight());
		this.getContentPane().add(jPanel);this.setVisible(true);
		for(int i=1;i<100;i+=2) {
			try{
				Thread.sleep(100);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			jLabel.setBounds(-i, 0, j.getIconWidth(),j.getIconHeight());
			jPanel.add(jLabel);
			this.getContentPane().add(jPanel);
		}

	}

	public static void main(String[] args){
		TestMap testMap=new TestMap();
	}
}
