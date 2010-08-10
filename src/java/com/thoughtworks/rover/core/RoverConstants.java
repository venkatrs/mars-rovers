package com.thoughtworks.rover.core;


public final class RoverConstants {
	
	public static final String MAX_COORDINATES = "maxXY";	
	public static final String MIN_COORDINATES = "minXY";
	
	public static final int STEP_ONE = 1;
	
	public static final String EXEC_SUCCESS = "success";
	public static final String EXEC_ERROR = "error";
	
	public static final char COMMAND_VALIDATE_INITIAL_POSITION = 'C';
	public static final char COMMAND_TURN_LEFT = 'L';
	public static final char COMMAND_TURN_RIGHT = 'R';
	public static final char COMMAND_MOVE_FORWARD = 'M';

	public static final char[] COMMANDS = new char[] {
		COMMAND_VALIDATE_INITIAL_POSITION, 
		COMMAND_TURN_LEFT,
		COMMAND_TURN_RIGHT,
		COMMAND_MOVE_FORWARD
	};
	
	public static final char DIRECTION_NORTH_CHAR = 'N';
	public static final char DIRECTION_SOUTH_CHAR = 'S';
	public static final char DIRECTION_EAST_CHAR = 'E';
	public static final char DIRECTION_WEST_CHAR = 'W';
	
	public static final char[] DIRECTIONS = new char[] {
		DIRECTION_NORTH_CHAR,
		DIRECTION_SOUTH_CHAR,
		DIRECTION_EAST_CHAR,
		DIRECTION_WEST_CHAR
	};
	
	public static final String ERR_CROSSED_MIN_LIMITS = "Cannot move Rover below certain limits!";
	public static final String ERR_CROSSED_MAX_LIMITS = "Cannot move Rover beyond maximum limits!";
	public static final String ERR_NO_DIRECTION_AVBL  = "Rover needs to have a valid direction to execute the command!";
	public static final String ERR_NO_POSITION_AVBL  = "Rover needs to have a valid position to execute the command!";
	public static final String ERR_ROVER_IS_NULL = "Rover cannot be null!";

	public static final String REGEX_MAX_COORDINATE = "^[0-9]+ [0-9]+$";	
	public static final String REGEX_ROVER_POSITION = getRoverPositionRegEx();	
	public static final String REGEX_ROVER_COMMANDS = getRoverCommandsRegEx();
	
	/**
	 * 
	 * @return RegEx: "^[0-9]+ [0-9]+ [NSEW]?$"
	 */
	private static final String getRoverPositionRegEx() {
		StringBuilder regex = new StringBuilder(6);
		regex.append("^[0-9]+ [0-9]+ [");
		regex.append(DIRECTION_NORTH_CHAR);
		regex.append(DIRECTION_SOUTH_CHAR);
		regex.append(DIRECTION_EAST_CHAR);
		regex.append(DIRECTION_WEST_CHAR);
		regex.append("]?$");
		return regex.toString();
	}
	
	/**
	 * 
	 * @return RegEx: "^[LRM]*$"
	 */
	private static final String getRoverCommandsRegEx() {
		StringBuilder regex = new StringBuilder(6);
		regex.append("^[");
		regex.append(COMMAND_TURN_LEFT);
		regex.append(COMMAND_TURN_RIGHT);
		regex.append(COMMAND_MOVE_FORWARD);
		regex.append("]*$");
		return regex.toString();
	}
}
