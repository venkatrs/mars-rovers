package com.thoughtworks.rover.command;

import java.awt.Point;

import com.thoughtworks.rover.core.Rover;
import com.thoughtworks.rover.core.RoverConstants;
import com.thoughtworks.rover.core.RoverUtils;

public class ValidateInitialPositionCommand extends AbstractRoverCommand {

	@Override
	protected void preExecute(Rover rover) {
		super.preExecute(rover);
		validateForNoPosition(rover);
		validateForNoDirection(rover);
	}

	@Override
	protected void execute(Rover rover) {
		//This command does nothing other than validating the rover's position.
		Point roverPosition = rover.getPosition();
		
		if(RoverUtils.isExceedingMaxLimits(roverPosition)) {
			rover.getRoverErrors().addErrorMessage(RoverConstants.ERR_CROSSED_MAX_LIMITS);
		}
		
		if(RoverUtils.isBelowMinLimits(roverPosition)) {
			rover.getRoverErrors().addErrorMessage(RoverConstants.ERR_CROSSED_MIN_LIMITS);
		}
	}
}
