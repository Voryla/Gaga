package com.zwk.game;

import com.zwk.client.LoginClient;
import com.zwk.tool.GameConstant;
import com.zwk.tool.SwordManConstant;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class SkillBar {
	// 获取主窗体
	private JFrame mainJFrame;
	// 左边消耗品栏
	private JPanel jp_consumables = new JPanel();
	// 右侧技能栏键位提醒
	private JPanel jp_skillKeyTips = new JPanel();
	// 左下角等级栏
	private JPanel jp_playerGrade = new JPanel();
	// 左下角显示lv图片
	private JLabel lb_gradeImage = new JLabel();
	// 等级的十位
	private ImageIcon imageIcon_gradeDecade = GameConstant.IMAGE_GRADE_0;
	private JLabel lb_gradeDecade = new JLabel();
	// 等级的个位
	private ImageIcon imageIcon_gradeBit = GameConstant.IMAGE_GRADE_1;
	private JLabel lb_gradeBit = new JLabel();
	// 技能类
	private SwordSkillBar jp_swordMainSkill;
	// 整个下方栏
	JPanel jp_skillBar = new JPanel();
	// 疲劳值控制
	FatigueValue fatigueValue = new FatigueValue();
	// 绘制经验条
	ExperienceBar experienceBar=new ExperienceBar();
	public SkillBar(JFrame jFrame) {
		// 获取主窗体
		this.mainJFrame = jFrame;
		jp_swordMainSkill = new SwordSkillBar(jFrame);
		// 添加疲劳值至主窗体
		mainJFrame.getLayeredPane().add(fatigueValue);
		// 将经验条绘制至主窗体
		mainJFrame.getLayeredPane().add(experienceBar);
		// 修该人物等级panel属性
		jp_playerGrade.setLayout(null);
		jp_playerGrade.setOpaque(false);
		jp_playerGrade.setBounds(93, 538, 100, 80);
		lb_gradeImage.setIcon(GameConstant.IMAGE_GRADE);
		lb_gradeImage.setBounds(0, 0, 30, 30);
		jp_playerGrade.add(lb_gradeImage);
		lb_gradeDecade.setBounds(18, 0, 30, 30);
		lb_gradeDecade.setIcon(imageIcon_gradeDecade);
		jp_playerGrade.add(lb_gradeDecade);
		lb_gradeBit.setBounds(28, 0, 30, 30);
		lb_gradeBit.setIcon(imageIcon_gradeBit);
		jp_playerGrade.add(lb_gradeBit);
		// 将人物等级panel加入至主窗体中
		mainJFrame.getLayeredPane().add(jp_playerGrade);

		jp_skillKeyTips.setLayout(null);
		jp_skillKeyTips.setOpaque(false);
		jp_skillKeyTips.setBounds(450, 519, 180, 30);
		mainJFrame.getLayeredPane().add(jp_skillKeyTips);
		mainJFrame.getLayeredPane().add(jp_swordMainSkill);
		// 整个下方栏
		jp_skillBar.setOpaque(false);
		jp_skillBar.setLayout(null);
		jp_skillBar.setBounds(0, GameConstant.GAME_WINDOW_HEIGHT - 128, GameConstant.GAME_WINDOW_WIDTH, GameConstant.GAME_WINDOW_HEIGHT);

		jp_consumables.setLayout(null);
		jp_consumables.setOpaque(false);
		jp_consumables.setBounds(155, 519, 180, 30);
	}

	JLabel jL_skillBar;
	JLabel jL_hp;
	JLabel jL_mp;
	JLabel lb_consumable1;
	JLabel lb_consumable2;

	public void paint() {
		jL_skillBar = new JLabel(GameConstant.SKILL_BAR);
		jL_skillBar.setBounds((GameConstant.GAME_WINDOW_WIDTH - 655) / 2, 0, GameConstant.GAME_SKILL_BAR_WIDTH, GameConstant.GAME_SKILL_BAR_HEIGHT);

		jL_hp = new JLabel(GameConstant.HP_IMAGE);
		jL_hp.setBounds(63, -7, 100, 100);
		jL_mp = new JLabel(GameConstant.MP_IMAGE);
		jL_mp.setBounds(GameConstant.GAME_SKILL_BAR_WIDTH - 20, -7, 100, 100);

		// 左下角等级panel

		// 左边消耗品使用键位提示
		paintKeyTips();
		// 右边技能使用键位提示
		paintSkillKeyTips();
		paintHp();
		// 左边消耗品栏
		// 消耗品1
		lb_consumable1 = new JLabel(GameConstant.IMAGE_CONSUMABLE_1);
		lb_consumable1.setBounds(0, 1, GameConstant.CONSUMABLE_WIDTH, GameConstant.CONSUMABLE_HEIGHT);
		jp_consumables.add(lb_consumable1);
		lb_consumable2 = new JLabel(GameConstant.IMAGE_CONSUMABLE_2);
		lb_consumable2.setBounds(30, 1, GameConstant.CONSUMABLE_WIDTH, GameConstant.CONSUMABLE_HEIGHT);
		jp_consumables.add(lb_consumable2);
		JLabel lb_consumable3 = new JLabel(GameConstant.IMAGE_CONSUMABLE_3);
		lb_consumable3.setBounds(60, 1, GameConstant.CONSUMABLE_WIDTH, GameConstant.CONSUMABLE_HEIGHT);
		jp_consumables.add(lb_consumable3);
		// 将hp和mp添加到技能底板
		jp_skillBar.add(jL_hp);
		jp_skillBar.add(jL_mp);
		jp_skillBar.add(jL_skillBar);
		// 将消耗品栏和技能底板
		mainJFrame.getLayeredPane().add(jp_consumables);
		mainJFrame.getLayeredPane().add(jp_skillBar);
		new Thread(() -> {
			while (true) {
				// 画等级
				paintGrade();
				// 画疲劳值
				fatigueValue.paint();
				experienceBar.paint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void repaint() {
		jL_skillBar.repaint();
		jL_hp.repaint();
		jL_mp.repaint();
		lb_consumable1.repaint();
		lb_consumable2.repaint();
	}

	private void paintKeyTips() {
		// 消耗品按键提示1
		JLabel lb_consumable1KeyTips = new JLabel(GameConstant.IMAGE_CONSUMABLE_1_key);
		lb_consumable1KeyTips.setBounds(17, 16, 15, 15);
		// 消耗品按键提示2
		JLabel lb_consumable2KeyTips = new JLabel(GameConstant.IMAGE_CONSUMABLE_2_key);
		lb_consumable2KeyTips.setBounds(45, 16, 15, 15);
		// 消耗品按键提示3
		JLabel lb_consumable3KeyTips = new JLabel(GameConstant.IMAGE_CONSUMABLE_3_key);
		lb_consumable3KeyTips.setBounds(75, 16, 15, 15);
		// 消耗品按键提示4
		JLabel lb_consumable4KeyTips = new JLabel(GameConstant.IMAGE_CONSUMABLE_4_key);
		lb_consumable4KeyTips.setBounds(105, 16, 15, 15);
		// 消耗品按键提示5
		JLabel lb_consumable5KeyTips = new JLabel(GameConstant.IMAGE_CONSUMABLE_5_key);
		lb_consumable5KeyTips.setBounds(135, 16, 15, 15);
		// 消耗品按键提示6
		JLabel lb_consumable6KeyTips = new JLabel(GameConstant.IMAGE_CONSUMABLE_6_key);
		lb_consumable6KeyTips.setBounds(165, 16, 15, 15);
		jp_consumables.add(lb_consumable1KeyTips);
		jp_consumables.add(lb_consumable2KeyTips);
		jp_consumables.add(lb_consumable3KeyTips);
		jp_consumables.add(lb_consumable4KeyTips);
		jp_consumables.add(lb_consumable5KeyTips);
		jp_consumables.add(lb_consumable6KeyTips);
	}

	private void paintSkillKeyTips() {
		// 技能按键提示1
		JLabel lb_skillKeyTips_A = new JLabel(GameConstant.IMAGE_KEY_ICON_A);
		lb_skillKeyTips_A.setBounds(17, 16, 15, 15);
		// 技能按键提示2
		JLabel lb_skillKeyTips_S = new JLabel(GameConstant.IMAGE_KEY_ICON_S);
		lb_skillKeyTips_S.setBounds(45, 16, 15, 15);
		// 技能按键提示3
		JLabel lb_skillKeyTips_D = new JLabel(GameConstant.IMAGE_KEY_ICON_D);
		lb_skillKeyTips_D.setBounds(75, 16, 15, 15);
		// 技能按键提示4
		JLabel lb_skillKeyTips_F = new JLabel(GameConstant.IMAGE_KEY_ICON_F);
		lb_skillKeyTips_F.setBounds(105, 16, 15, 15);
		// 技能按键提示5
		JLabel lb_skillKeyTips_G = new JLabel(GameConstant.IMAGE_KEY_ICON_G);
		lb_skillKeyTips_G.setBounds(135, 16, 15, 15);
		// 技能按键提示6
		JLabel lb_skillKeyTips_H = new JLabel(GameConstant.IMAGE_KEY_ICON_H);
		lb_skillKeyTips_H.setBounds(165, 16, 15, 15);
		jp_skillKeyTips.add(lb_skillKeyTips_A);
		jp_skillKeyTips.add(lb_skillKeyTips_S);
		jp_skillKeyTips.add(lb_skillKeyTips_D);
		jp_skillKeyTips.add(lb_skillKeyTips_F);
		jp_skillKeyTips.add(lb_skillKeyTips_G);
		jp_skillKeyTips.add(lb_skillKeyTips_H);
	}

	// 绘制等级
	private void paintGrade() {
		int grade = GamePlayer.getGrade();
		switch (grade / 10) {
			case 0:
				imageIcon_gradeDecade = GameConstant.IMAGE_GRADE_0;
				break;
			case 1:
				imageIcon_gradeDecade = GameConstant.IMAGE_GRADE_1;
				break;
			case 2:
				imageIcon_gradeDecade = GameConstant.IMAGE_GRADE_2;
				break;
			case 3:
				imageIcon_gradeDecade = GameConstant.IMAGE_GRADE_3;
				break;
			case 4:
				imageIcon_gradeDecade = GameConstant.IMAGE_GRADE_4;
				break;
			case 5:
				imageIcon_gradeDecade = GameConstant.IMAGE_GRADE_5;
				break;
			case 6:
				imageIcon_gradeDecade = GameConstant.IMAGE_GRADE_6;
				break;
		}

		switch (grade % 10) {
			case 0:
				imageIcon_gradeBit = GameConstant.IMAGE_GRADE_0;
				break;
			case 1:
				imageIcon_gradeBit = GameConstant.IMAGE_GRADE_1;
				break;
			case 2:
				imageIcon_gradeBit = GameConstant.IMAGE_GRADE_2;
				break;
			case 3:
				imageIcon_gradeBit = GameConstant.IMAGE_GRADE_3;
				break;
			case 4:
				imageIcon_gradeBit = GameConstant.IMAGE_GRADE_4;
				break;
			case 5:
				imageIcon_gradeBit = GameConstant.IMAGE_GRADE_5;
				break;
			case 6:
				imageIcon_gradeBit = GameConstant.IMAGE_GRADE_6;
				break;
			case 7:
				imageIcon_gradeBit = GameConstant.IMAGE_GRADE_7;
				break;
			case 8:
				imageIcon_gradeBit = GameConstant.IMAGE_GRADE_8;
				break;
			case 9:
				imageIcon_gradeBit = GameConstant.IMAGE_GRADE_9;
				break;
		}
		lb_gradeDecade.setIcon(imageIcon_gradeDecade);
		lb_gradeBit.setIcon(imageIcon_gradeBit);
	}

	// 绘制疲劳值
	private class FatigueValue extends JProgressBar {
		public FatigueValue() {
			this.setMaximum(156);
			this.setMinimum(0);
			this.setBackground(Color.BLACK);
			this.setForeground(new Color(30, 144, 255));
			this.setBounds(350, 515, 85, 5);
		}

		public void paint() {
			this.setValue(SwordManConstant.fatigueValues);
		}
	}

	/**
	 * 怪物死亡人物增加经验值，这里判断经验值是否满足该等级升级所用如果满足那么等级+1 当前值清零，并重新更改该等级升级的经验
	 */
	private class ExperienceBar extends JProgressBar {
		public ExperienceBar() {
			int grade = GamePlayer.getGrade();
			this.setMaximum(grade * 1000);
			this.setMinimum(0);
			this.setBackground(Color.black);
			this.setForeground(new Color(0, 255, 0));
			this.setBounds(142,552,499,5);
		}
		public void paint(){
			this.setValue(0);
		}
	}

	private void paintHp(){
		// 更新血条
		new Thread(() -> {
			while(true) {
				try {
					Thread.sleep(200);
				}catch (InterruptedException e){
					e.printStackTrace();
				}
				int hp=SwordManConstant.hp;
				double s=hp/SwordManConstant.ALL_HP;
				Icon icon = new JLabel(new ImageIcon(LoginClient.class.getResource("/com/zwk/images/gameUI/hp" + ((int)(s*10/1)) * 10 + ".png").getPath().replace("%20", " "))).getIcon();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				jL_hp.setIcon(icon);
			}
		}).start();
	}
}