package com.thoughtworks.rover.exception;

public class InvalidInputException extends RuntimeException {
	
	private Object input;
	private String message;
	
	public InvalidInputException(Object input, String message) {
		this.input = input;
		this.message = message;
	}

	@Override
	public String getMessage() {
		return String.format("Error: Invalid input: [%s] - %s", input, message);
	}

}
