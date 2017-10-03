package io.hatdog.ysf.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import io.hatdog.ysf.controller.argument.ListSpotArgument;
import io.hatdog.ysf.controller.command.ListSpotCommand;
import io.hatdog.ysf.repository.SpotRepository;
import io.hatdog.ysf.service.SpotServiceImplementation;
import io.hatdog.ysf.validator.ListSpotArgumentValidator;

public class SpotServiceImplementationTest {

	SpotServiceImplementation spotService;
	
	@Mock SpotRepository spotRepository;
	@Mock ListSpotArgumentValidator listSpotArgumentValidator;

	final BigDecimal TEST_LATITUDE = new BigDecimal(14.553016);
	final BigDecimal TEST_LONGITUDE = new BigDecimal(121.049116);
	
	@Before
	public void setUp(){
		initMocks(this);
		spotService = new SpotServiceImplementation(spotRepository, listSpotArgumentValidator);
	}
	
	@Test
	public void basicPositiveTest(){
		ListSpotArgument argument = ListSpotArgument.builder().latitude(TEST_LATITUDE).longitude(TEST_LONGITUDE).build();
		
		ListSpotCommand listSpotCommand = spotService.getSpots(argument);
		
		assertNotNull(listSpotCommand);
		verify(listSpotArgumentValidator, times(1)).validate(eq(argument));
		verify(spotRepository, times(1)).getNearbySpots(eq(argument));
	}
	
}
