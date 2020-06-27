package com.zwk.game;

import com.zwk.tool.GameConstant;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public abstract class Biology {
	protected JFrame mainJFrame = null;
	protected boolean bU = false, bD = false, bL = false, bR = false;
	protected volatile Direction direction = Direction.STOP;
	protected JLabel lb_biology = null;
	protected volatile Integer x, y, width, height, speed, hp, injuryValue;
	private static int grade = 1;
	public JTextArea hitValueJText;

	public Biology(JFrame mainJFrame) {
		hitValueJText = new JTextArea();
		hitValueJText.setFont(new Font("", Font.BOLD, 16));
		hitValueJText.setBounds(200, 300, 50, 25);
		// 设置不可聚焦
		hitValueJText.setFocusable(false);
		// 设置不可编辑
		hitValueJText.setEditable(false);
		// 设置透明
		hitValueJText.setOpaque(false);
		// 设置前景色 即改变字体颜色
		hitValueJText.setForeground(Color.MAGENTA);
		hitValueJText.setVisible(false);
		this.mainJFrame = mainJFrame;
		this.mainJFrame.add(hitValueJText);
	}
	// 显示伤害字体


	public abstract void paint();

	public void setHitValueJText( int value) {
		new Thread(() -> {
			if (hitValueJText != null) {
				hitValueJText.setText(value+"");
				hitValueJText.setLocation(x, y-40);
				hitValueJText.repaint();
				hitValueJText.setVisible(true);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {

				}
				hitValueJText.setVisible(false);

			}
		}).start();

	}

	public void move() {
		//	direction();
//		switch (direction) {
//			case LEFT:
//				x -= speed;
//				break;
//			case RIGHT:
//				x += speed;
//				break;
//			case UP:
//				y -= speed;
//				break;
//			case DOWN:
//				y += speed;
//				break;
//			case LEFT_DOWN:
//				x -= speed;
//				y += speed;
//				break;
//			case LEFT_UP:
//				x -= speed;
//				y -= speed;
//				break;
//			case RIGHT_DOWN:
//				x += speed;
//				y += speed;
//				break;
//			case RIGHT_UP:
//				x += speed;
//				y -= speed;
//				break;
//			default:
//				break;
//		}
	}

	protected void direction() {
		if (!bU && !bD && bL && !bR) {
			direction = Direction.LEFT;
		} else if (!bU && !bD && !bL && bR) {
			direction = Direction.RIGHT;
		} else if (bU && !bD && !bL && !bR) {
			direction = Direction.UP;
		} else if (!bU && bD && !bL && !bR) {
			direction = Direction.DOWN;
		} else if (bU && !bD && bL && !bR) {
			direction = Direction.LEFT_UP;
		} else if (!bU && bD && bL && !bR) {
			direction = Direction.LEFT_DOWN;
		} else if (bU && !bD && !bL && bR) {
			direction = Direction.RIGHT_UP;
		} else if (!bU && bD && !bL && bR) {
			direction = Direction.RIGHT_DOWN;
		} else {
			direction = Direction.STOP;
		}
		// 防止出界
		if (x < 0) {
			x = 0;
			direction = Direction.STOP;
		} else if (x > GameConstant.GAME_WINDOW_WIDTH) {
			x = GameConstant.GAME_WINDOW_WIDTH - width;
			direction = Direction.STOP;
		} else if (y < width) {
			y = 0 + width;
			direction = Direction.STOP;
		} else if (y > GameConstant.GAME_WINDOW_HEIGHT) {
			y = GameConstant.GAME_WINDOW_HEIGHT - width;
			direction = Direction.STOP;
		}
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getHp() {
		return hp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}

	public Integer getInjuryValue() {
		return injuryValue;
	}

	public void setInjuryValue(Integer injuryValue) {
		this.injuryValue = injuryValue;
	}

	public static int getGrade() {
		return grade;
	}

	public static void setGrade(int grade) {
		Biology.grade = grade;
	}
}