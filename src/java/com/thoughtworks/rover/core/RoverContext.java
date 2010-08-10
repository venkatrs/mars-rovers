package com.thoughtworks.rover.core;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;


public class RoverContext {
	
	protected static final Rover[] ALL_ROVERS = new Rover[5];
	
	private final static Map<String, Object> CONTEXT = new HashMap<String, Object>();
	
	public static Object getFromContext(String key) {
		return CONTEXT.get(key);
	}
	
	public static void addToContext(String key, Object value) {
		CONTEXT.put(key, value);
	}
	
	public static void removeFromContext(String key) {
		CONTEXT.remove(key);		
	}
	
	public static Point getMaxPosition() {
		return (Point)CONTEXT.get(RoverConstants.MAX_COORDINATES);
	}
	
	public static Point getMinPosition() {
		return (Point)CONTEXT.get(RoverConstants.MIN_COORDINATES);
	}
	
	public static Rover[] getAllRovers() {
		return ALL_ROVERS;
	}
}
