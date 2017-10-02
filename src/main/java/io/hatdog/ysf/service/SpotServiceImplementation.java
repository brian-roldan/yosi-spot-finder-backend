package io.hatdog.ysf.service;

import org.springframework.stereotype.Service;

import io.hatdog.ysf.controller.argument.ListSpotArgument;
import io.hatdog.ysf.controller.command.ListSpotCommand;
import io.hatdog.ysf.repository.SpotRepository;

@Service
public class SpotServiceImplementation implements SpotService {

	private SpotRepository spotRepository;
	
	public SpotServiceImplementation(SpotRepository spotRepository) {
		super();
		this.spotRepository = spotRepository;
	}

	@Override
	public ListSpotCommand getSpots(ListSpotArgument argument) {
		ListSpotCommand listSpotCommand = new ListSpotCommand();
		
		listSpotCommand.setSpots(spotRepository.getNearbySpots(argument));
		
		return listSpotCommand;
	}

}
