package io.hatdog.ysf.validator;

import static java.lang.String.format;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.hatdog.ysf.controller.argument.ListSpotArgument;

@Component
public class ListSpotArgumentValidatorImplementation implements ListSpotArgumentValidator {

	private int maximumLimit;

	public ListSpotArgumentValidatorImplementation(@Value("${ysf.list.spot.maximum.limit}") int maximumLimit) {
		super();
		this.maximumLimit = maximumLimit;
	}

	@Override
	public void validate(ListSpotArgument argument) {
		if(isNull(argument.getLatitude())){
			throw new IllegalArgumentException("Latitude is a required argument.");
		} else if(isNull(argument.getLongitude())){
			throw new IllegalArgumentException("Longitude is a required argument.");
		}
		
		if(nonNull(argument.getRecordLimit())){
			if(argument.getRecordLimit() < 1){
				throw new IllegalArgumentException("Provided record limit cannot be zero or negative.");
			} else if(argument.getRecordLimit() > maximumLimit){
				throw new IllegalArgumentException(format("Provided record limit is greater than the maximum limit which is %d.", maximumLimit));
			}
		}
	}

}
