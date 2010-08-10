package com.thoughtworks.rover.core;

import java.awt.Point;

import com.thoughtworks.rover.command.ICommand;
import com.thoughtworks.rover.direction.Direction;


public class Rover {
	
	private Point position = new Point(0, 0);	
	private Direction direction = Direction.NORTH;
	
	private RoverErrors errors;
	
	public Rover() {
		this.errors = new RoverErrors();
	}
	
	public Rover(Point position, Direction direction) {
		this();
		this.position = position;
		this.direction = direction;
	}
	
	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public String executeCommands(ICommand[] commands) {
		if(RoverUtils.isNull(commands))
			return RoverConstants.EXEC_SUCCESS;
		
		for(int i = 0; i < commands.length; i++) {
			if(this.errors.hasErrors())
				return RoverConstants.EXEC_ERROR;
		
			if(!RoverUtils.isNull(commands[i]))
				commands[i].executeCommand(this);
		}		
		return RoverConstants.EXEC_SUCCESS;
	}	

	public RoverErrors getRoverErrors() {
		return errors;
	}

	@Override
	public String toString() {
		return this.position.x + " " + this.position.y + " " + this.direction.getDirectionChar();
	}
}
