package com.zwk.tool;

import com.zwk.client.LoginClient;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

public class MonsterConstant {
	/*********************************************怪物动画*****************************************************************************/
	// 金角大王
	public static Map<Integer, ImageIcon> MONSTER_IMAGE_KINGSILVERHORN;
	static {
		MONSTER_IMAGE_KINGSILVERHORN=new HashMap<>();
		// 1右侧停止
		MONSTER_IMAGE_KINGSILVERHORN.put(1,new ImageIcon(LoginClient.class.getResource("/com/zwk/images/monster/kingsilverhorn_stop.gif").getPath()));
		// 2左侧停止
		MONSTER_IMAGE_KINGSILVERHORN.put(2,new ImageIcon(LoginClient.class.getResource("/com/zwk/images/monster/kingsilverhorn_stop_left.gif").getPath()));
		// 3向右走
		MONSTER_IMAGE_KINGSILVERHORN.put(3,new ImageIcon(LoginClient.class.getResource("/com/zwk/images/monster/kingsilverhorn_walk.gif").getPath()));
		// 4向左走
		MONSTER_IMAGE_KINGSILVERHORN.put(4,new ImageIcon(LoginClient.class.getResource("/com/zwk/images/monster/kingsilverhorn_walk_left.gif").getPath()));
		// 右侧攻击
		MONSTER_IMAGE_KINGSILVERHORN.put(5,new ImageIcon(LoginClient.class.getResource("/com/zwk/images/monster/kingsilverhorn_attack.gif").getPath()));
		// 左侧攻击
		MONSTER_IMAGE_KINGSILVERHORN.put(6,new ImageIcon(LoginClient.class.getResource("/com/zwk/images/monster/kingsilverhorn_attack_left.gif").getPath()));
		// 受击
		MONSTER_IMAGE_KINGSILVERHORN.put(7,new ImageIcon(LoginClient.class.getResource("/com/zwk/images/monster/kingsilverhorn_hit.gif").getPath()));
		// 死亡
		MONSTER_IMAGE_KINGSILVERHORN.put(8,new ImageIcon(LoginClient.class.getResource("/com/zwk/images/monster/kingsilverhorn_death.gif").getPath()));
		MONSTER_IMAGE_KINGSILVERHORN.put(9,new ImageIcon(LoginClient.class.getResource("/com/zwk/images/monster/kingsilverhorn_death.gif").getPath()));

	}
}
