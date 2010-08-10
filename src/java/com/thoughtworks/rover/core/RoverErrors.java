package com.thoughtworks.rover.core;

import java.util.ArrayList;
import java.util.List;

public class RoverErrors {
	
	private final List<String> errorsList = new ArrayList<String>();
	
	public void addErrorMessage(String errorMessage) {
		if(!RoverUtils.isNull(errorMessage) && errorMessage.length() > 0)
			errorsList.add(errorMessage);		
	}
	
	public boolean hasErrors() {
		return !errorsList.isEmpty();
	}
	
	public List<String> getErrorMessages() {
		return this.errorsList;
	}
}
