package com.thoughtworks.rover.direction;

import java.awt.Point;

import com.thoughtworks.rover.BaseTestCase;
import com.thoughtworks.rover.core.RoverConstants;

public class TestNorthMgr extends BaseTestCase {
	
	private NorthMgr northMgr;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.northMgr = new NorthMgr();
	}
	
	/**
	 * Method for testing NorthMgr.moveForward()
	 * 
	 * Case#1: Pass the Point as null and assert for the no operation performed.
	 * Case#2: Pass a valid Point and assert for the y-coordinate is moved one step forward.
	 */
	public void testMoveForward() {
		//Case#1: Pass the Point as null and assert for the no operation performed.
		Point position = null;		
		northMgr.moveForward(position);		
		assertNull("Position should be null!", position);
		
		//Case#2: Pass a valid Point and assert for the y-coordinate is moved one step forward.
		Point initialPosition = new Point(0, 0);
		position = new Point(initialPosition);
		
		northMgr.moveForward(position);
		
		assertNotNull("Position should not be null!", position);
		assertEquals("x co-ordinate should be the same!", initialPosition.x, position.x);
		assertEquals("y co-ordinate should be moved forward to one step!", initialPosition.y + RoverConstants.STEP_ONE, position.y);
	}
	
	/**
	 * Method for testing NorthMgr.turnLeft().
	 * 
	 * Case#1: Assert for the right direction to be returned on turning left.
	 */
	public void testTurnLeft() {
		Direction output = northMgr.turnLeft();
		assertEquals("turnLeft() should return Direction.WEST!", Direction.WEST, output);
	}

	/**
	 * Method for testing NorthMgr.turnRight().
	 * 
	 * Case#1: Assert for the right direction to be returned on turning right.
	 */
	public void testTurnRight() {
		Direction output = northMgr.turnRight();
		assertEquals("turnRight() should return Direction.EAST!", Direction.EAST, output);		
	}
}
