package com.thoughtworks.rover.command;

import com.thoughtworks.rover.core.Rover;
import com.thoughtworks.rover.direction.Direction;
import com.thoughtworks.rover.direction.DirectionMgr;

public class TurnLeftCommand extends AbstractRoverCommand {
	
	@Override
	protected void preExecute(Rover rover) {
		super.preExecute(rover);
		super.validateForNoDirection(rover);
	}

	@Override
	protected void execute(Rover rover) {
		DirectionMgr directionMgr = rover.getDirection().getDirectionMgr();
		Direction direction = directionMgr.turnLeft();
		rover.setDirection(direction);
	}
}
