package com.thoughtworks.rover;

import java.awt.Point;

import com.thoughtworks.rover.core.RoverConstants;
import com.thoughtworks.rover.core.RoverContext;

import junit.framework.TestCase;

public class BaseTestCase extends TestCase {
	
	

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		RoverContext.addToContext(RoverConstants.MAX_COORDINATES, new Point(5, 5));
		RoverContext.addToContext(RoverConstants.MIN_COORDINATES, new Point(0,0));
	}

}
