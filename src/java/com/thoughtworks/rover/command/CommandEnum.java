package com.thoughtworks.rover.command;

import com.thoughtworks.rover.core.RoverConstants;

/**
 * This Enum helps in managing and returning the standard set of Commands that can be executed over a
 * given rover.
 * 
 * @author Venkatesh
 *
 */
public enum CommandEnum {
	
	TURN_LEFT (RoverConstants.COMMAND_TURN_LEFT, new TurnLeftCommand()),
	TURN_RIGHT (RoverConstants.COMMAND_TURN_RIGHT, new TurnRightCommand()),
	MOVE_FORWARD (RoverConstants.COMMAND_MOVE_FORWARD, new MoveForwardCommand()),
	SET_POSITION (RoverConstants.COMMAND_VALIDATE_INITIAL_POSITION, new ValidateInitialPositionCommand());
	
	private final char key;
	private final ICommand commandInstance;
	
	/**
	 * Constructor for instantiating the CommandEnum
	 * @param key commandKey.
	 * @param commandInstance associated Command instance.
	 */
	CommandEnum(char key, ICommand commandInstance) {
		this.key = key;
		this.commandInstance = commandInstance;
	}
	
	/**
	 * Method to return the respective command for the given CommandEnum.
	 * @return command object.
	 */
	public ICommand getCommand() {
		return this.commandInstance;
	}
	
	/**
	 * Method to return the respective key for the command.
	 * @return character key.
	 */
	public char getKey() {
		return this.key;
	}
	
	/**
	 * Method that returns the respective Command object for the given command key.
	 * @param key the character key for getting the Command.
	 * @return Command if it a valid key else returns null.
	 */
	public static ICommand getCommand(char key) {
		for(CommandEnum command : CommandEnum.values()) {
			if(command.getKey() == key)
				return command.getCommand();
		}
		return null;
	}
}
