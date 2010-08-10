package com.thoughtworks.rover.direction;

import com.thoughtworks.rover.BaseTestCase;
import com.thoughtworks.rover.core.RoverConstants;

public class TestDirection extends BaseTestCase {
	
	/**
	 * This method is to validate the Direction constants available in Direction enum.
	 * 
	 * Case#1: Assert if all the Direction constants are included in the Direction enum.
	 */
	public void testDirectionEnum() {
		assertEquals("Not all Direction constants are added to the Direction enum", 
										RoverConstants.DIRECTIONS.length, Direction.values().length);
		
		for(int i=0; i < RoverConstants.DIRECTIONS.length; i++) {
			assertNotNull("Should return a appropriate Direction for the given DirectionChar!", 
												Direction.getDirection(RoverConstants.DIRECTIONS[i]));
		}
	}

	/**
	 * Method for testing Direction.getDirection() method.
	 * 
	 * Case#1: Pass in a valid Direction character key and assert for if the method returns the right Direction instance.
	 * Case#2: Pass in an invalid Direction character key and assert for if the method returns null.
	 */
	public void testGetDirection() {
		//Case#1: Pass in a valid Direction character key and assert for if the method returns the right Direction instance.
		char validKey = RoverConstants.DIRECTION_NORTH_CHAR;
		Direction direction = Direction.getDirection(validKey);
		assertNotNull("Returned Direction cannot be null!", direction);
		assertEquals("Return Direction should be of type, NORTH", Direction.NORTH.getDirectionChar(), direction.getDirectionChar());

		//Case#2: Pass in an invalid Direction character key and assert for if the method returns null.
		char invalidKey = 'A';
		direction = Direction.getDirection(invalidKey);
		assertNull("Returned Direction should be null!", direction);
	}
	
	/**
	 * Method for testing Direction.getDirectionMgr().
	 * 
	 * Case#1: For a given Direction assert if the DirectionMgr object returned is not null and is the right one.
	 */
	public void testGetDirectionMgr() {
		DirectionMgr directionMgr = Direction.NORTH.getDirectionMgr();
		assertNotNull("Returned DirectionMgr cannot be null!", directionMgr);
		assertEquals("DirectionMgr should be of Type 'NorthMgr'!", NorthMgr.class, directionMgr.getClass());
	}
	
	/**
	 * Method for testing Direction.getDirectionChar().
	 * 
	 * Case#1: For a given Direction, assert if the DirectionChar key returned is valid.
	 */
	public void testGetDirectionChar() {
		char directionChar = Direction.NORTH.getDirectionChar();
		assertEquals("Returned key should be '" + RoverConstants.DIRECTION_NORTH_CHAR + "'!", 
																RoverConstants.DIRECTION_NORTH_CHAR, directionChar);
	}
}
