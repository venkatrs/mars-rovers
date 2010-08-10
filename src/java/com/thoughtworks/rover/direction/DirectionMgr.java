package com.thoughtworks.rover.direction;

import java.awt.Point;


public interface DirectionMgr {
	
	Direction turnLeft();
	
	Direction turnRight();
	
	void moveForward(Point point);

}
