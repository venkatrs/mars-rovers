package com.thoughtworks.rover.command;

import com.thoughtworks.rover.core.Rover;
import com.thoughtworks.rover.direction.Direction;
import com.thoughtworks.rover.direction.DirectionMgr;

/**
 * This Command Class is used for turning the given Rover 90 degrees on the right side.
 *    
 * @author Venkatesh
 *
 */
public class TurnRightCommand extends AbstractRoverCommand {

	@Override
	protected void preExecute(Rover rover) {
		super.preExecute(rover);
		super.validateForNoDirection(rover);
	}

	/**
	 * Method that sets the new direction to the given rover.
	 * @param rover whose direction is to be changed.
	 */
	@Override
	protected void execute(Rover rover) {
		DirectionMgr directionMgr = rover.getDirection().getDirectionMgr();
		Direction direction = directionMgr.turnRight();
		rover.setDirection(direction);
	}

}
