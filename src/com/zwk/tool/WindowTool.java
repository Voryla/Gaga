package com.zwk.tool;

import com.zwk.client.LoginClient;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javazoom.jl.player.Player;

public class WindowTool {
	private static Toolkit toolKit = Toolkit.getDefaultToolkit();
	private static Player player;
	private static volatile boolean musicIsPlay = false;
	static BufferedInputStream buffer;

	// 获取屏幕宽度
	public static Integer getScreenWidth() {
		return (int) toolKit.getScreenSize().getWidth();
	}

	// 获取屏幕高度
	public static Integer getScreenHeight() {
		return (int) toolKit.getScreenSize().getHeight();
	}

	// 设置背景图片
	public static void setBackImage(String imagePath, JFrame jFrame,Integer width,Integer height) {
		try {
			JLabel jLabel = new JLabel(new ImageIcon(imagePath));
			jLabel.setBounds(0, 0, width, height);
			jFrame.getContentPane().add(jLabel);
		} catch (Exception e) {

		}

	}

	/**
	 * 播放音乐
	 *
	 * @param musicPath
	 */
	public static void musicPlay(String musicPath) {
		new Thread(() -> {
			while (musicIsPlay) {
			}
			synchronized (WindowTool.class) {

				try {
					File file = new File(
							LoginClient.class.getResource("/com/zwk/music/loginBGM.mp3").getPath().replace("%20", " "));
					if (file.exists()) {
						musicIsPlay = true;
						buffer = new BufferedInputStream(new FileInputStream(file));
						player = new Player(buffer);
						player.play();
						buffer.close();
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}

		}).start();
	}

	/**
	 * 播放音乐
	 *
	 * @param file
	 */
	public static void play(File file) {
		new Thread(() -> {
			synchronized (WindowTool.class) {
				// if (!musicIsPlay) {
				try {
					if (file.exists()) {
						musicIsPlay = true;
						BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(file));
						player = new Player(buffer);
						player.play();
					}
				} catch (Exception e) {
					System.out.println(e);
				}
				// }
			}
		}).start();
	}

	/**
	 * 关闭音乐
	 */
	public static void musicClose() {
		new Thread(() -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
			if (player != null) {
				player.close();
				player = null;
				musicIsPlay = false;
			}
		}).start();

	}

	/**
	 * 获取透明图片提示框JPanel
	 *
	 * @param backImage
	 * @return
	 */
	public static JPanel getImagePromptPanel(ImageIcon backImage) {
		JPanel jPanel = new JPanel();
		jPanel.setOpaque(false);
		jPanel.setLayout(null);
		jPanel.setBounds((GameConstant.WINDOW_WIDTH - GameConstant.PROMPT_BOX_WIDTH) / 2,
				(GameConstant.WINDOW_HEIGHT - GameConstant.PROMPT_BOX_HEIGHT) / 2, GameConstant.PROMPT_BOX_WIDTH,
				GameConstant.PROMPT_BOX_HEIGHT);
		JLabel backLabel = new JLabel(backImage);
		backLabel.setBounds(0, 0, GameConstant.PROMPT_BOX_WIDTH, GameConstant.PROMPT_BOX_HEIGHT);
		jPanel.add(backLabel);
		return jPanel;
	}

	/**
	 * 获取透明图片Button
	 *
	 * @param backIcon
	 * @param replaceImage
	 * @return
	 */

	public static JButton getImageButton(ImageIcon backIcon, ImageIcon replaceImage) {

		JButton yes_no_button = new JButton(backIcon);
		yes_no_button.setContentAreaFilled(false);
		yes_no_button.setBorderPainted(false);
		yes_no_button.setFocusPainted(false);
		yes_no_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				yes_no_button.setIcon(backIcon);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				yes_no_button.setIcon(replaceImage);
			}
		});
		return yes_no_button;
	}

	/**
	 * 获取透明Button，注意：位置和大小自行设置
	 *
	 * @param title
	 * @return
	 */
	public static JButton getTransparentButton(String title) {
		JButton transparentButton = new JButton();
		transparentButton.setText(title);
		transparentButton.setBorderPainted(false);
		transparentButton.setFocusPainted(false);
		transparentButton.setOpaque(false);
		transparentButton.setFont(new Font("", Font.ITALIC, 16));
		transparentButton.setContentAreaFilled(false);
		return transparentButton;
	}

	/**
	 * 改变鼠标图片
	 *
	 * @param jFrame
	 */
	public static void changeCursor(JFrame jFrame) {
		Image image = GameConstant.CURSOR_IMAGE1.getImage();
		Cursor cursor = toolKit.createCustomCursor(image, new Point(10, 10), "norm");
		jFrame.getLayeredPane().setCursor(cursor); //panel 也可以是其他组件
		jFrame.getLayeredPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				Image image = GameConstant.CURSOR_IMAGE2.getImage();
				Cursor cursor = toolKit.createCustomCursor(image, new Point(10, 10), "norm");
				jFrame.getLayeredPane().setCursor(cursor);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				Image image = GameConstant.CURSOR_IMAGE1.getImage();
				Cursor cursor = toolKit.createCustomCursor(image, new Point(10, 10), "norm");
				jFrame.getLayeredPane().setCursor(cursor); //panel 也可以是其他组件
			}
		});
	}

	// 使用默认浏览器打开网页
	public static void browse(String url) {
		Desktop desktop = Desktop.getDesktop();
		if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				URI uri = new URI(url);
				desktop.browse(uri);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

		}
	}

	public static ImageIcon getScaleImageIcon(ImageIcon imageIcon,Integer width,Integer height){
		imageIcon.setImage(imageIcon.getImage().getScaledInstance(width,height, Image.SCALE_DEFAULT));
		return imageIcon;
	}
}
