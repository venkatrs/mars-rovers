package com.thoughtworks.rover.command;

import java.awt.Point;

import com.thoughtworks.rover.BaseTestCase;
import com.thoughtworks.rover.core.Rover;
import com.thoughtworks.rover.core.RoverConstants;
import com.thoughtworks.rover.direction.Direction;
import com.thoughtworks.rover.direction.DirectionMgr;
import com.thoughtworks.rover.exception.InvalidInputException;

public class TestTurnLeftCommand extends BaseTestCase {
	
	private TurnLeftCommand turnLeftCommand;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.turnLeftCommand = new TurnLeftCommand();
	}
	
	/**
	 * Method to test TurnLeftCommand.preExecute() method.
	 * 
	 * Case#1: Pass the rover as null and assert for InvalidInputException being thrown.
	 * Case#2: Pass a valid rover with the direction set to null. Assert for the appropriate error message being added.
	 * Case#3: Pass a valid rover along with a valid direction. Assert for no errors.
	 */
	public void testPreExecute() {
		try {
			//Case#1: Pass the rover as null and assert for InvalidInputException being thrown.
			Rover rover = null;
			try {
				turnLeftCommand.preExecute(rover);
				fail("Should have thrown exception by this time!");
			} catch(InvalidInputException e) {
				String message = "Rover Cannot be null!";
				assertEquals(String.format("Error: Invalid input: [%s] - %s", rover, message), e.getMessage());
			}	
			
			//Case#2: Pass a valid rover with the direction set to null. Assert for the appropriate error message being added.
			rover = new Rover(new Point(0,0), null);
			
			turnLeftCommand.preExecute(rover);
			
			assertNotNull("Rover cannot be null!", rover);
			assertNotNull("RoverErrors cannot be null!", rover.getRoverErrors());
			assertEquals("RoverErrors should contain 1 error message!", 1, rover.getRoverErrors().getErrorMessages().size());
			assertEquals("Not a correct error message!", RoverConstants.ERR_NO_DIRECTION_AVBL, rover.getRoverErrors().getErrorMessages().get(0));
			
			//Case#3: Pass a valid rover along with a valid direction. Assert for no errors.
			rover = new Rover(new Point(0,0), Direction.NORTH);
			
			turnLeftCommand.preExecute(rover);
			
			assertNotNull("Rover cannot be null!", rover);
			assertNotNull("RoverErrors cannot be null!", rover.getRoverErrors());
			assertFalse("There should not be any errors!", rover.getRoverErrors().hasErrors());
			assertEquals("Rover's direction shouldn't have been modified!", Direction.NORTH.getDirectionChar(), rover.getDirection().getDirectionChar());
			
		} catch(Exception e) {
			fail("No other exception should have been thrown from TurnLeftCommand.preExecute()!");
		}
	}
	
	/**
	 * Method to test TurnLeftCommand.execute() method.
	 * 
	 * Case#1: Pass in a Rover with a valid direction and assert for the rover direction is switched to 90 degrees right.
	 */
	public void testExecute() {
		Point position = new Point(0, 0);
		Rover rover = new Rover(position, Direction.NORTH);
		
		turnLeftCommand.execute(rover);
		
		assertNotNull("Rover instance cannot be null!", rover);
		assertNotNull("Rover's Direction cannot be null!", rover.getDirection());
		assertNotNull("Rover's Position cannot be null!", rover.getPosition());
		assertEquals("Rover's position shouldn't have been changed!", position, rover.getPosition());
		
		DirectionMgr directionMgr = Direction.NORTH.getDirectionMgr();
		Direction expectedDirection = directionMgr.turnLeft();
		assertEquals("Direction should be changed to '" + expectedDirection.getDirectionChar() + "'!", 
															expectedDirection.getDirectionChar(), rover.getDirection().getDirectionChar());
	}	

}
