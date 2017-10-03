package io.hatdog.ysf.validator;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import io.hatdog.ysf.controller.argument.ListSpotArgument;

public class ListSpotArgumentValidatorImplementationTest {

	ListSpotArgumentValidatorImplementation validator;
	final int MAX_RECORD_LIMIT = 20;

	final BigDecimal CORRECT_LATITUDE = new BigDecimal("12.21");
	final BigDecimal CORRECT_LONGITUDE = new BigDecimal("12.21");
	final int CORRECT_RECORD_LIMIT = 10;

	@Before
	public void setUp() {
		validator = new ListSpotArgumentValidatorImplementation(MAX_RECORD_LIMIT);
	}

	@Test
	public void properRecordLimitTest() {

		ListSpotArgument argument = ListSpotArgument.builder().latitude(CORRECT_LATITUDE).longitude(CORRECT_LONGITUDE)
				.recordLimit(CORRECT_RECORD_LIMIT).build();

		validator.validate(argument);

	}

	@Test(expected = IllegalArgumentException.class)
	public void negativeRecordLimitTest() {

		ListSpotArgument argument = ListSpotArgument.builder().latitude(CORRECT_LATITUDE).longitude(CORRECT_LONGITUDE)
				.recordLimit(-1).build();

		validator.validate(argument);

	}

	@Test(expected = IllegalArgumentException.class)
	public void exceededRecordLimitTest() {

		ListSpotArgument argument = ListSpotArgument.builder().latitude(CORRECT_LATITUDE).longitude(CORRECT_LONGITUDE)
				.recordLimit(MAX_RECORD_LIMIT + 3).build();

		validator.validate(argument);

	}

	@Test(expected = IllegalArgumentException.class)
	public void blankLatitude() {
		ListSpotArgument argument = ListSpotArgument.builder().latitude(null).longitude(CORRECT_LONGITUDE)
				.recordLimit(CORRECT_RECORD_LIMIT).build();

		validator.validate(argument);
	}

	@Test(expected = IllegalArgumentException.class)
	public void blankLongitude() {
		ListSpotArgument argument = ListSpotArgument.builder().latitude(CORRECT_LATITUDE).longitude(null)
				.recordLimit(CORRECT_RECORD_LIMIT).build();

		validator.validate(argument);
	}

}
