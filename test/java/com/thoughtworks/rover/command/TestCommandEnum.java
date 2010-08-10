package com.thoughtworks.rover.command;

import com.thoughtworks.rover.BaseTestCase;
import com.thoughtworks.rover.core.RoverConstants;


public class TestCommandEnum extends BaseTestCase {
	
	
	/**
	 * This method is to validate the command values available from CommandEnum.
	 * Case#1: Assert if all the commands are included in the command enum.
	 */
	public void testCommandEnum() {
		//Case#1: Assert if all the commands are included in the command enum.
		assertEquals("Not all Commands are added to the CommandEnum", RoverConstants.COMMANDS.length, CommandEnum.values().length);
		
		for(int i=0; i < RoverConstants.COMMANDS.length; i++) {
			assertNotNull("Should return a appropriate command for the given commandKey!", CommandEnum.getCommand(RoverConstants.COMMANDS[i]));
		}
	}
	
	/**
	 * This method is to test CommandEnum.getCommand(key) method.
	 * Case#1: Pass in a valid command character key and assert for if the method returns the right command instance.
	 * Case#2: Pass in an invalid command character key and assert for if the method returns null.
	 */
	public void testGetCommand() {
		//Case#1: Pass in a valid command character key and assert for if the method returns the right command instance.
		char validKey = RoverConstants.COMMAND_TURN_LEFT;
		ICommand command = CommandEnum.getCommand(validKey);
		assertNotNull("Returned command cannot be null!", command);
		assertEquals("Return command should be of type, TURN_LEFT", CommandEnum.TURN_LEFT.getCommand(), command);
		assertEquals("TURN_LEFT commands key should be " + validKey, CommandEnum.TURN_LEFT.getKey(), validKey);			

		//Case#2: Pass in an invalid command character key and assert for if the method throws a InvalidInputException.
		char invalidKey = 'A';
		command = CommandEnum.getCommand(invalidKey);
		assertNull("Returned command should be null!", command);
	}
	
	/**
	 * This method is to the test CommandEnum.getCommand().
	 * Case#1: For a given commandEnum assert if the command object returned is not null and is the right one.
	 */
	public void testGetCommandWithNoArgs() {
		//Case#1: For a given commandEnum assert if the command object returned is not null and is the right one.
		ICommand command = CommandEnum.TURN_LEFT.getCommand();
		assertNotNull("Returned command cannot be null!", command);
		assertEquals("Command should be of Type 'TurnLeft' command!", TurnLeftCommand.class, command.getClass());
	}
	
	/**
	 * This method is to test the CommandEnum.getKey().
	 * Case#1: For a given CommandEnum, assert if the command key returned is valid.
	 */
	public void testGetKey() {
		//Case#1: For a given CommandEnum, assert if the command key returned is valid.
		char commandKey = CommandEnum.TURN_LEFT.getKey();
		assertEquals("Returned key should be '" + RoverConstants.COMMAND_TURN_LEFT + "'!", RoverConstants.COMMAND_TURN_LEFT, commandKey);
	}
}
