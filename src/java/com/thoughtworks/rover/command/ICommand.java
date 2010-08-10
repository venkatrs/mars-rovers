package com.thoughtworks.rover.command;

import com.thoughtworks.rover.core.Rover;

public interface ICommand {
	void executeCommand(Rover rover);
}
