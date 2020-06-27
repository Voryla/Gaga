package com.zwk.game;

public enum Direction {
	UP,DOWN,LEFT,RIGHT,LEFT_UP,LEFT_DOWN,RIGHT_UP,RIGHT_DOWN,STOP,LEFT_RUN,RIGHT_RUN,RIGHT_UP_RUN,RIGHT_DOWN_RUN,LEFT_UP_RUN,LEFT_DOWN_RUN;

	@Override
	public String toString() {
		String dir;
		switch (this){
			case RIGHT:
				dir="right";
				break;
			case LEFT:
				dir = "left";
				break;
			case UP:
				dir="up";
				break;
			case DOWN:
				dir="down";
				break;
			case LEFT_RUN:
				dir="leftRun";
				break;
			case RIGHT_RUN:
				dir="rightRun";
				break;
			default:dir="stop";
		}
		return dir;
	}
}