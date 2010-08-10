package com.thoughtworks.rover.input;

import java.awt.Point;

import com.thoughtworks.rover.BaseTestCase;
import com.thoughtworks.rover.command.CommandEnum;
import com.thoughtworks.rover.core.RoverConstants;
import com.thoughtworks.rover.direction.Direction;
import com.thoughtworks.rover.input.RoverInput;

public class TestRoverInput extends BaseTestCase {

	/**
	 * Method for testing RoverInput constructor(position, direction, commands)
	 * 
	 * Case#1: Pass in the appropriate parameters. Assert for the proper instantiation of RoverInput object.
	 */
	public void testConstructor() {
		
		RoverInput roverInput = new RoverInput(new Point(1,0), Direction.NORTH, null);
		assertNotNull("RoverInput should not be null!", roverInput);
		assertEquals("Incorrect Position!", new Point(1,0), roverInput.getPosition());
		assertEquals("Incorrect Direction!", Direction.NORTH.getDirectionChar(), roverInput.getDirection().getDirectionChar());
		assertNotNull("Commands array cannot be null!", roverInput.getCommands());
		assertEquals("Commands array should be of size '1'!", 1, roverInput.getCommands().length);
		assertEquals("Incorrect Commands!", 
				CommandEnum.getCommand(RoverConstants.COMMAND_VALIDATE_INITIAL_POSITION).getClass(), roverInput.getCommands()[0].getClass());
	}
	
	/**
	 * Method for testing RoverInput's getter methods.
	 * 
	 * Case#1: Assert for the correct position returned by getPosition() method.
	 * Case#2: Assert for the correct direction returned by getDirection() method.
	 */
	public void testGetters() {
		//Case#1: Assert for the correct position returned by getPosition() method.
		RoverInput roverInput = new RoverInput(new Point(1,0), Direction.NORTH, null);
		assertNotNull("RoverInput should not be null!", roverInput);
		assertEquals("Incorrect Position!", new Point(1,0), roverInput.getPosition());
		
		//Case#2: Assert for the correct direction returned by getDirection() method.
		assertEquals("Incorrect Direction!", Direction.NORTH.getDirectionChar(), roverInput.getDirection().getDirectionChar());
	}
	
	/**
	 * Method for testing RoverInput.getCommands()
	 * 
	 * Case#1: Set the commands string as null. Assert for the default command returned.
	 * Case#2: Set the commands as empty string. Assert for the default command returned.
	 * Case#3: Set the proper commands. Assert for the commands returned by the method. 
	 */
	public void testGetCommands() {
		//Case#1: Set the commands string as null. Assert for the default command returned.
		String commandsStr = null;
		RoverInput roverInput = new RoverInput(new Point(1,0), Direction.NORTH, commandsStr);
		assertNotNull("Commands array cannot be null!", roverInput.getCommands());
		assertEquals("Commands array should be of size '1'!", 1, roverInput.getCommands().length);
		assertEquals("Incorrect Commands!", 
				CommandEnum.getCommand(RoverConstants.COMMAND_VALIDATE_INITIAL_POSITION).getClass(), roverInput.getCommands()[0].getClass());
		
		//Case#2: Set the commands as empty string. Assert for the default command returned.
		commandsStr = "";
		roverInput = new RoverInput(new Point(1,0), Direction.NORTH, commandsStr);
		assertNotNull("Commands array cannot be null!", roverInput.getCommands());
		assertEquals("Commands array should be of size '1'!", 1, roverInput.getCommands().length);
		assertEquals("Incorrect Commands!", 
				CommandEnum.getCommand(RoverConstants.COMMAND_VALIDATE_INITIAL_POSITION).getClass(), roverInput.getCommands()[0].getClass());
		
		//Case#3: Set the proper commands. Assert for the commands returned by the method.
		commandsStr = Character.toString(RoverConstants.COMMAND_TURN_RIGHT);
		roverInput = new RoverInput(new Point(1,0), Direction.NORTH, commandsStr);
		assertNotNull("Commands array cannot be null!", roverInput.getCommands());
		assertEquals("Commands array should be of size '2'!", 2, roverInput.getCommands().length);
		assertEquals("Incorrect Commands!", 
				CommandEnum.getCommand(RoverConstants.COMMAND_VALIDATE_INITIAL_POSITION).getClass(), roverInput.getCommands()[0].getClass());
		assertEquals("Incorrect Commands!", 
				CommandEnum.getCommand(RoverConstants.COMMAND_TURN_RIGHT).getClass(), roverInput.getCommands()[1].getClass());
	}
}
