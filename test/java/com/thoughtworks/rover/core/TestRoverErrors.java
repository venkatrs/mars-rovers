package com.thoughtworks.rover.core;

import com.thoughtworks.rover.BaseTestCase;

public class TestRoverErrors extends BaseTestCase {
	
	private RoverErrors roverErrors;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		roverErrors = new RoverErrors();
	}

	/**
	 * Method for testing RoverErrors.hasErrors()
	 * 
	 * Case#1: Instantiate a new empty RoverErrors. Assert for method returning false.
	 * Case#2: Add an error message. Assert for method returning true.
	 */
	public void testHasErrors() {
		//Case#1: Instantiate a new empty RoverErrors. Assert for method returning false.
		RoverErrors roverErrors = new RoverErrors();
		assertFalse("hasErrors() should return false!", roverErrors.hasErrors());
		
		//Case#2: Add an error message. Assert for method returning true.
		roverErrors.addErrorMessage(RoverConstants.ERR_CROSSED_MAX_LIMITS);
		assertTrue("Error message should have been added!", roverErrors.hasErrors());
	}
	
	/**
	 * Method for testing RoverErrors.getErrorMessages()
	 * 
	 * Case#1: With no errors added. Assert for an empty list returned.
	 * Case#2: With error messages added. Assert for non-empty list returned. Also assert the values in the list.
	 */
	public void testGetErrorMessages() {
		//Case#1: With no errors added. Assert for an empty list returned.
		RoverErrors roverErrors = new RoverErrors();
		assertTrue("getErrorMessages should return an empty list!", roverErrors.getErrorMessages().isEmpty());
		
		//Case#2: With error messages added. Assert for non-empty list returned. Also assert the values in the list.
		roverErrors.addErrorMessage(RoverConstants.ERR_CROSSED_MAX_LIMITS);
		assertTrue("Error message should have been added!", roverErrors.hasErrors());
		assertEquals("Errors collection should be of size 1", 1, roverErrors.getErrorMessages().size());
		assertEquals("Errors collection doesnt have the same error message added!", 
						RoverConstants.ERR_CROSSED_MAX_LIMITS, roverErrors.getErrorMessages().get(0));		
	}
	
	/**
	 * Method for testing RoverErrors.addErrorMessage(error)
	 * 
	 * Case#1: Add a null value to the RoverErrors. Assert for value not added to the list.
	 * Case#2: Add an empty string value to the RoverErrors. Assert for value not added to the list.
	 * Case#3: Add a valid error message. Assert for the value is added to the list.
	 */
	public void testAddErrorMessage() {
		//Case#1: Add a null value to the RoverErrors. Assert for value not added to the list.
		roverErrors.addErrorMessage(null);
		assertFalse("There should be no errors added!", roverErrors.hasErrors());
		
		//Case#2: Add an empty string value to the RoverErrors. Assert for value not added to the list.
		roverErrors.addErrorMessage("");
		assertFalse("There should be no errors added!", roverErrors.hasErrors());
		
		//Case#3: Add a valid error message. Assert for the value is added to the list.
		roverErrors.addErrorMessage(RoverConstants.ERR_CROSSED_MAX_LIMITS);
		assertTrue("Error message should have been added!", roverErrors.hasErrors());
		assertEquals("Errors collection should be of size 1", 1, roverErrors.getErrorMessages().size());
		assertEquals("Errors collection doesnt have the same error message added!", 
						RoverConstants.ERR_CROSSED_MAX_LIMITS, roverErrors.getErrorMessages().get(0));
	}
}
