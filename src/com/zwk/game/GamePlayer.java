package com.zwk.game;

import com.zwk.tool.GameConstant;
import com.zwk.tool.SwordManConstant;
import com.zwk.tool.SwordManSkillConstant;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 玩家
 */
public class GamePlayer extends Biology {
	// 创建随机数，生成人物攻击范围内的伤害值
	private Random random = new Random();
	// 标志run状态
	volatile boolean flag_RUN = false;
	// 标志人物左右动画播放 false 左，true 右
	public volatile static boolean flag_Left_Right = false;
	// 设置人物移动显示的动画
	private ImageIcon moveImage;
	// 人物技能及攻击动画
	private ImageIcon skillImage;
	public SwordsEnum swordsEnum = SwordsEnum.whiteSword;
	// 剑类
	private Sword lb_getSword;
	// 称号
	private PlayerTitle lb_playerTitle;

	public enum PlayerState {walk, attack, skill, hit, death}

	public volatile static PlayerState playerState = PlayerState.walk;
	// 技能动画
	private DoSkill lb_doSkill;
	private int skillKeyCode;

	public GamePlayer(JFrame mainJFrame) {
		super(mainJFrame);
		injuryValue = 1500;
		lb_doSkill = new DoSkill();
		mainJFrame.getLayeredPane().add(lb_doSkill);
		// 初始化等级
		setGrade(1);
		lb_getSword = new Sword();
		mainJFrame.getLayeredPane().add(lb_getSword);
		lb_playerTitle = new PlayerTitle();
		mainJFrame.getLayeredPane().add(lb_playerTitle);
		super.lb_biology = new JLabel();
		lb_biology.setBounds(0, 0, SwordManConstant.SWORD_MAN_WIDTH, SwordManConstant.SWORD_MAN_HEIGHT);
		lb_biology.setIcon(SwordManConstant.SWORD_MAN_HOUSE_STAY_LEFT_IMAGE);
		mainJFrame.getLayeredPane().add(lb_biology);
		addKeyListenerForDirection();
	}


	@Override
	public void paint() {
		new Thread(() -> {
			while (true) {
				synchronized (this) {
					// 绘制称号动画位置
					lb_playerTitle.paintTitle(TitleEnum.TITLE_GOTHIC);
					// 将人物xy坐标赋予父类
					super.x = SwordManConstant.swordManX;
					super.y = SwordManConstant.swordManX;
					// 刷新频率
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// 检测怪物是否死亡
					if (GagaGame.monster.hp < 1) {
						GagaGame.monster.monsterStatus = Monster.MonsterStatus.death;
					}
					// 检测自身是否死亡
					if (playerState.equals(PlayerState.death)) {
						lb_biology.setIcon(SwordManSkillConstant.SWORD_MAN_DEATH);
						continue;
					}
					// 检测自身是否受击，播发受击动画
					if (playerState.equals(PlayerState.hit)) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						lb_biology.setIcon(SwordManSkillConstant.SWORD_MAN_HIT);
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						playerState = PlayerState.walk;
					}
					// 根据方向移动坐标
					move();
					// 人物为普通攻击状态
					if (playerState.equals(PlayerState.attack)) {
						// 因为普攻动画剑和人物是一体的，所以先将walk状态的剑隐藏
						lb_getSword.setVisible(false);
						// 设置释放普攻时人物的动画
						lb_biology.setIcon(skillImage);
						// 设置释放普攻时人物的位置
						lb_biology.setBounds(SwordManConstant.swordManX - 80, SwordManConstant.swordManY - 10, 294, SwordManConstant.SWORD_MAN_HEIGHT + 40);
						// 检测是否在house中
						if (!GagaGame.houseVisible) {
							// 检测怪物是否死亡
							if (GagaGame.monster.monsterStatus != Monster.MonsterStatus.death) {
								// 检测是否与怪物碰撞
								if (collisionDetection()) {
									// 设置怪物的状态为受击
									GagaGame.monster.monsterStatus = Monster.MonsterStatus.hit;
									// 检测怪物的是否死亡
									if (GagaGame.monster.hp < 1) {
										GagaGame.monster.monsterStatus = Monster.MonsterStatus.death;
										System.out.println("怪物以死1");
										continue;
									} else {
										// 设置攻击数值
										int number = random.nextInt(injuryValue - injuryValue / 2) + injuryValue / 2;
										System.out.println("atta");
										// 减少怪物血量
										GagaGame.monster.hp -= number;
										// 播放怪物自身受到攻击的伤害量
										GagaGame.monster.setHitValueJText(number);
									}
								} else {
									// 普攻时没有碰撞怪物，怪物的状态应为walk
									GagaGame.monster.monsterStatus = Monster.MonsterStatus.walk;
								}
							}
						}
						// 延时使人物普攻动画播放完毕
						try {
							Thread.sleep(600);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						// 普攻释放完毕后，人物的状态应该重置到walk状态
						playerState = PlayerState.walk;
					}
					// 人物所处释放技能状态
					if (playerState.equals(PlayerState.skill)) {
						// 标志技能可以释放
						boolean manCD = false;
						// 获取技能cd
						switch (skillKeyCode) {
							case KeyEvent.VK_A: {
								break;
							}
							case KeyEvent.VK_S: {
								if (!DoSkill.getReadCDS()) {
									playerState = PlayerState.walk;
									continue;
								}
								break;
							}
							case KeyEvent.VK_D: {
								break;
							}
						}
						// 确保人物状态不处于house中
						if (!GagaGame.houseVisible) {
							// 确保怪物没有死亡
							if (GagaGame.monster.monsterStatus != Monster.MonsterStatus.death) {
								// 碰撞检测
								if (collisionDetection()) {
									// 将怪物状态设置为受技能击中状态
									GagaGame.monster.monsterStatus = Monster.MonsterStatus.skillHit;
									// 判断怪物血量
									if (GagaGame.monster.hp < 1) {
										// 如果hp<1该怪物死亡
										GagaGame.monster.monsterStatus = Monster.MonsterStatus.death;
										// 跳出本次循环
										continue;
									} else {
										int number = random.nextInt(injuryValue - injuryValue / 2) + injuryValue / 2;
										for (int i = 0; i < 4; i++) {
											if (!DoSkill.getReadCDS()) {
												playerState = PlayerState.walk;
												continue;
											}
										}
										GagaGame.monster.hp -= number * 2;
										System.out.println("skill");
										GagaGame.monster.setHitValueJText(number * 2);
									}
								} else {
									GagaGame.monster.monsterStatus = Monster.MonsterStatus.walk;
								}
							}
						}
						switch (skillKeyCode) {
							case KeyEvent.VK_A: {
								manCD = DoSkill.getReadCDA();
								break;
							}
							case KeyEvent.VK_S: {
								manCD = DoSkill.getReadCDS();
								break;
							}
							case KeyEvent.VK_D: {
								manCD = DoSkill.getReadCDD();
								break;
							}
						}
						if (manCD) {
							lb_biology.setIcon(skillImage);
							manCD = false;
						} else {
							lb_biology.setIcon(moveImage);
							lb_getSword.setVisible(true);
						}
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						lb_doSkill.paintSkill(flag_Left_Right, skillKeyCode);
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						lb_biology.setBounds(SwordManConstant.swordManX, SwordManConstant.swordManY, SwordManConstant.SWORD_MAN_WIDTH, SwordManConstant.SWORD_MAN_HEIGHT);
						playerState = PlayerState.walk;
						lb_getSword.setVisible(true);
					}
					if (playerState.equals(PlayerState.walk)) {

						GagaGame.monster.monsterStatus = Monster.MonsterStatus.walk;

						if (direction == Direction.STOP) {
							moveImage = flag_Left_Right ? SwordManConstant.SWORD_MAN_HOUSE_STAY_RIGHT_IMAGE : SwordManConstant.SWORD_MAN_HOUSE_STAY_LEFT_IMAGE;

						}
						// 当人物状态为Run时再根据左右标识更换动画 92 55
						else if (flag_RUN) {
							moveImage = flag_Left_Right ? SwordManConstant.SWORD_MAN_RUN_RIGHT_IMAGE : SwordManConstant.SWORD_MAN_RUN_LEFT_IMAGE;

						}
						// 此时人物状态为走动，根据左右标识更换动画
						else {
							moveImage = flag_Left_Right ? SwordManConstant.SWORD_MAN_WALK_RIGHT_IMAGE : SwordManConstant.SWORD_MAN_WALK_LEFT_IMAGE;
						}
						// 绘制人物

						lb_getSword.setVisible(true);
						lb_getSword.paintSword(flag_Left_Right, flag_RUN, direction, this.swordsEnum);
						lb_biology.setIcon(moveImage);
						lb_biology.setBounds(SwordManConstant.swordManX, SwordManConstant.swordManY, SwordManConstant.SWORD_MAN_WIDTH, SwordManConstant.SWORD_MAN_HEIGHT);
					}
				}
			}
		}).start();
	}

	// 添加键盘监听
	private void addKeyListenerForDirection() {
		mainJFrame.addKeyListener(new KeyAdapter() {
			// 每次进入获取当前时间用于判断是否双击左或者右，这里需要为左右各创建一个，否则按下左键即刻按下右键会导致出发Run状态
			Long timeRight = System.currentTimeMillis();
			Long timeLeft = System.currentTimeMillis();
			int timeInterval = 200;
			Long timeAttack = System.currentTimeMillis();
			Long timeSkillS = System.currentTimeMillis();

			// 键盘按下事件
			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				skillKeyCode = code;
				switch (code) {
					// 右键
					case KeyEvent.VK_RIGHT: {
						bR = true;
						// 如果已经为Run状态不进行判断 跳出break
						if (direction == Direction.RIGHT_RUN) break;
						// 根据两次按下右键的时间间隔进行判断是否为双击，即跑动状态
						if (System.currentTimeMillis() - timeRight < timeInterval)
							flag_RUN = true;
						else flag_RUN = false;
						break;
					}
					// 左键
					case KeyEvent.VK_LEFT: {
						bL = true;
						// 如果已经为Run状态不进行判断 跳出break
						if (direction == Direction.LEFT_RUN) break;
						// 根据两次按下左键的时间间隔进行判断是否为双击，即跑动状态
						if (System.currentTimeMillis() - timeLeft < timeInterval)
							flag_RUN = true;
						else flag_RUN = false;
						break;
					}

					case KeyEvent.VK_UP:
						bU = true;
						break;
					case KeyEvent.VK_DOWN:
						bD = true;
						break;
					case KeyEvent.VK_X: {
						if(playerState.equals(PlayerState.death))return;
						if (playerState.equals(PlayerState.attack)) return;
						playerState = PlayerState.attack;
						if (System.currentTimeMillis() - timeAttack > 300) {
							skillImage = flag_Left_Right ? SwordManSkillConstant.ATTACK1 : SwordManSkillConstant.ATTACK1_LEFT;

							return;
						}
						if (flag_Left_Right) {
							if (skillImage.equals(SwordManSkillConstant.ATTACK1))
								skillImage = SwordManSkillConstant.ATTACK2;
							else if (skillImage.equals(SwordManSkillConstant.ATTACK2))
								skillImage = SwordManSkillConstant.ATTACK3;
							else {
								skillImage = SwordManSkillConstant.ATTACK1;

							}
						} else {
							if (skillImage.equals(SwordManSkillConstant.ATTACK1_LEFT))
								skillImage = SwordManSkillConstant.ATTACK2_LEFT;
							else if (skillImage.equals(SwordManSkillConstant.ATTACK2_LEFT))
								skillImage = SwordManSkillConstant.ATTACK3_LEFT;
							else
								skillImage = SwordManSkillConstant.ATTACK1_LEFT;
						}
						break;
					}
					case KeyEvent.VK_A: {
						break;
					}
					case KeyEvent.VK_S: {

						if (playerState.equals(PlayerState.skill)) break;

						if (DoSkill.getReadCDS()) {
							System.out.println("s");
							lb_getSword.setVisible(false);
							playerState = PlayerState.skill;
							skillImage = flag_Left_Right ? SwordManSkillConstant.HARD_ATTACK_BLADE_MAN : SwordManSkillConstant.HARD_ATTACK_BLADE_MAN_LEFT;
						}
						break;
					}
					case KeyEvent.VK_D: {

					}
				}
			}

			// 键盘抬起事件
			@Override
			public void keyReleased(KeyEvent e) {
				int code = e.getKeyCode();
				if (code == KeyEvent.VK_RIGHT) {
					// 抬起按键时记录时间，用以按下时判断时间间隔
					timeRight = System.currentTimeMillis();
					bR = false;
					// 当松开按键时 关闭Run状态
					flag_RUN = false;
				}
				if (code == KeyEvent.VK_LEFT) {
					// 抬起按键时记录时间，用以按下时判断时间间隔
					timeLeft = System.currentTimeMillis();
					bL = false;
					// 当松开按键时 关闭Run状态
					flag_RUN = false;
				}
				if (code == KeyEvent.VK_UP) {
					bU = false;
				}
				if (code == KeyEvent.VK_DOWN) {
					bD = false;
				}
				if (code == KeyEvent.VK_X) {
					timeAttack = System.currentTimeMillis();
				}
				if (code == KeyEvent.VK_S) {
					timeSkillS = System.currentTimeMillis();
				}
			}
		});
	}

	// 人物移动
	@Override
	public void move() {
		// 判断方向
		direction();
		// 根据左右判断人物面对的方向
		if (bR)
			flag_Left_Right = true;
		if (bL)
			flag_Left_Right = false;
		// 根据不同的方向更改坐标值
		switch (direction) {
			case RIGHT_RUN: {
				SwordManConstant.swordManX += SwordManConstant.runSpeed;
			}
			break;
			case LEFT_RUN: {
				SwordManConstant.swordManX -= SwordManConstant.runSpeed;
			}
			break;
			case RIGHT: {
				{
					SwordManConstant.swordManX += SwordManConstant.speed;
				}
			}
			break;
			case LEFT: {
				SwordManConstant.swordManX -= SwordManConstant.speed;
			}
			break;

			case UP: {
				SwordManConstant.swordManY -= SwordManConstant.speed;
			}
			break;

			case DOWN: {
				SwordManConstant.swordManY += SwordManConstant.speed;
			}
			break;
			case RIGHT_UP:
				SwordManConstant.swordManY -= SwordManConstant.speed;
				SwordManConstant.swordManX += SwordManConstant.speed;
				break;
			case LEFT_UP:
				SwordManConstant.swordManY -= SwordManConstant.speed;
				SwordManConstant.swordManX -= SwordManConstant.speed;
				break;
			case RIGHT_DOWN:
				SwordManConstant.swordManY += SwordManConstant.speed;
				SwordManConstant.swordManX += SwordManConstant.speed;
				break;
			case LEFT_DOWN:
				SwordManConstant.swordManY += SwordManConstant.speed;
				SwordManConstant.swordManX -= SwordManConstant.speed;
				break;
			case RIGHT_UP_RUN:
				SwordManConstant.swordManY -= SwordManConstant.runSpeed;
				SwordManConstant.swordManX += SwordManConstant.runSpeed;
				break;
			case LEFT_UP_RUN:
				SwordManConstant.swordManY -= SwordManConstant.runSpeed;
				SwordManConstant.swordManX -= SwordManConstant.runSpeed;
				break;
			case RIGHT_DOWN_RUN:
				SwordManConstant.swordManY += SwordManConstant.runSpeed;
				SwordManConstant.swordManX += SwordManConstant.runSpeed;
				break;
			case LEFT_DOWN_RUN:
				SwordManConstant.swordManY += SwordManConstant.runSpeed;
				SwordManConstant.swordManX -= SwordManConstant.runSpeed;
				break;
			case STOP: {

			}
			break;
		}

	}

	// 攻击怪物检测
	private boolean collisionDetection() {
		boolean flag = false;
		int x = SwordManConstant.swordManX + SwordManConstant.SWORD_MAN_WIDTH / 2;
		int y = SwordManConstant.swordManY + SwordManConstant.SWORD_MAN_HEIGHT / 2;
		if (x > GagaGame.monster.x && x < GagaGame.monster.x + GagaGame.monster.width
				&& y > GagaGame.monster.y && y < GagaGame.monster.y + GagaGame.monster.height - 50) {
			flag = true;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			flag = false;
		}
		return flag;
	}

	// 确定人物的方向
	protected void direction() {
		// 防止出界
		if (SwordManConstant.swordManX < GameConstant.BIOLOGY_BORDER_LINE_LEFT) {
			bL = false;
		}
		if (SwordManConstant.swordManX > GameConstant.BIOLOGY_BORDER_LINE_RIGHT) {
			bR = false;
		}
		if (SwordManConstant.swordManY < GameConstant.BIOLOGY_BORDER_LINE_UP) {
			bU = false;
		}
		if (SwordManConstant.swordManY > GameConstant.BIOLOGY_BORDER_LINE_DOWN) {
			bD = false;
		}

		// 确定方向
		if (!bU && !bD && bL && !bR) {
			if (flag_RUN)
				direction = Direction.LEFT_RUN;
			else
				direction = Direction.LEFT;
		} else if (!bU && !bD && !bL && bR) {
			if (flag_RUN)
				direction = Direction.RIGHT_RUN;
			else
				direction = Direction.RIGHT;
		} else if (bU && !bD && !bL && !bR) {
			direction = Direction.UP;
		} else if (!bU && bD && !bL && !bR) {
			direction = Direction.DOWN;
		} else if (bU && !bD && bL && !bR) {
			if (flag_RUN)
				direction = Direction.LEFT_UP_RUN;
			else
				direction = Direction.LEFT_UP;
		} else if (!bU && bD && bL && !bR) {
			if (flag_RUN)
				direction = Direction.LEFT_DOWN_RUN;
			else
				direction = Direction.LEFT_DOWN;
		} else if (bU && !bD && !bL && bR) {
			if (flag_RUN)
				direction = Direction.RIGHT_UP_RUN;
			else
				direction = Direction.RIGHT_UP;
		} else if (!bU && bD && !bL && bR) {
			if (flag_RUN)
				direction = Direction.RIGHT_DOWN_RUN;
			else
				direction = Direction.RIGHT_DOWN;
		} else {
			direction = Direction.STOP;
		}
	}

	// 显示伤害
	@Override
	public void setHitValueJText(int value) {
		new Thread(() -> {
			if (hitValueJText != null) {
				hitValueJText.setText("-" + value + "");
				hitValueJText.setLocation(SwordManConstant.swordManX + 40, SwordManConstant.swordManY);
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
}