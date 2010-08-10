package com.thoughtworks.rover;

import com.thoughtworks.rover.input.RoverFileInputHandler;


public class RunRoversMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		try {
			System.out.println("Starting Execution...");
			
			if(args.length <= 0) {
				System.out.println("Error: Please enter a valid input file name as an argument!");
				System.out.println("\tFor Example, $PROMPT:> java com.thoughtworks.rover.RunRoversMain validInput.txt");
				return;
			}
			String inputFileName = args[0];	
			RoverFileInputHandler roverHandler = RoverFileInputHandler.getInstance(inputFileName);
			roverHandler.executeCommands();
		} finally {
			System.out.println("End of Execution...");
		}
	}
}
