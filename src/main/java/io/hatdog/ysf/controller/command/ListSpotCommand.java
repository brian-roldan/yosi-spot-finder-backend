package io.hatdog.ysf.controller.command;

import java.util.List;

import io.hatdog.ysf.domain.Spot;
import lombok.Data;

@Data
public class ListSpotCommand {

	private List<Spot> spots;
	
}
