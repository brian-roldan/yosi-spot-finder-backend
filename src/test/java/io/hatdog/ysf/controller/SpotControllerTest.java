package io.hatdog.ysf.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

import io.hatdog.ysf.controller.argument.ListSpotArgument;
import io.hatdog.ysf.controller.command.ListSpotCommand;
import io.hatdog.ysf.service.SpotService;

public class SpotControllerTest {

	SpotController spotController;
	
	MockMvc mockMvc;
	
	@Mock SpotService spotService;

	final BigDecimal TEST_LATITUDE = new BigDecimal(14.553016);
	final BigDecimal TEST_LONGITUDE = new BigDecimal(121.049116);
	final Integer TEST_RECORD_LIMIT = 10;
	
	@Before
	public void setUp(){
		initMocks(this);
		spotController = new SpotController(spotService);
		mockMvc = standaloneSetup(spotController).addPlaceholderValue("ysf.list.spot.default.limit", "10").build();
	}
	
	@Test
	public void behaviourTest(){
		when(spotService.getSpots(any())).thenReturn(new ListSpotCommand());
		
		ListSpotCommand returnCommand = spotController.getSpots(TEST_LATITUDE, TEST_LONGITUDE, null);
		
		assertNotNull(returnCommand);
		verify(spotService, times(1)).getSpots(any(ListSpotArgument.class));
	}
	
	@Test
	public void behaviourWithRecordLimitTest(){
		when(spotService.getSpots(any())).thenReturn(new ListSpotCommand());
		
		ListSpotCommand returnCommand = spotController.getSpots(TEST_LATITUDE, TEST_LONGITUDE, TEST_RECORD_LIMIT);
		
		assertNotNull(returnCommand);
		verify(spotService, times(1)).getSpots(any(ListSpotArgument.class));
	}
	
	@Ignore
	@Test
	public void basicPositiveRequestTest() throws Exception{
		mockMvc.perform(get("/spots/find?latitude=12.31&longitude=12.31")).andExpect(status().isOk());
	}
	
	@Test
	public void basicPositiveWithLimitRequestTest() throws Exception{
		mockMvc.perform(get("/spots/find?latitude=12.31&longitude=12.31&limit=3")).andExpect(status().isOk());
	}
	
	@Test
	public void missingLatitudeRequestTest() throws Exception{
		mockMvc.perform(get("/spots/find?longitude=12.31")).andExpect(status().isBadRequest());
	}
	
	@Test
	public void invalidLatitudeRequestTest() throws Exception{
		mockMvc.perform(get("/spots/find?latitude=a&longitude=12.31")).andExpect(status().isBadRequest());
	}
	
	@Test
	public void invalidHTTPMethodRequestTest() throws Exception{
		mockMvc.perform(post("/spots/find?latitude=12.31&longitude=12.31")).andExpect(status().isMethodNotAllowed());
	}
}
