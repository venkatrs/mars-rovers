package com.thoughtworks.rover.command;

import java.awt.Point;

import com.thoughtworks.rover.BaseTestCase;
import com.thoughtworks.rover.core.Rover;
import com.thoughtworks.rover.core.RoverConstants;
import com.thoughtworks.rover.core.RoverContext;
import com.thoughtworks.rover.core.RoverErrors;
import com.thoughtworks.rover.direction.Direction;
import com.thoughtworks.rover.exception.InvalidInputException;

public class TestMoveForwardCommand extends BaseTestCase {
	
	private MoveForwardCommand moveForwardCommand;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		moveForwardCommand = new MoveForwardCommand();
	}
	
	/**
	 * Method to test MoveForwardCommand.preExecute() method.
	 * 
	 * Case#1: Pass the rover as null and assert for InvalidInputException being thrown.
	 * Case#2: Pass a valid rover with the direction set to null. Assert for the appropriate error message being added.
	 * Case#3: Pass a valid rover with the position as well set to null. Assert for the appropriate error messages being added.
	 * Case#4: Pass a valid rover along with a valid direction. Assert for no errors.
	 */
	public void testPreExecute() {
		try {
			//Case#1: Pass the rover as null and assert for InvalidInputException being thrown.
			Rover rover = null;
			try {
				moveForwardCommand.preExecute(rover);
				fail("Should have thrown exception by this time!");
			} catch(InvalidInputException e) {
				String message = "Rover Cannot be null!";
				assertEquals(String.format("Error: Invalid input: [%s] - %s", rover, message), e.getMessage());
			}	
			
			//Case#2: Pass a valid rover with the direction set to null. Assert for the appropriate error message being added.
			rover = new Rover(new Point(0,0), null);
			
			moveForwardCommand.preExecute(rover);
			
			assertNotNull("Rover cannot be null!", rover);
			assertNotNull("RoverErrors cannot be null!", rover.getRoverErrors());
			assertEquals("RoverErrors should contain 1 error message!", 1, rover.getRoverErrors().getErrorMessages().size());
			assertEquals("Not a correct error message!", RoverConstants.ERR_NO_DIRECTION_AVBL, rover.getRoverErrors().getErrorMessages().get(0));
			
			//Case#3: Pass a valid rover with the position as well set to null. Assert for the appropriate error message being added.
			rover = new Rover(null, null);
			
			moveForwardCommand.preExecute(rover);

			assertNotNull("Rover cannot be null!", rover);
			assertNotNull("RoverErrors cannot be null!", rover.getRoverErrors());
			assertEquals("RoverErrors should contain 2 error messages!", 2, rover.getRoverErrors().getErrorMessages().size());
			assertTrue("Appropriate error message not found!", rover.getRoverErrors().getErrorMessages().contains(RoverConstants.ERR_NO_DIRECTION_AVBL));
			assertTrue("Appropriate error message not found!", rover.getRoverErrors().getErrorMessages().contains(RoverConstants.ERR_NO_POSITION_AVBL));
			
			//Case#4: Pass a valid rover along with a valid direction. Assert for no errors.
			rover = new Rover(new Point(0,0), Direction.NORTH);
			
			moveForwardCommand.preExecute(rover);
			
			assertNotNull("Rover cannot be null!", rover);
			assertNotNull("RoverErrors cannot be null!", rover.getRoverErrors());
			assertFalse("There should not be any errors!", rover.getRoverErrors().hasErrors());
			assertEquals("Rover's direction shouldn't have been modified!", Direction.NORTH.getDirectionChar(), rover.getDirection().getDirectionChar());
			
		} catch(Exception e) {
			fail("No other exception should have been thrown from TestMoveForwardCommand.preExecute()!");
		}
	}

	/**
	 * Method to test MoveForwardCommand.postExecute() method.
	 * 
	 * Case#1: Pass in a Rover with a position exceeding the maximum set coordinates. 
	 * 		   Assert for the appropriate error message and the rover's position being reset within limits.
	 * Case#2: Pass in a Rover with a position set below the minimum set coordinates. 
	 * 		   Assert for the appropriate error message and the rover's position being reset within limits.
	 * Case#3: Pass in a Rover with a valid position. 
	 * 		   Assert for no error messages have been added and the rover's position is not modified.
	 */	
	public void testPostExecute() {
		try {
			//Case#1: Pass in a Rover with a position exceeding the maximum set coordinates. 
			//		  Assert for the appropriate error message and the rover's position being reset within limits.
			Point maxPosition = RoverContext.getMaxPosition();
			Point initialRoverPosition = new Point(1, maxPosition.y + 1);
			Rover rover = new Rover(initialRoverPosition, Direction.NORTH);
			
			moveForwardCommand.postExecute(rover);
			
			assertNotNull("Rover cannot be null!", rover);
			assertNotNull("Rover's position cannot be null!", rover.getPosition());
			assertEquals("Not a expected position!", new Point(initialRoverPosition.x, maxPosition.y), rover.getPosition());
			RoverErrors errors = rover.getRoverErrors();
			assertNotNull("RoverErrors cannot be null!", errors);
			assertEquals("RoverErrors should contain only one error message!", 1, errors.getErrorMessages().size());
			assertEquals("Incorrect error message!", RoverConstants.ERR_CROSSED_MAX_LIMITS, errors.getErrorMessages().get(0));
			
			// Case#2: Pass in a Rover with a position set below the minimum set coordinates. 
			// 		   Assert for the appropriate error message and the rover's position being reset within limits.
			Point minPosition = RoverContext.getMinPosition();
			initialRoverPosition = new Point(minPosition.x -1, 2);
			rover = new Rover(initialRoverPosition, Direction.NORTH);
			
			moveForwardCommand.postExecute(rover);
			
			assertNotNull("Rover cannot be null!", rover);
			assertNotNull("Rover's position cannot be null!", rover.getPosition());
			assertEquals("Not a expected position!", new Point(minPosition.x, initialRoverPosition.y), rover.getPosition());
			errors = rover.getRoverErrors();
			assertNotNull("RoverErrors cannot be null!", errors);
			assertEquals("RoverErrors should contain only one error message!", 1, errors.getErrorMessages().size());
			assertEquals("Incorrect error message!", RoverConstants.ERR_CROSSED_MIN_LIMITS, errors.getErrorMessages().get(0));
			
			//Case#3: Pass in a Rover with a valid position. 
			//        Assert for no error messages have been added and the rover's position is not modified.
			initialRoverPosition = new Point(minPosition.x + 1, 2);
			rover = new Rover(new Point(initialRoverPosition), Direction.NORTH);
			
			moveForwardCommand.postExecute(rover);
			
			assertNotNull("Rover cannot be null!", rover);
			assertNotNull("Rover's position cannot be null!", rover.getPosition());
			assertEquals("Not a expected position!", initialRoverPosition, rover.getPosition());
			errors = rover.getRoverErrors();
			assertNotNull("RoverErrors cannot be null!", errors);
			assertFalse("Rover shouldn't have encountered any error!", errors.hasErrors());			
		} catch(Exception e) {
			fail("No other exception should have been thrown from TestMoveForwardCommand.postExecute()!");
		}
	}
	
	/**
	 * Method to test MoveForwardCommand.execute() method.
	 * 
	 * Case#1: Pass in a Rover with a valid direction and position assert for the rover has been moved forward.
	 */	
	public void testExecute() {
		Point minPosition = RoverContext.getMinPosition();
		Point initialRoverPosition = new Point(minPosition.x + 1, 2);
		Rover rover = new Rover(new Point(initialRoverPosition), Direction.NORTH);
		
		moveForwardCommand.execute(rover);

		assertNotNull("Rover cannot be null!", rover);
		assertNotNull("Rover's position cannot be null!", rover.getPosition());
		assertNotNull("Rover's Direction cannot be null!", rover.getDirection());
		rover.getDirection().getDirectionMgr().moveForward(initialRoverPosition);
		assertEquals("Not a expected position!", initialRoverPosition, rover.getPosition());
		RoverErrors errors = rover.getRoverErrors();
		assertNotNull("RoverErrors cannot be null!", errors);
		assertFalse("Rover shouldn't have encountered any error!", errors.hasErrors());
	}
}
