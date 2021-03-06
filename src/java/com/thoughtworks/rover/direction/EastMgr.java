package com.thoughtworks.rover.direction;

import java.awt.Point;

import com.thoughtworks.rover.core.RoverConstants;
import com.thoughtworks.rover.core.RoverUtils;

public final class EastMgr implements DirectionMgr {

	public void moveForward(Point point) {
		if(RoverUtils.isNull(point))
			return;
		point.x = point.x + RoverConstants.STEP_ONE;
	}

	public Direction turnLeft() {
		return Direction.NORTH;
	}

	public Direction turnRight() {
		return Direction.SOUTH;
	}

}
