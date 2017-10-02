package io.hatdog.ysf.controller.argument;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListSpotArgument {

	private BigDecimal latitude;
	private BigDecimal longitude;
	private Integer recordLimit;
	
}
