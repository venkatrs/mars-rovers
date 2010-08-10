package com.thoughtworks.rover.core;

import java.awt.Point;

import com.thoughtworks.rover.BaseTestCase;

public class TestRoverContext extends BaseTestCase {
	
	/**
	 * Method for testing RoverContext.addToContext(key, value)
	 * 
	 * Case#1: Add a valid key and null value. Assert for the object is added to the context.
	 * Case#2: Add a valid key and valid value. Assert for the object is added to the context.
	 */
	public void testAddToContext() {
		//Case#1: Add a valid key and null value. Assert for the object is added to the context.
		RoverContext.addToContext("key", null);
		assertNull("Value returned should be null", RoverContext.getFromContext("key"));
		
		//Case#2: Add a valid key and valid value. Assert for the object is added to the context.
		RoverContext.addToContext("key", "value");
		assertEquals("RoverContext did not returned the expected value!", "value", RoverContext.getFromContext("key"));
	}
	
	/**
	 * Method for testing RoverContext.removeFromContext(key)
	 * 
	 * Case#1: Try to remove a value whose key is not in the context. Assert for the context doesn't have the value.
	 * Case#2: Try to remove a value whose key is in the context. Assert for the key, value is removed from the context.
	 */
	public void testRemoveFromContext() {
		//Case#1: Try to remove a value whose key is not in the context. Assert for the context doesn't have the value.
		assertNull("Value with the key, 'abc' should not be available in the context!", RoverContext.getFromContext("abc"));
		RoverContext.removeFromContext("abc");
		assertNull("Value with the key, 'abc' should not be available in the context!", RoverContext.getFromContext("abc"));
		
		//Case#2: Try to remove a value whose key is in the context. Assert for the key, value is removed from the context.
		RoverContext.addToContext("abc", "toBeRemovedValue");
		assertNotNull("Value with the key, 'abc' should be available in the context!", RoverContext.getFromContext("abc"));
		RoverContext.removeFromContext("abc");
		assertNull("Value with the key, 'abc' should not be available in the context!", RoverContext.getFromContext("abc"));
	}
	/**
	 * Method for testing RoverContext.getFromContext(key)
	 * 
	 * Case#1: Try to get some value using a key which is not in the context. Assert for method returning null.
	 * Case#2: Try to get some value using a key which is available in the context. Assert for the right object being returned.
	 */
	public void getFromContext() {
		//Case#1: Try to get some value using a key which is not in the context. Assert for method returning null.
		assertNull("Value with the key, 'abc' should not be available in the context!", RoverContext.getFromContext("cde"));
		
		//Case#2: Try to get some value using a key which is available in the context. Assert for the right object being returned.
		RoverContext.addToContext("cde", "toBeObtained");
		assertNotNull("Value with the key, 'abc' should be available in the context!", RoverContext.getFromContext("cde"));
		assertEquals("Value from context should be equal to 'toBeObtained'", "toBeObtained", RoverContext.getFromContext("cde"));
	}
	
	/**
	 * Method for testing RoverContext.getMaxPosition()
	 * 
	 * Case#1: Try getting the Max position coordinates. Assert if the max position coordinates are correct.
	 */
	public void testGetMaxPosition() {
		Point maxPosition = (Point)RoverContext.getFromContext(RoverConstants.MAX_COORDINATES);
		assertNotNull("Max Position coordinates cannot be null!", maxPosition);
		
		Point obtainedMaxPosition = RoverContext.getMaxPosition();
		
		assertNotNull("Obtained Max Position coordinates cannot be null!", obtainedMaxPosition);
		assertEquals("Obtained Max Position is incorrect!", maxPosition, obtainedMaxPosition);
	}

	/**
	 * Method for testing RoverContext.getMinPosition()
	 * 
	 * Case#1: Try getting the Max position coordinates. Assert if the max position coordinates are correct.
	 */
	public void testGetMinPosition() {
		Point minPosition = (Point)RoverContext.getFromContext(RoverConstants.MIN_COORDINATES);
		assertNotNull("Min Position coordinates cannot be null!", minPosition);
		
		Point obtainedMinPosition = RoverContext.getMinPosition();
		
		assertNotNull("Obtained Min Position coordinates cannot be null!", obtainedMinPosition);
		assertEquals("Obtained Min Position is incorrect!", minPosition, obtainedMinPosition);
	}
	
	/**
	 * Method for testing RoverContext.getAllRovers()
	 * 
	 * Case#1: Try getting all the available rovers. Assert if the obtained rovers are same as defined.
	 */
	public void testGetAllRovers() {
		Rover[] allRovers = RoverContext.getAllRovers();
		assertNotNull("All Rovers collection cannot be null!", allRovers);
		assertEquals("Defined number of Rovers is different from obtained number of rovers!", 
															RoverContext.ALL_ROVERS.length, allRovers.length);
	}
}
