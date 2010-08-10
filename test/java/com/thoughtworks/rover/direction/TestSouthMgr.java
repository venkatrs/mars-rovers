package com.thoughtworks.rover.direction;

import java.awt.Point;

import com.thoughtworks.rover.BaseTestCase;
import com.thoughtworks.rover.core.RoverConstants;

public class TestSouthMgr extends BaseTestCase {

	private SouthMgr southMgr;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.southMgr = new SouthMgr();
	}
	
	/**
	 * Method for testing SouthMgr.moveForward()
	 * 
	 * Case#1: Pass the Point as null and assert for the no operation performed.
	 * Case#2: Pass a valid Point and assert for the y-coordinate is moved one step downward.
	 */
	public void testMoveForward() {
		//Case#1: Pass the Point as null and assert for the no operation performed.
		Point position = null;		
		southMgr.moveForward(position);		
		assertNull("Position should be null!", position);
		
		//Case#2: Pass a valid Point and assert for the y-coordinate is moved one step downward.
		Point initialPosition = new Point(1, 2);
		position = new Point(initialPosition);
		
		southMgr.moveForward(position);
		
		assertNotNull("Position should not be null!", position);
		assertEquals("x co-ordinate should be the same!", initialPosition.x, position.x);
		assertEquals("y co-ordinate should be moved downward to one step!", initialPosition.y - RoverConstants.STEP_ONE, position.y);
	}
	
	/**
	 * Method for testing SouthMgr.turnLeft().
	 * 
	 * Case#1: Assert for the right direction to be returned on turning left.
	 */
	public void testTurnLeft() {
		Direction output = southMgr.turnLeft();
		assertEquals("turnLeft() should return Direction.EAST!", Direction.EAST, output);
	}
	
	/**
	 * Method for testing SouthMgr.turnRight().
	 * 
	 * Case#1: Assert for the right direction to be returned on turning right.
	 */
	public void testTurnRight() {
		Direction output = southMgr.turnRight();
		assertEquals("turnRight() should return Direction.WEST!", Direction.WEST, output);		
	}
}
