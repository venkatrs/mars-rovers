package com.thoughtworks.rover.direction;

import java.awt.Point;

import com.thoughtworks.rover.BaseTestCase;
import com.thoughtworks.rover.core.RoverConstants;

public class TestEastMgr extends BaseTestCase {
	
	private EastMgr eastMgr;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.eastMgr = new EastMgr();
	}
	
	/**
	 * Method for testing EastMgr.moveForward()
	 * 
	 * Case#1: Pass the Point as null and assert for the no operation performed.
	 * Case#2: Pass a valid Point and assert for the x-coordinate is moved one step forward.
	 */
	public void testMoveForward() {
		//Case#1: Pass the Point as null and assert for the no operation performed.
		Point position = null;		
		eastMgr.moveForward(position);		
		assertNull("Position should be null!", position);
		
		//Case#2: Pass a valid Point and assert for the x-coordinate is moved one step forward.
		Point initialPosition = new Point(1, 2);
		position = new Point(initialPosition);
		
		eastMgr.moveForward(position);
		
		assertNotNull("Position should not be null!", position);
		assertEquals("y co-ordinate should be the same!", initialPosition.y, position.y);
		assertEquals("x co-ordinate should be moved forward to one step!", initialPosition.x + RoverConstants.STEP_ONE, position.x);
	}
	
	/**
	 * Method for testing EastMgr.turnLeft().
	 * 
	 * Case#1: Assert for the right direction to be returned on turning left.
	 */
	public void testTurnLeft() {
		Direction output = eastMgr.turnLeft();
		assertEquals("turnLeft() should return Direction.NORTH!", Direction.NORTH, output);
	}
	
	/**
	 * Method for testing EastMgr.turnRight().
	 * 
	 * Case#1: Assert for the right direction to be returned on turning right.
	 */
	public void testTurnRight() {
		Direction output = eastMgr.turnRight();
		assertEquals("turnRight() should return Direction.SOUTH!", Direction.SOUTH, output);		
	}	
}
