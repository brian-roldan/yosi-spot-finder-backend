package io.hatdog.ysf.service;

import io.hatdog.ysf.controller.argument.ListSpotArgument;
import io.hatdog.ysf.controller.command.ListSpotCommand;


public interface SpotService {

	public ListSpotCommand getSpots(ListSpotArgument argument);
	
}
