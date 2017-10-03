package io.hatdog.ysf.service;

import org.springframework.stereotype.Service;

import io.hatdog.ysf.controller.argument.ListSpotArgument;
import io.hatdog.ysf.controller.command.ListSpotCommand;
import io.hatdog.ysf.repository.SpotRepository;
import io.hatdog.ysf.validator.ListSpotArgumentValidator;

@Service
public class SpotServiceImplementation implements SpotService {

	private final SpotRepository spotRepository;
	private final ListSpotArgumentValidator listSpotArgumentValidator;
	
	public SpotServiceImplementation(SpotRepository spotRepository, ListSpotArgumentValidator listSpotArgumentValidator) {
		super();
		this.spotRepository = spotRepository;
		this.listSpotArgumentValidator = listSpotArgumentValidator;
	}

	@Override
	public ListSpotCommand getSpots(ListSpotArgument argument) {
		listSpotArgumentValidator.validate(argument);		
		
		ListSpotCommand listSpotCommand = new ListSpotCommand();
		
		listSpotCommand.setSpots(spotRepository.getNearbySpots(argument));
		
		return listSpotCommand;
	}

}
