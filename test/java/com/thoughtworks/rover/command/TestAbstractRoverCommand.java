package com.thoughtworks.rover.command;

import java.awt.Point;

import com.thoughtworks.rover.BaseTestCase;
import com.thoughtworks.rover.core.Rover;
import com.thoughtworks.rover.core.RoverConstants;
import com.thoughtworks.rover.core.RoverErrors;
import com.thoughtworks.rover.direction.Direction;
import com.thoughtworks.rover.exception.InvalidInputException;

public class TestAbstractRoverCommand extends BaseTestCase {

	private ConcreteTestableRoverCommand testableRoverCommand;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.testableRoverCommand = new ConcreteTestableRoverCommand();
	}
	
	/**
	 * Test method for AbstractRoverCommand.validateForNoDirection() method.
	 * 
	 * Case#1: Pass in a null value for Rover's direction and assert for the error message.
	 * Case#2: Pass in a valid Rover's Direction and assert for empty errors.
	 */
	public void testValidateForNoDirection() {
		//Case#1: Pass in a null value for Rover's direction and assert for the error message.
		Direction roverDirection = null;
		Rover rover = new Rover(new Point(0,0), roverDirection);
		
		testableRoverCommand.validateForNoDirection(rover);
		
		assertNotNull("Rover cannot be null!", rover);
		RoverErrors errors = rover.getRoverErrors();
		assertNotNull("RoverErrors cannot be null!", errors);
		assertEquals("RoverErrors should contain only one error message!", 1, errors.getErrorMessages().size());
		assertEquals("Incorrect error message!", RoverConstants.ERR_NO_DIRECTION_AVBL, errors.getErrorMessages().get(0));
		
		//Case#2: Pass in a valid Rover's Direction and assert for empty errors.
		roverDirection = Direction.NORTH;
		rover = new Rover(new Point(0,0), roverDirection);
		
		testableRoverCommand.validateForNoDirection(rover);
		
		assertNotNull("Rover cannot be null!", rover);
		assertNotNull("Rover's Direction cannot be null!", rover.getDirection());
		assertEquals("Incorrect Rover's Direction!", Direction.NORTH.getDirectionChar(), rover.getDirection().getDirectionChar());
		errors = rover.getRoverErrors();
		assertNotNull("RoverErrors cannot be null!", errors);
		assertFalse("Rover should not contain any errors!", errors.hasErrors());
	}

	/**
	 * Test method for AbstractRoverCommand.validateForNoPosition() method.
	 * 
	 * Case#1: Pass in a null value for Rover's position and assert for the error message.
	 * Case#2: Pass in a valid Rover's Position and assert for empty errors.
	 */
	public void testValidateForNoPosition() {
		//Case#1: Pass in a null value for Rover's position and assert for the error message.
		Point roverPosition = null;
		Rover rover = new Rover(roverPosition, Direction.NORTH);
		
		testableRoverCommand.validateForNoPosition(rover);
		
		assertNotNull("Rover cannot be null!", rover);
		RoverErrors errors = rover.getRoverErrors();
		assertNotNull("RoverErrors cannot be null!", errors);
		assertEquals("RoverErrors should contain only one error message!", 1, errors.getErrorMessages().size());
		assertEquals("Incorrect error message!", RoverConstants.ERR_NO_POSITION_AVBL, errors.getErrorMessages().get(0));
		
		//Case#2: Pass in a valid Rover's Position and assert for empty errors.
		roverPosition = new Point(0, 0);
		rover = new Rover(roverPosition, Direction.NORTH);
		
		testableRoverCommand.validateForNoPosition(rover);
		
		assertNotNull("Rover cannot be null!", rover);
		assertNotNull("Rover's Position cannot be null!", rover.getPosition());
		assertEquals("Incorrect Rover's Position!", roverPosition, rover.getPosition());
		errors = rover.getRoverErrors();
		assertNotNull("RoverErrors cannot be null!", errors);
		assertFalse("Rover should not contain any errors!", errors.hasErrors());
		errors = rover.getRoverErrors();
		assertNotNull("RoverErrors cannot be null!", errors);
		assertFalse("Rover should not contain any errors!", errors.hasErrors());
	}
	
	/**
	 * Method for testing AbstractRoverCommand.executeCommand()
	 * 
	 * Case#1: Pass Rover as null and assert for InvalidInputException 
	 * Case#2: Pass a valid Rover with empty errors and assert that all the three methods are executed.
	 * Case#3: Pass a valid Rover with error messages and assert if only the preExecute() method is executed.
	 */
	public void testExecuteCommand() {
		//Case#1: Pass Rover as null and assert for InvalidInputException
		Rover rover = null;
		try {
			testableRoverCommand.preExecute(rover);
			fail("Should have thrown exception by this time!");
		} catch(InvalidInputException e) {
			String message = "Rover Cannot be null!";
			assertEquals(String.format("Error: Invalid input: [%s] - %s", rover, message), e.getMessage());
		}	
		
		//Case#2: Pass a valid Rover with empty errors and assert that all the three methods are executed.
		rover = new Rover(new Point(0,0), Direction.NORTH);
		RoverErrors errors = rover.getRoverErrors();
		errors.addErrorMessage(RoverConstants.ERR_CROSSED_MAX_LIMITS);		
		assertTrue("Rover should have an error!", errors.hasErrors());
		
		testableRoverCommand.executeCommand(rover);
		
		assertNotNull("Rover cannot be null!", rover);
		assertTrue("preExecute() method should have got executed!", testableRoverCommand.isInvokedPreExecuteMethod());
		assertFalse("execute() method should not have got executed!", testableRoverCommand.isInvokedExecuteMethod());
		assertFalse("postExecute() method should not have got executed!", testableRoverCommand.isInvokedPostExecuteMethod());
		
		//Case#3: Pass a valid Rover with error messages and assert if only the preExecute() method is executed.
		rover = new Rover(new Point(0,0), Direction.NORTH);
		assertFalse("Rover should not have any errors!", rover.getRoverErrors().hasErrors());
		
		testableRoverCommand.executeCommand(rover);
		
		assertNotNull("Rover cannot be null!", rover);
		assertTrue("preExecute() method should have got executed!", testableRoverCommand.isInvokedPreExecuteMethod());
		assertTrue("execute() method should have got executed!", testableRoverCommand.isInvokedExecuteMethod());
		assertTrue("postExecute() method should have got executed!", testableRoverCommand.isInvokedPostExecuteMethod());
	}
	
	/**
	 * Method to test AbstractRoverCommand.preExecute() method
	 * 
	 * Case#1: Pass in a null rover and assert for InvalidInputException.
	 * Case#2: Pass in a valid rover and assert for the values doesn't change.
	 */
	public void testPreExecute() {
		//Case#1: Pass in a null rover and assert for InvalidInputException.
		Rover rover = null;
		try {
			testableRoverCommand.preExecute(rover);
			fail("Should have thrown exception by this time!");
		} catch(InvalidInputException e) {
			String message = "Rover Cannot be null!";
			assertEquals(String.format("Error: Invalid input: [%s] - %s", rover, message), e.getMessage());
		}	

		Point roverPosition = new Point(0, 0);
		rover = new Rover(roverPosition, Direction.NORTH);
		
		testableRoverCommand.validateForNoPosition(rover);
		
		assertNotNull("Rover cannot be null!", rover);
		assertNotNull("Rover's Position cannot be null!", rover.getPosition());
		assertEquals("Incorrect Rover's Position!", roverPosition, rover.getPosition());		
		assertNotNull("Rover's Direction cannot be null!", rover.getDirection());
		assertEquals("Incorrect Rover's Direction!", Direction.NORTH.getDirectionChar(), rover.getDirection().getDirectionChar());
		
	}

	/**
	 * This method is used for testing the AbstractRoverCommand implementation.
	 * @author Venkatesh
	 *
	 */
	class ConcreteTestableRoverCommand extends AbstractRoverCommand {
		
		private boolean invokedPreExecuteMethod;
		private boolean invokedExecuteMethod;
		private boolean invokedPostExecuteMethod;

		@Override
		protected void preExecute(Rover rover) {
			super.preExecute(rover);
			invokedPreExecuteMethod = true;
		}

		@Override
		protected void execute(Rover rover) {
			invokedExecuteMethod = true;
		}

		@Override
		protected void postExecute(Rover rover) {
			super.postExecute(rover);
			invokedPostExecuteMethod = true;
		}
		
		public boolean isInvokedPreExecuteMethod() {
			return invokedPreExecuteMethod;
		}

		public boolean isInvokedExecuteMethod() {
			return invokedExecuteMethod;
		}

		public boolean isInvokedPostExecuteMethod() {
			return invokedPostExecuteMethod;
		}
	}
}
