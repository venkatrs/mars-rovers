package com.thoughtworks.rover.core;

import java.awt.Point;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoverUtils {
	
	public static boolean resetIfExceededMaxLimits(Point roverPosition) {
		if(isNull(roverPosition))
			return false;
		
		boolean exceededLimits = false;
		Point maxPosition = RoverContext.getMaxPosition();
		
		if(exceededLimits = roverPosition.x > maxPosition.x) {
			roverPosition.x = maxPosition.x;
		} else if (exceededLimits = roverPosition.y > maxPosition.y) {
			roverPosition.y = maxPosition.y;
		}
		return exceededLimits;		
	}
	
	public static boolean resetIfBelowMinLimits(Point roverPosition) {
		if(isNull(roverPosition))
			return false;
		
		boolean belowMinLimits = false;
		Point minPosition = RoverContext.getMinPosition();
		
		if(belowMinLimits = roverPosition.x < minPosition.x) {
			roverPosition.x = minPosition.x;
		} else if (belowMinLimits = roverPosition.y < minPosition.y) {
			roverPosition.y = minPosition.y;
		}
		return belowMinLimits;
	}	
	
	public static boolean isExceedingMaxLimits(Point roverPosition) {
		if(isNull(roverPosition))
			return false;
		return (RoverContext.getMaxPosition().x < roverPosition.x || RoverContext.getMaxPosition().y < roverPosition.y); 
	}
	
	public static boolean isBelowMinLimits(Point roverPosition) {
		if(isNull(roverPosition))
			return false;
		return (RoverContext.getMinPosition().x > roverPosition.x || RoverContext.getMinPosition().y > roverPosition.y); 
	}
	
	public static boolean isValidInput(String patternStr, String input) {
		if(isNull(patternStr) || isNull(input))
			return false;
		Pattern pattern = Pattern.compile(patternStr);		
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}
	
	public static boolean isNull(Object input) {
		return (input == null);
	}

	public static String getPrintablePoint(Point point) {
		return (isNull(point))? "" : "[" + point.x + ", " + point.y + "]";
	}

}
