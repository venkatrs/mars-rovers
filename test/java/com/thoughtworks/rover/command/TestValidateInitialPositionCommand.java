package com.thoughtworks.rover.command;

import java.awt.Point;

import com.thoughtworks.rover.BaseTestCase;
import com.thoughtworks.rover.core.Rover;
import com.thoughtworks.rover.core.RoverConstants;
import com.thoughtworks.rover.core.RoverContext;
import com.thoughtworks.rover.core.RoverErrors;
import com.thoughtworks.rover.direction.Direction;
import com.thoughtworks.rover.exception.InvalidInputException;

public class TestValidateInitialPositionCommand extends BaseTestCase {
	
	private ValidateInitialPositionCommand validateInitialPositionCommand;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.validateInitialPositionCommand = new ValidateInitialPositionCommand();
	}
	
	/**
	 * Method to test ValidateInitialPositionCommand.preExecute() method.
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
				validateInitialPositionCommand.preExecute(rover);
				fail("Should have thrown exception by this time!");
			} catch(InvalidInputException e) {
				String message = "Rover Cannot be null!";
				assertEquals(String.format("Error: Invalid input: [%s] - %s", rover, message), e.getMessage());
			}	
			
			//Case#2: Pass a valid rover with the direction set to null. Assert for the appropriate error message being added.
			rover = new Rover(new Point(0,0), null);
			
			validateInitialPositionCommand.preExecute(rover);
			
			assertNotNull("Rover cannot be null!", rover);
			assertNotNull("RoverErrors cannot be null!", rover.getRoverErrors());
			assertEquals("RoverErrors should contain 1 error message!", 1, rover.getRoverErrors().getErrorMessages().size());
			assertEquals("Not a correct error message!", RoverConstants.ERR_NO_DIRECTION_AVBL, rover.getRoverErrors().getErrorMessages().get(0));
			
			//Case#3: Pass a valid rover with the position as well set to null. Assert for the appropriate error message being added.
			rover = new Rover(null, null);
			
			validateInitialPositionCommand.preExecute(rover);

			assertNotNull("Rover cannot be null!", rover);
			assertNotNull("RoverErrors cannot be null!", rover.getRoverErrors());
			assertEquals("RoverErrors should contain 2 error messages!", 2, rover.getRoverErrors().getErrorMessages().size());
			assertTrue("Appropriate error message not found!", rover.getRoverErrors().getErrorMessages().contains(RoverConstants.ERR_NO_DIRECTION_AVBL));
			assertTrue("Appropriate error message not found!", rover.getRoverErrors().getErrorMessages().contains(RoverConstants.ERR_NO_POSITION_AVBL));
			
			//Case#4: Pass a valid rover along with a valid direction. Assert for no errors.
			rover = new Rover(new Point(0,0), Direction.NORTH);
			
			validateInitialPositionCommand.preExecute(rover);
			
			assertNotNull("Rover cannot be null!", rover);
			assertNotNull("RoverErrors cannot be null!", rover.getRoverErrors());
			assertFalse("There should not be any errors!", rover.getRoverErrors().hasErrors());
			assertEquals("Rover's direction shouldn't have been modified!", Direction.NORTH.getDirectionChar(), rover.getDirection().getDirectionChar());
			
		} catch(Exception e) {
			fail("No other exception should have been thrown from TestValidateInitialPositionCommand.preExecute()!");
		}
	}
	
	/**
	 * Method to test ValidateInitialPositionCommand.execute() method.
	 * 
	 * Case#1: Pass in a Rover with a position exceeding the maximum set coordinates. 
	 * 		   Assert for the appropriate error message.
	 * Case#2: Pass in a Rover with a position set below the minimum set coordinates. 
	 * 		   Assert for the appropriate error message.
	 * Case#3: Pass in a Rover with a valid position. Assert for no error messages have been added.
	 */	
	public void testExecute() {
		//Case#1: Pass in a Rover with a position exceeding the maximum set coordinates. 
		//		   Assert for the appropriate error message.
		Point maxPosition = RoverContext.getMaxPosition();
		Point roverPosition = new Point(1, maxPosition.y + 1);
		Rover rover = new Rover(roverPosition, Direction.NORTH);
		
		validateInitialPositionCommand.execute(rover);
		
		assertNotNull("Rover cannot be null!", rover);
		RoverErrors errors = rover.getRoverErrors();
		assertNotNull("RoverErrors cannot be null!", errors);
		assertEquals("RoverErrors should contain only one error message!", 1, errors.getErrorMessages().size());
		assertEquals("Incorrect error message!", RoverConstants.ERR_CROSSED_MAX_LIMITS, errors.getErrorMessages().get(0));
		
		// Case#2: Pass in a Rover with a position set below the minimum set coordinates. 
		// 		   Assert for the appropriate error message.
		Point minPosition = RoverContext.getMinPosition();
		roverPosition = new Point(minPosition.x -1, 2);
		rover = new Rover(roverPosition, Direction.NORTH);
		
		validateInitialPositionCommand.execute(rover);
		
		assertNotNull("Rover cannot be null!", rover);
		errors = rover.getRoverErrors();
		assertNotNull("RoverErrors cannot be null!", errors);
		assertEquals("RoverErrors should contain only one error message!", 1, errors.getErrorMessages().size());
		assertEquals("Incorrect error message!", RoverConstants.ERR_CROSSED_MIN_LIMITS, errors.getErrorMessages().get(0));
		
		//Case#3: Pass in a Rover with a valid position. Assert for no error messages have been added.
		roverPosition = new Point(minPosition.x + 1, maxPosition.y);
		rover = new Rover(roverPosition, Direction.NORTH);
		
		validateInitialPositionCommand.execute(rover);
		
		assertNotNull("Rover cannot be null!", rover);
		errors = rover.getRoverErrors();
		assertNotNull("RoverErrors cannot be null!", errors);
		assertFalse("Rover shouldn't have encountered any error!", errors.hasErrors());
	}

}
