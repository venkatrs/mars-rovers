package com.thoughtworks.rover.command;

import com.thoughtworks.rover.core.Rover;
import com.thoughtworks.rover.core.RoverConstants;
import com.thoughtworks.rover.exception.InvalidInputException;

public abstract class AbstractRoverCommand implements ICommand {
	
	protected void preExecute(Rover rover) {
		if(rover == null)
			throw new InvalidInputException(rover, "Rover Cannot be null!");
	}

	protected abstract void execute(Rover rover);
	
	public void executeCommand(Rover rover) {
		preExecute(rover);
		if(!rover.getRoverErrors().hasErrors()) {
			execute(rover);
			postExecute(rover);			
		}
	}
	
	protected void postExecute(Rover rover) {
		//TODO: Are rovers colliding.
	}
	
	protected void validateForNoDirection(Rover rover) {
		if(rover.getDirection() == null) 
			rover.getRoverErrors().addErrorMessage(RoverConstants.ERR_NO_DIRECTION_AVBL);
	}
	
	protected void validateForNoPosition(Rover rover) {
		if(rover.getPosition() == null)
			rover.getRoverErrors().addErrorMessage(RoverConstants.ERR_NO_POSITION_AVBL);
	}
}
