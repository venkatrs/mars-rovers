package com.thoughtworks.rover.input;

import com.thoughtworks.rover.BaseTestCase;
import com.thoughtworks.rover.exception.InvalidInputException;

public class TestRoverFileInputHandler extends BaseTestCase {
	
	/**
	 * Method for testing RoverFileInputHandler.getInstance()
	 * 
	 * Case#1: Pass in a invalid input file. Assert for exception being thrown.
	 * Case#2: Pass in a valid input file. Assert the instance obtained for not null.
	 */
	public void testGetInstance() {
		//Case#1: Pass in a invalid input file. Assert for exception being thrown.
		try {
			RoverFileInputHandler.getInstance("invalidInput.txt");
			fail("RoverFileInputHandler.getInstance('invalidInput.txt') call should have thrown an exception!");
		} catch(InvalidInputException e) {
			assertNotNull("Message cannot be null!", e.getMessage());
		} catch(Exception e) {
			fail("This is not an expected exception!" +  e.getMessage());
		}

		//Case#2: Pass in a valid input file. Assert the instance obtained for not null.
		try {
			RoverFileInputHandler roverFileInputHandler = RoverFileInputHandler.getInstance("validInput.txt");
			assertNotNull("RoverFileInputHandler cannot be null!", roverFileInputHandler);
		} catch(Exception e) {
			fail("This time exception shouldn't have been thrown!");
		}
	}
	
	/**
	 * Method for testing RoverFileInputHandler.executeCommands()
	 * 
	 * Case#1: Pass in a valid input file with errors. Assert for Errors Collector not empty.
	 * Case#2: Pass in a valid input file with no errors. Assert for Results Collector not empty.
	 */
	public void testExecuteCommands() {
		try {
			RoverFileInputHandler roverFileInputHandler = RoverFileInputHandler.getInstance("validInputWithError.txt");			
			assertNotNull("RoverFileInputHandler cannot be null!", roverFileInputHandler);
			
			roverFileInputHandler.executeCommands();			
			assertFalse("Error Messages Collector cannot be empty!", roverFileInputHandler.errorMessagesCollector.isEmpty());
			
			roverFileInputHandler = RoverFileInputHandler.getInstance("validInput.txt");			
			assertNotNull("RoverFileInputHandler cannot be null!", roverFileInputHandler);
			
			roverFileInputHandler.executeCommands();			
			assertTrue("Error Messages Collector should be empty!", roverFileInputHandler.errorMessagesCollector.isEmpty());
			assertFalse("Success Results Collector cannot be empty!", roverFileInputHandler.successResultsCollector.isEmpty());
			
		} catch(Exception e) {
			fail("The exception is not supposed to occur during executing of rover commands!");
		}
	}

}
