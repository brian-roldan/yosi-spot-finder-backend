package io.hatdog.ysf.repository;

import java.util.List;

import io.hatdog.ysf.controller.argument.ListSpotArgument;
import io.hatdog.ysf.domain.Spot;

public interface SpotRepository {

	public List<Spot> getNearbySpots(ListSpotArgument argument);
	
}
