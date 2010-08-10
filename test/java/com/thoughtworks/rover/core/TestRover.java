package com.thoughtworks.rover.core;

import java.awt.Point;

import com.thoughtworks.rover.BaseTestCase;
import com.thoughtworks.rover.command.ICommand;
import com.thoughtworks.rover.direction.Direction;
import com.thoughtworks.rover.input.RoverInput;

public class TestRover extends BaseTestCase {
	
	private Rover rover;
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		rover = new Rover();
	}

	/**
	 * Method for testing the overloaded constructor(position, direction)
	 * Case#1: Use the overloaded constructor to instantiate the Rover. Assert the successful instantiation of the object.
	 */
	public void testConstructor() {
		Point initialPosition = new Point(1,2);
		Rover overloadedRover = new Rover(new Point(initialPosition), Direction.NORTH);
		assertNotNull("Rover instantiated should not be null!", overloadedRover);
		assertNotNull("Position returned should not be null!", overloadedRover.getPosition());
		assertEquals("Obtained position is incorrect!", initialPosition, overloadedRover.getPosition());		
		assertNotNull("Direction returned should not be null!", overloadedRover.getDirection());
		assertEquals("Obtained Direction is incorrect!", Direction.NORTH.getDirectionChar(), overloadedRover.getDirection().getDirectionChar());
	}

	/**
	 * Method for testing getter and setter methods of Rover.
	 * 
	 * Case#1: Set a Position. Assert if getter returns the same value.
	 * Case#2: Set a Direction. Assert if getter returns the same Direction.
	 * Case#3: Assert if getRoverErrors() returns a non-null object of RoverErrors.
	 */
	public void testGettersAndSetters() {
		//Case#1: Set a Position. Assert if getter returns the same value.
		Point initialPosition = new Point(1,2);
		rover.setPosition(new Point(initialPosition));
		assertNotNull("Position returned should not be null!", rover.getPosition());
		assertEquals("Set and Get positions are not the same!", initialPosition, rover.getPosition());
		
		//Case#2: Set a Direction. Assert if getter returns the same Direction.
		rover.setDirection(Direction.NORTH);
		assertNotNull("Direction returned should not be null!", rover.getDirection());
		assertEquals("Set and Get Directions are not the same!", Direction.NORTH.getDirectionChar(), rover.getDirection().getDirectionChar());
		
		//Case#3: Assert if getRoverErrors() returns a non-null object of RoverErrors.
		assertNotNull("RoverErrors cannot be null!", rover.getRoverErrors());
	}
	
	/**
	 * Method for testing Rover.executeCommands(ICommand[])
	 * 
	 * Case#1: Pass in a null object as commands. Assert for the method to return Success.
	 * Case#2: Pass in an empty Commands list. Assert for the method to return Success.
	 * Case#3: Pass in an Commands array with null commands. Assert for the return of Success.
	 * Case#4: Pass in an valid commands array. Assert for the return of Success.
	 * Case#5: Pass in an valid commands array but with no direction set to rover. Assert for return of Error.
	 */
	public void testExecuteCommands() {
		//Case#1: Pass in a null object as commands. Assert for the method to return Success.
		ICommand[] commands = null;
		assertEquals("Passing null should do nothing and return Success!", RoverConstants.EXEC_SUCCESS, rover.executeCommands(commands));
		
		//Case#2: Pass in an empty Commands list. Assert for the method to return Success.
		commands = new ICommand[0];
		assertEquals("Passing empty command list should do nothing and return Success!", 
																			RoverConstants.EXEC_SUCCESS, rover.executeCommands(commands));
		
		//Case#3: Pass in an Commands array with null commands. Assert for the return of Success.
		commands = new ICommand[5];
		assertEquals("Passing null commands should do nothing and return Success!", 
																			RoverConstants.EXEC_SUCCESS, rover.executeCommands(commands));
		
		//Case#4: Pass in an valid commands array. Assert for the return of Success.		
		RoverInput input = new RoverInput(new Point(1,2), Direction.NORTH, Character.toString(RoverConstants.COMMAND_TURN_LEFT));
		commands = input.getCommands();
		assertEquals("Passing valid commands should return Success!", RoverConstants.EXEC_SUCCESS, rover.executeCommands(commands));
		
		//Case#5: Pass in an valid commands array but with no direction set to rover. Assert for return of Error.
		commands = input.getCommands();
		rover.setDirection(null);
		assertEquals("Passing valid commands with missing Direction should return Error!", 
																			RoverConstants.EXEC_ERROR, rover.executeCommands(commands));
		assertTrue("RoverErrors should be available!", rover.getRoverErrors().hasErrors());
		
		rover = new Rover();
	}
}
