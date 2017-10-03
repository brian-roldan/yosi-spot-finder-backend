package io.hatdog.ysf.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.hatdog.ysf.controller.argument.ListSpotArgument;
import io.hatdog.ysf.controller.command.ListSpotCommand;
import io.hatdog.ysf.service.SpotService;

@RestController
public class SpotController {

	private SpotService spotService;
	
	public SpotController(SpotService spotService) {
		super();
		this.spotService = spotService;
	}

	@GetMapping("/spots/find")
	public ListSpotCommand getSpots(@RequestParam(name="latitude") BigDecimal latitude, @RequestParam(name="longitude") BigDecimal longitude,
			@RequestParam(name="limit", defaultValue="${ysf.list.spot.default.limit}") Integer recordLimit){
		return spotService.getSpots(ListSpotArgument.builder().latitude(latitude).longitude(longitude).recordLimit(recordLimit).build());
	}
	
}
