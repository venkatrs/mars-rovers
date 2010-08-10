package com.thoughtworks.rover.direction;

import com.thoughtworks.rover.core.RoverConstants;


public enum Direction {
	
	NORTH (RoverConstants.DIRECTION_NORTH_CHAR, new NorthMgr()), 
	SOUTH (RoverConstants.DIRECTION_SOUTH_CHAR, new SouthMgr()), 
	EAST (RoverConstants.DIRECTION_EAST_CHAR, new EastMgr()), 
	WEST (RoverConstants.DIRECTION_WEST_CHAR, new WestMgr());
	
	private final char directionChar;
	private final DirectionMgr directionMgr;
	
	Direction( char directionChar, DirectionMgr direction ) 
	{
		this.directionChar = directionChar;
		this.directionMgr = direction;
	}
	
	public char getDirectionChar() 
	{
		return this.directionChar;
	}
	
	public DirectionMgr getDirectionMgr() {
		return this.directionMgr;
	}

	/**
	 * This method helps to get the enum instance of any direction based on the
	 * direction character provided as an input argument.
	 * 
	 * @param directionChar character representing the direction.
	 * @return Direction based on the given direction character. If not found return null.
	 */
	public static Direction getDirection(char directionChar) 
	{
		for(Direction direction: Direction.values()) {
			if(direction.getDirectionChar() == directionChar)
				return direction;
		}
		return null;
	}

}
