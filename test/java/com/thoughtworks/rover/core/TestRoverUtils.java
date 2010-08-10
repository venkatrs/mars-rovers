package com.thoughtworks.rover.core;

import java.awt.Point;

import com.thoughtworks.rover.BaseTestCase;

public class TestRoverUtils extends BaseTestCase {
	
	/**
	 * Method for testing RoverUtils.resetIfExceededMaxLimits(position);
	 * 
	 * Case#1: Pass the Position as null and assert for the method returning false.
	 * Case#2: Pass a Position with x-coordinate exceeding max limits. 
	 *         Assert if the method resets the value within the limits. Also assert if the method returns true.
	 * Case#3: Pass a Position with y-coordinate exceeding max limits.
	 * 	       Assert if the method resets the value within the limits. Also assert if the method returns true.
	 * Case#4: Pass a position with values within limits. Assert if the value returned is false and no values are modified.
	 */
	public void testResetIfExceededMaxLimits() {
		//Case#1: Pass the Position as null and assert for the method returning false.
		Point roverPosition = null;
		assertFalse("Method should return false!", RoverUtils.resetIfExceededMaxLimits(roverPosition));
		
		// Case#2: Pass a Position with x-coordinate exceeding max limits. 
		//         Assert if the method resets the value within the limits. Also assert if the method returns true.
		Point maxPosition = RoverContext.getMaxPosition();
		Point initialPosition = new Point(maxPosition.x + RoverConstants.STEP_ONE, maxPosition.y);
		roverPosition = new Point(initialPosition);
		assertTrue("Method should return true!", RoverUtils.resetIfExceededMaxLimits(roverPosition));
		assertEquals("x-coordinate should have been reset!", maxPosition.x, roverPosition.x);
		assertEquals("y-coordinate should have been the same!", initialPosition.y, roverPosition.y);
		
		// Case#3: Pass a Position with y-coordinate exceeding max limits.
		// 	       Assert if the method resets the value within the limits. Also assert if the method returns true.
		initialPosition = new Point(maxPosition.x, maxPosition.y  + RoverConstants.STEP_ONE);
		roverPosition = new Point(initialPosition);
		assertTrue("Method should return true!", RoverUtils.resetIfExceededMaxLimits(roverPosition));
		assertEquals("y-coordinate should have been reset!", maxPosition.y, roverPosition.y);
		assertEquals("x-coordinate should have been the same!", initialPosition.x, roverPosition.x);
		
		//Case#4: Pass a position with values within limits. Assert if the value returned is false and no values are modified.
		initialPosition = new Point(maxPosition.x, maxPosition.y);
		roverPosition = new Point(initialPosition);
		assertFalse("Method should return false!", RoverUtils.resetIfExceededMaxLimits(roverPosition));
		assertEquals("x-coordinate should have been the same!", initialPosition.x, roverPosition.x);
		assertEquals("y-coordinate should have been the same!", initialPosition.y, roverPosition.y);
	}

	/**
	 * Method for testing RoverUtils.resetIfBelowMinLimits(position);
	 * 
	 * Case#1: Pass the Position as null and assert for the method returning false.
	 * Case#2: Pass a Position with x-coordinate is set below the min limits. 
	 *         Assert if the method resets the value within the limits. Also assert if the method returns true.
	 * Case#3: Pass a Position with y-coordinate is set below the min limits.
	 * 	       Assert if the method resets the value within the limits. Also assert if the method returns true.
	 * Case#4: Pass a position with values within limits. Assert if the value returned is false and no values are modified.
	 */
	public void testResetIfBelowMinLimits() {
		//Case#1: Pass the Position as null and assert for the method returning false.
		Point roverPosition = null;
		assertFalse("Method should return false!", RoverUtils.resetIfBelowMinLimits(roverPosition));
		
		// Case#2: Pass a Position with x-coordinate is set below the min limits. 
		//         Assert if the method resets the value within the limits. Also assert if the method returns true.
		Point minPosition = RoverContext.getMinPosition();
		Point initialPosition = new Point(minPosition.x - RoverConstants.STEP_ONE, minPosition.y);
		roverPosition = new Point(initialPosition);
		assertTrue("Method should return true!", RoverUtils.resetIfBelowMinLimits(roverPosition));
		assertEquals("x-coordinate should have been reset!", minPosition.x, roverPosition.x);
		assertEquals("y-coordinate should have been the same!", initialPosition.y, roverPosition.y);
		
		// Case#3: Pass a Position with y-coordinate is set below the min limits.
		// 	       Assert if the method resets the value within the limits. Also assert if the method returns true.
		initialPosition = new Point(minPosition.x, minPosition.y  - RoverConstants.STEP_ONE);
		roverPosition = new Point(initialPosition);
		assertTrue("Method should return true!", RoverUtils.resetIfBelowMinLimits(roverPosition));
		assertEquals("y-coordinate should have been reset!", minPosition.y, roverPosition.y);
		assertEquals("x-coordinate should have been the same!", initialPosition.x, roverPosition.x);
		
		//Case#4: Pass a position with values within limits. Assert if the value returned is false and no values are modified.
		initialPosition = new Point(minPosition.x, minPosition.y);
		roverPosition = new Point(initialPosition);
		assertFalse("Method should return false!", RoverUtils.resetIfBelowMinLimits(roverPosition));
		assertEquals("x-coordinate should have been the same!", initialPosition.x, roverPosition.x);
		assertEquals("y-coordinate should have been the same!", initialPosition.y, roverPosition.y);
	}
	
	/**
	 * Method for testing RoverUtils.isExceedingMaxLimits(position)
	 * 
	 * Case#1: Pass position as null. Assert for the method returning false.
	 * Case#2: Pass a valid position which exceeds the maxlimits via x-coordinate. Assert for method returning true.
	 * Case#3: Pass a valid position which exceeds the maxlimits via y-coordinate. Assert for method returning true.
	 * Case#4: Pass a valid position with x, y coordinates within limits. Assert for the method returning false. 
	 */
	public void testIsExceedingMaxLimits() {
		//Case#1: Pass position as null. Assert for the method returning false.
		Point roverPosition = null;
		assertFalse("Method should return false!", RoverUtils.isExceedingMaxLimits(roverPosition));
		
		//Case#2: Pass a valid position which exceeds the maxlimits via x-coordinate. Assert for method returning true.
		Point maxPosition = RoverContext.getMaxPosition();
		Point initialPosition = new Point(maxPosition.x + RoverConstants.STEP_ONE, maxPosition.y);
		roverPosition = new Point(initialPosition);
		assertTrue("Method should return true!", RoverUtils.isExceedingMaxLimits(roverPosition));
		assertEquals("x-coordinate should have been the same!", initialPosition.x, roverPosition.x);
		assertEquals("y-coordinate should have been the same!", initialPosition.y, roverPosition.y);
		
		//Case#3: Pass a valid position which exceeds the maxlimits via y-coordinate. Assert for method returning true.
		initialPosition = new Point(maxPosition.x, maxPosition.y  + RoverConstants.STEP_ONE);
		roverPosition = new Point(initialPosition);
		assertTrue("Method should return true!", RoverUtils.isExceedingMaxLimits(roverPosition));		
		assertEquals("x-coordinate should have been the same!", initialPosition.x, roverPosition.x);
		assertEquals("y-coordinate should have been the same!", initialPosition.y, roverPosition.y);
		
		//Case#4: Pass a valid position with x, y coordinates within limits. Assert for the method returning false.
		initialPosition = new Point(maxPosition.x, maxPosition.y);
		roverPosition = new Point(initialPosition);
		assertFalse("Method should return false!", RoverUtils.isExceedingMaxLimits(roverPosition));
		assertEquals("x-coordinate should have been the same!", initialPosition.x, roverPosition.x);
		assertEquals("y-coordinate should have been the same!", initialPosition.y, roverPosition.y);
	}

	/**
	 * Method for testing RoverUtils.isBelowMinLimits(position)
	 * 
	 * Case#1: Pass position as null. Assert for the method returning false.
	 * Case#2: Pass a valid position which is set below the minlimits via x-coordinate. Assert for method returning true.
	 * Case#3: Pass a valid position which is set below the minlimits via y-coordinate. Assert for method returning true.
	 * Case#4: Pass a valid position with x, y coordinates within limits. Assert for the method returning false. 
	 */
	public void testIsBelowMinLimits() {
		//Case#1: Pass position as null. Assert for the method returning false.
		Point roverPosition = null;
		assertFalse("Method should return false!", RoverUtils.isBelowMinLimits(roverPosition));
		
		//Case#2: Pass a valid position which is set below the minlimits via x-coordinate. Assert for method returning true.
		Point minPosition = RoverContext.getMinPosition();
		Point initialPosition = new Point(minPosition.x - RoverConstants.STEP_ONE, minPosition.y);
		roverPosition = new Point(initialPosition);
		assertTrue("Method should return true!", RoverUtils.isBelowMinLimits(roverPosition));
		assertEquals("x-coordinate should have been the same!", initialPosition.x, roverPosition.x);
		assertEquals("y-coordinate should have been the same!", initialPosition.y, roverPosition.y);
		
		//Case#3: Pass a valid position which is set below the minlimits via y-coordinate. Assert for method returning true.
		initialPosition = new Point(minPosition.x, minPosition.y  - RoverConstants.STEP_ONE);
		roverPosition = new Point(initialPosition);
		assertTrue("Method should return true!", RoverUtils.isBelowMinLimits(roverPosition));		
		assertEquals("x-coordinate should have been the same!", initialPosition.x, roverPosition.x);
		assertEquals("y-coordinate should have been the same!", initialPosition.y, roverPosition.y);
		
		//Case#4: Pass a valid position with x, y coordinates within limits. Assert for the method returning false.
		initialPosition = new Point(minPosition.x, minPosition.y);
		roverPosition = new Point(initialPosition);
		assertFalse("Method should return false!", RoverUtils.isBelowMinLimits(roverPosition));
		assertEquals("x-coordinate should have been the same!", initialPosition.x, roverPosition.x);
		assertEquals("y-coordinate should have been the same!", initialPosition.y, roverPosition.y);
	}
	
	/**
	 * Method for testing RoverUtils.isValidInput()
	 * 
	 * Case#1: Pass the patternString as null. Assert for method returning false.
	 * Case#2: Pass the input as null. Assert for method returning false.
	 * Case#3: Pass a patternString and invalid input. Assert for method returning false.
	 * Case#4: Pass a patternString and a valid matching input. Assert for method returning false.
	 */
	public void testIsValidInput() {
		//Case#1: Pass the patternString as null. Assert for method returning false.
		assertFalse("isValidInput() should return false!", RoverUtils.isValidInput(null, "input"));
		
		//Case#2: Pass the input as null. Assert for method returning false.
		assertFalse("isValidInput() should return false!", RoverUtils.isValidInput("patternStr", null));
		
		//Case#3: Pass a patternString and invalid input. Assert for method returning false.
		assertFalse("isValidInput() should return false!", RoverUtils.isValidInput(RoverConstants.REGEX_ROVER_POSITION, ""));
		
		//Case#4: Pass a patternString and a valid matching input. Assert for method returning false.
		assertTrue("isValidInput() should return true!", RoverUtils.isValidInput(RoverConstants.REGEX_ROVER_POSITION, "1 1 N"));
	}
	
	/**
	 * Method for testing RoverUtils.isNull()
	 * 
	 * Case#1: Pass a String which is null. Assert for the method returning true.
	 * Case#2: Pass a String which is not null. Assert for the method returning false.
	 */
	public void testIsNull() {
		//Case#1: Pass a String which is null. Assert for the method returning true.
		String input = null;
		assertTrue("isNull() should return true!", RoverUtils.isNull(input));
		
		//Case#2: Pass a String which is not null. Assert for the method returning false.
		input = "";
		assertFalse("isNull() should return false!", RoverUtils.isNull(input));
	}
	
	/**
	 * Method for testing RoverUtils.getPrintablePoint()
	 * 
	 * Case#1: Pass a Point which is null. Assert if the method returns a empty string.
	 * Case#2: Pass a valid Point. Assert if the method returns a printable version.
	 */
	public void testGetPrintablePoint() {
		//Case#1: Pass a Point which is null. Assert if the method returns a empty string.
		Point input = null;
		assertEquals("Should return a empty string!", "", RoverUtils.getPrintablePoint(input));
		
		//Case#2: Pass a valid Point. Assert if the method returns a printable version.
		input = new Point(1, 2);
		assertEquals("Should return the appropriate string!", "[" + input.x + ", " + input.y + "]", RoverUtils.getPrintablePoint(input));
	}
}
