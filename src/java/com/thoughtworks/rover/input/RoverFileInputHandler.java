package com.thoughtworks.rover.input;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.thoughtworks.rover.core.Rover;
import com.thoughtworks.rover.core.RoverConstants;
import com.thoughtworks.rover.core.RoverContext;
import com.thoughtworks.rover.core.RoverUtils;
import com.thoughtworks.rover.direction.Direction;
import com.thoughtworks.rover.exception.InvalidInputException;

public class RoverFileInputHandler {

	private final static int X_COORDINATE_INDEX = 0;
	private final static int Y_COORDINATE_INDEX = 1;
	private final static int DIRECTION_INDEX = 2;
	
	private static final RoverFileInputHandler ROVER_HANDLER = new RoverFileInputHandler();	
	
	private final Queue<RoverInput> roverInputsCollector = new LinkedList<RoverInput>();	
	protected List<String> errorMessagesCollector; 
	protected List<String> successResultsCollector;
	
	public static RoverFileInputHandler getInstance(String inputFileName) throws Exception {
		ROVER_HANDLER.initialize(inputFileName);
		return ROVER_HANDLER;
	}
	
	public void executeCommands() {
		try {
			errorMessagesCollector = new ArrayList<String>();
			successResultsCollector = new ArrayList<String>();
			
			Rover[] allRovers = RoverContext.getAllRovers();
			
			for(int i=0; i < allRovers.length; i++) {
				if(roverInputsCollector.isEmpty())
					break;
				
				Rover roverInAction = allRovers[i];
				RoverInput roverInputs = roverInputsCollector.poll();
				try {
					roverInAction = new Rover(roverInputs.getPosition(), roverInputs.getDirection());				
					
					String result = roverInAction.executeCommands(roverInputs.getCommands());
					
					if(RoverConstants.EXEC_ERROR.equals(result)) 
						printErrors(roverInAction);
					else
						printResult(roverInAction);
					
				} catch(Exception e) {
					System.out.println("UnExpected error occured while executing commands for rover:" + roverInAction);
					e.printStackTrace();
				}				
			}
		} catch(Exception e) {
			throw new RuntimeException("UnExpected error occurred!", e);
		} finally {
			if(!roverInputsCollector.isEmpty()) {
				roverInputsCollector.clear();
			}
		}
	}

	/****************** ------ private methods-----  ***/
	
	private void initialize(String inputFileName) throws IOException {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(new File(inputFileName)));
			
			String maxCoordinates = in.readLine();
			configureMaxMinPositions(maxCoordinates);
			
			collectRoverInputs(in);
			
		} finally {
			if(in != null)
				in.close();
		}
	}
	
	/****** -- Print Output Methods -- *******/
	
	private void ignoreInput(String positionInput, String commandsInput) {
		System.out.println("Ignoring excess Input:");
		System.out.println(positionInput);
		System.out.println(commandsInput);
	}
	
	private void printResult(Rover rover) {
		successResultsCollector.add(rover.toString());
		System.out.println(rover);
	}
	
	private void printErrors(Rover rover) {
		errorMessagesCollector.add(rover.getRoverErrors().getErrorMessages().toString());
		System.out.println(rover.getRoverErrors().getErrorMessages());	
	}
	
	/******* -- Data Collector Methods -- *******/
	
	private void collectRoverInputs(BufferedReader in) throws IOException {
		String positionInput = "";
		String commandsInput = "";
		int availableRoversCount = RoverContext.getAllRovers().length;
		
		while((positionInput = in.readLine()) != null) {
			commandsInput = in.readLine();
			if(roverInputsCollector.size() <= availableRoversCount)
				queueRoverInput(positionInput, commandsInput);
			else
				ignoreInput(positionInput, commandsInput);
		}
	}
	
	private void queueRoverInput(String positionInput, String commandsInput) {
		validatePosition(positionInput);
		validateCommands(commandsInput);
		this.roverInputsCollector.offer(new RoverInput(getPosition(positionInput), getDirection(positionInput), commandsInput));
	}
	
	private void configureMaxMinPositions(String maxCoordinates) {
		validateMaxCoordinates(maxCoordinates);
		String[] xyPoint = maxCoordinates.split(" ");
		Point maxPoint = new Point(Integer.parseInt(xyPoint[0]), Integer.parseInt(xyPoint[1]));
		RoverContext.addToContext(RoverConstants.MAX_COORDINATES, maxPoint);
		RoverContext.addToContext(RoverConstants.MIN_COORDINATES, new Point(0,0));
	}

	/******** --Input Validator methods -- ********/
	
	private void validateMaxCoordinates(String maxCoordinates) {
		if(!RoverUtils.isValidInput(RoverConstants.REGEX_MAX_COORDINATE, maxCoordinates)) {
			throw new InvalidInputException(maxCoordinates, "Invalid Max Coordinates!");
		}
	}

	private void validatePosition(String positionInput) {
		if(!RoverUtils.isValidInput(RoverConstants.REGEX_ROVER_POSITION, positionInput)) {
			throw new InvalidInputException(positionInput, "Invalid Position! Rover's position should be represented as [X Y D]");
		}
	}

	private void validateCommands(String commandsInput) {
		if(!RoverUtils.isValidInput(RoverConstants.REGEX_ROVER_COMMANDS, commandsInput)) {
			throw new InvalidInputException(commandsInput, "Invalid Commands! Rover's commands can only be L|M|R");
		}
	}
	
	/********* --helper methods-- **********/
	
	private Point getPosition(String positionWithDirection) {
		if(RoverUtils.isNull(positionWithDirection))
			return new Point(0,0);
		String[] xyd = positionWithDirection.split(" ");
		return new Point(Integer.parseInt(xyd[X_COORDINATE_INDEX]), Integer.parseInt(xyd[Y_COORDINATE_INDEX]));
	}
	
	private Direction getDirection(String positionWithDirection) {
		if(RoverUtils.isNull(positionWithDirection))
			return Direction.NORTH;
		String[] xyd = positionWithDirection.split(" ");
		return Direction.getDirection(xyd[DIRECTION_INDEX].charAt(0));
	}
}
