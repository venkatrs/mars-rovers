package com.thoughtworks.rover.command;

import java.awt.Point;

import com.thoughtworks.rover.core.Rover;
import com.thoughtworks.rover.core.RoverConstants;
import com.thoughtworks.rover.core.RoverUtils;
import com.thoughtworks.rover.direction.DirectionMgr;

public class MoveForwardCommand extends AbstractRoverCommand {
	
	@Override
	protected void preExecute(Rover rover) {
		super.preExecute(rover);
		validateForNoDirection(rover);
		validateForNoPosition(rover);
	}

	@Override
	protected void execute(Rover rover) {
		DirectionMgr directionMgr = rover.getDirection().getDirectionMgr();
		directionMgr.moveForward(rover.getPosition());
	}
	
	@Override
	protected void postExecute(Rover rover) {
		Point roverPosition = rover.getPosition();
		
		if(RoverUtils.resetIfExceededMaxLimits(roverPosition)) 
		{
			rover.getRoverErrors().addErrorMessage(RoverConstants.ERR_CROSSED_MAX_LIMITS);
		} 
		
		if (RoverUtils.resetIfBelowMinLimits(roverPosition)) 
		{
			rover.getRoverErrors().addErrorMessage(RoverConstants.ERR_CROSSED_MIN_LIMITS);
		}
	}	
}
