package com.zwk.client;

import com.zwk.game.GagaGame;
import com.zwk.tool.GameConstant;
import com.zwk.tool.MyDocument;
import com.zwk.tool.WindowTool;
import com.zwk.util.SocketManager;
import com.zwk.util.User;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * 游戏登陆客户端
 */
public class LoginClient extends JFrame {
	// 账号输入框
	private JTextField tf_userName;
	// 密码输入框
	private JPasswordField tf_password;
	private LoginClient mainClient;
	// socket连接
	private Socket client = null;
	// 提示用户账号密码的输入规范
	private JTextArea ta_tips = null;

	private Boolean loginState = false;

	public LoginClient() {
		WindowTool.changeCursor(this);
		this.setTitle("Gaga");
		// 设置屏幕中央显示窗体
		this.setBounds((WindowTool.getScreenWidth() - GameConstant.WINDOW_WIDTH) / 2,
				(WindowTool.getScreenHeight() - GameConstant.WINDOW_HEIGHT) / 2, GameConstant.WINDOW_WIDTH,
				GameConstant.WINDOW_HEIGHT);
		// 去除顶部标题栏
		this.setUndecorated(true);
		// 设置背景图片
		WindowTool.setBackImage(GameConstant.LOGIN_CLIENT_IMAGE_BACKGROUND, this,GameConstant.WINDOW_WIDTH,GameConstant.WINDOW_HEIGHT);
		// 登陆界面BGM
		//WindowTool.musicPlay("/com/zwk/music/loginBGM.mp3");
		// 设置无法更改屏幕大小
		this.setResizable(false);
		// 关闭窗口
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		settingLayout();
		mainClient = this;
		this.setVisible(true);
	}

	private void settingLayout() {
		addLoginButton();
		addLoginTextBox();
		addCloseButton();
		addRegisterButton();
	}

	/**
	 * 登陆按钮
	 */
	public void addLoginButton() {
		JPanel loginBt_Panel = new JPanel();
		JButton jbutton = new JButton();
		ImageIcon images = new ImageIcon(GameConstant.BT_IMAGE_LOGIN);
		jbutton.setIcon(images);
		// 不绘制内容区
		jbutton.setContentAreaFilled(false);
		// 不绘制聚焦
		jbutton.setFocusPainted(false);
		// 不绘制边框
		jbutton.setBorderPainted(false);
		jbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				User user = new User();
				String username = tf_userName.getText();
				String pwd = new String().valueOf(tf_password.getPassword());
				if (username.length() > 9 || pwd.length() > 9) {
					System.out.println("超出界限");
					ta_tips.setVisible(true);
					return;
				}
				if (tf_userName.getText() != null && tf_password.getPassword().toString() != null) {
					user.setUserName(tf_userName.getText());
					// 因为password文本框控件getPassword方法获取的是字符数组所以需要String.valueOf()转化
					user.setPassword(new String().valueOf(tf_password.getPassword()));
				}
				try {
					client = new Socket("localhost", 8019);
					client.setKeepAlive(true);
					new Thread(() -> {
						SocketManager.sendObject(client, user);
						//while (true) {
						// 如果没有登陆成功
						if (!loginState) {
							if (!client.isClosed()) {
								// 从具有空字符的char数组中转化的字符串需要要将空字符剪切，因为这里自己写的获取服务端方法中的字符是从具有空字符转化来的
								if (SocketManager.readText(client).trim().equals("true")) {
									addLoginState(GameConstant.PANEL_IMAGE_LOGIN_SUCCESS);
									try {
										Thread.sleep(1500);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
									// 关闭登陆窗体
									mainClient.dispose();
									// 启动游戏从窗体
									new GagaGame();
								} else
									addLoginState(GameConstant.PANEL_IMAGE_LOGIN_FAILED);
							}
						}
						//}
					}).start();
				}  catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		});
		loginBt_Panel.setOpaque(false);
		loginBt_Panel.add(jbutton);
		// panel中的控件必须有设置Bounds的如果其他控件没有要在Panel上设置 否则无法显示
		loginBt_Panel.setBounds(GameConstant.WINDOW_WIDTH - 690, GameConstant.WINDOW_HEIGHT - 235, 167, 100);

		getLayeredPane().add(loginBt_Panel);
	}

	/**
	 * 添加登陆账号密码控件
	 */
	private void addLoginTextBox() {
		// 添加提示用户输入规则
		ta_tips = new JTextArea();
		// 创建控件存放面板
		JPanel loginText_Panel = new JPanel();
		// 设置面板的布局方式
		loginText_Panel.setLayout(null);
		// 将面板设置为透明
		loginText_Panel.setOpaque(false);
		// 设置面板显示的位置，如果面板设置了Bounds 则处于该面板上的其他控件均要设置Bounds
		loginText_Panel.setBounds(GameConstant.WINDOW_WIDTH / 2 - 200, GameConstant.WINDOW_HEIGHT - 220, 150, 300);
		// 创建一个文本区域 提示用户账号或密码输入
		JTextArea ta_userName = new JTextArea();
		JTextArea ta_password = new JTextArea();
		// 设置文本区域的显示内容
		ta_userName.setText("账号：");
		ta_password.setText("密码：");
		// 设置文本控件不可被选中
		ta_userName.setFocusable(false);
		ta_password.setFocusable(false);
		// 设置文本控件不可编辑
		ta_userName.setEditable(false);
		ta_password.setEditable(false);
		// 设置文本控件的位置和大小
		ta_userName.setBounds(0, 0, 50, 20);
		ta_password.setBounds(0, 31, 50, 20);
		// 实例化账号和密码输入框
		tf_userName = new JTextField(9);
		tf_password = new JPasswordField(9);
		// 为输入框添加正则控制
		tf_userName.setDocument(new MyDocument("[_0-9a-z]+"));
		tf_password.setDocument(new MyDocument("[_0-9a-z]+"));
		// 设置字体
		ta_userName.setFont(new Font("", Font.BOLD, 14));
		ta_password.setFont(new Font("", Font.BOLD, 14));
		tf_userName.setBounds(35, 0, 200, 20);
		tf_password.setBounds(35, 31, 200, 20);
		// 去除边框
		tf_userName.setBorder(null);
		tf_password.setBorder(null);
		tf_userName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ta_tips.setVisible(false);
			}
		});
		tf_password.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ta_tips.setVisible(false);
			}
		});
		ta_tips.setEditable(false);
		ta_tips.setVisible(false);
		ta_tips.setText("请输入英文和字母!");
		ta_tips.setBounds(0, 61, 200, 25);
		// 将控件添加至面板中
		loginText_Panel.add(ta_userName);
		loginText_Panel.add(ta_password);
		loginText_Panel.add(tf_userName);
		loginText_Panel.add(tf_password);
		loginText_Panel.add(ta_tips);
		// 将面板添加至JFrame的层中
		this.getLayeredPane().add(loginText_Panel);
	}

	/**
	 * 添加登陆界面右上角关闭按钮
	 */
	private void addCloseButton() {
		JPanel closePanel = new JPanel();
		closePanel.setBounds(GameConstant.WINDOW_WIDTH - 40, -10, 50, 50);
		JButton bt_close = WindowTool.getImageButton(new ImageIcon(GameConstant.BT_IMAGE_EXIT1), new ImageIcon(GameConstant.BT_IMAGE_EXIT2));
		// 当鼠标放移动至退出按钮时改变按钮的图片
		bt_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bt_close.setIcon(new ImageIcon(LoginClient.class.getResource("/com/zwk/images/bt_close3.png")));
				// 加载提示框
				addLoginState(GameConstant.PANEL_IMAGE_EXIT);
			}
		});
		closePanel.add(bt_close);
		closePanel.setOpaque(false);
		this.getLayeredPane().add(closePanel);
	}

	/**
	 * 创建登陆结果提示框
	 *
	 * @param panelImagePath 提示框的图片路径
	 */
	private void addLoginState(String panelImagePath) {
		// JPanel存放背景图片
		ImageIcon backImage = new ImageIcon(panelImagePath);
		JPanel jPanel = WindowTool.getImagePromptPanel(backImage);
		// 存放确认和取消的控件
		JPanel jPanel2 = new JPanel();
		jPanel2.setOpaque(false);
		jPanel2.setLayout(null);
		jPanel2.setBounds((GameConstant.WINDOW_WIDTH - GameConstant.PROMPT_BOX_WIDTH) / 2,
				(GameConstant.WINDOW_HEIGHT - GameConstant.PROMPT_BOX_HEIGHT) / 2, GameConstant.PROMPT_BOX_WIDTH,
				GameConstant.PROMPT_BOX_HEIGHT);
		// 如果不是登陆成功提示框，添加确认按钮
		if (!panelImagePath.equals(GameConstant.PANEL_IMAGE_LOGIN_SUCCESS)) {
			JButton bt_close_yes = WindowTool.getImageButton(
					new ImageIcon(GameConstant.BT_IMAGE_EXIT_YES1), new ImageIcon(GameConstant.BT_IMAGE_EXIT_YES2));
			bt_close_yes.setBounds(110, GameConstant.PROMPT_BOX_HEIGHT - 30 - 20, 50, 30);
			bt_close_yes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// 登陆失败时，点击确认按钮，隐藏提示框
					if (panelImagePath.equals(GameConstant.PANEL_IMAGE_LOGIN_FAILED)) {
						// 隐藏提示框
						jPanel.setVisible(false);
						jPanel2.setVisible(false);
					} else {
						try {
							// 退出时关闭socket连接
							if (client != null) {
								if (!client.isClosed()) {
									client.close();
								}
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						// 退出程序
						System.exit(0);
					}
				}
			});
			jPanel2.add(bt_close_yes);
		}
		// 如果是点击右上角退出按钮显示取消按钮
		if (panelImagePath.equals(GameConstant.PANEL_IMAGE_EXIT)) {
			JButton bt_close_no = WindowTool.getImageButton(new ImageIcon(GameConstant.BT_IMAGE_EXIT_NO1),
					new ImageIcon(GameConstant.BT_IMAGE_EXIT_NO2));
			bt_close_no.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					jPanel.setVisible(false);
					jPanel2.setVisible(false);
				}
			});
			// getIconHeight()-30-20 第一个-控件宽度第二个-边距

			bt_close_no.setBounds(GameConstant.PROMPT_BOX_WIDTH - 50 - 110, GameConstant.PROMPT_BOX_HEIGHT - 30 - 20, 50, 30);

			jPanel2.add(bt_close_no);
		}
		// 放入控件的先后顺序先放顶层显示再放底层显示
		this.getLayeredPane().add(jPanel2);
		this.getLayeredPane().add(jPanel);

	}

	/**
	 * 添加注册和修改密码按钮
	 */
	private void addRegisterButton() {
		JPanel panel_Register = new JPanel();
		panel_Register.setOpaque(false);
		panel_Register.setLayout(null);
		panel_Register.setBounds(GameConstant.WINDOW_WIDTH / 2 - 200, GameConstant.WINDOW_HEIGHT - 135, 400, 30);
		JButton bt_Register = WindowTool.getTransparentButton("注册账号");

		bt_Register.setBounds(0, 0, 150, 30);
		bt_Register.setFont(new Font("", Font.ITALIC, 16));
		panel_Register.add(bt_Register);
		// 添加注册按钮点击事件，打开网页注册账号
		bt_Register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				WindowTool.browse(GameConstant.URL_REGISTER);
			}
		});
		JButton bt_UpdatePwd = WindowTool.getTransparentButton("修改密码");

		// 添加修改密码按钮点击事件，打开网页修改密码
		bt_UpdatePwd.setBounds(160, 0, 150, 30);
		bt_UpdatePwd.setFont(new Font("", Font.ITALIC, 16));
		// 添加修改密码按钮点击事件，打开网页修改密码
		bt_UpdatePwd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				WindowTool.browse(GameConstant.URL_UPDATE_PWD);
			}
		});
		panel_Register.add(bt_UpdatePwd);
		this.getLayeredPane().add(panel_Register);
	}
}