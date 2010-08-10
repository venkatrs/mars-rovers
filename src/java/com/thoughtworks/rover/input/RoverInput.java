package com.thoughtworks.rover.input;

import java.awt.Point;

import com.thoughtworks.rover.command.ICommand;
import com.thoughtworks.rover.command.CommandEnum;
import com.thoughtworks.rover.core.RoverConstants;
import com.thoughtworks.rover.core.RoverUtils;
import com.thoughtworks.rover.direction.Direction;

public class RoverInput {

	private Point position;
	private Direction direction;
	/**
	 * Property to hold the commands that needs to be executed for rover.
	 * By default it is set to hold the internal position validation command and later the rest of the incoming 
	 * commands are appended to it. This would ensure the initial position of the rover within x,y coordinate limits. 
	 */
	private String commands =  Character.toString(RoverConstants.COMMAND_VALIDATE_INITIAL_POSITION);
	
	public RoverInput(Point position, Direction direction, String commands) {
		if(!RoverUtils.isNull(commands))
			this.commands = this.commands + commands.trim();
		this.position = position;
		this.direction = direction;
	}

	public ICommand[] getCommands() {
		char[] commandChars = commands.toCharArray();
		ICommand[] commandsAvbl = new ICommand[commandChars.length];
		for(int i=0; i < commandChars.length; i++) 
			commandsAvbl[i] = CommandEnum.getCommand(commandChars[i]);
		return commandsAvbl;
	}
	
	public Point getPosition() {
		return this.position;
	}
	
	public Direction getDirection() {
		return this.direction;
	}
}
