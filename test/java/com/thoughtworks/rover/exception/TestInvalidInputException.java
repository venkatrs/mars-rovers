package com.thoughtworks.rover.exception;

import com.thoughtworks.rover.BaseTestCase;

public class TestInvalidInputException extends BaseTestCase {

	/**
	 * Method for testing the constructor.
	 * 
	 * Case#1: Pass in the appropriate parameter. Assert if the object is successfully instantiated.
	 */
	public void testConstructor() {
		InvalidInputException invalidInputException = new InvalidInputException("input", "message");
		assertNotNull("InvalidInputException instance cannot be null!", invalidInputException);
	}
	
	/**
	 * Method for testing InvalidInputException.getMessage()
	 * 
	 * Case#1: Instantiate a InvalidInputException. Assert for the proper message.
	 */
	public void testGetMessage() {
		String input = "Problemetic Input";
		String message = "Original Message will come here";
		
		InvalidInputException invalidInputException = new InvalidInputException(input, message);
		
		String obtainedMessage = invalidInputException.getMessage();
		assertNotNull("Obtained message cannot be null!", obtainedMessage);
		
		String expectedMessage = String.format("Error: Invalid input: [%s] - %s", input, message);
		assertEquals("InvalidInputException did not return the expected error message", expectedMessage, obtainedMessage);
	}
}
