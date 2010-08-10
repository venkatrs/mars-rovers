package com.thoughtworks.rover.direction;

import java.awt.Point;

import com.thoughtworks.rover.core.RoverConstants;
import com.thoughtworks.rover.core.RoverUtils;

public final class NorthMgr implements DirectionMgr {

	public void moveForward(Point point) {
		if(RoverUtils.isNull(point))
			return;
		point.y = point.y + RoverConstants.STEP_ONE;
	}

	public Direction turnLeft() {
		return Direction.WEST;
	}

	public Direction turnRight() {
		return Direction.EAST;
	}

}
