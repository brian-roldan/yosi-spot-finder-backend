package io.hatdog.ysf.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import io.hatdog.ysf.controller.argument.ListSpotArgument;
import io.hatdog.ysf.domain.Spot;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SpotRepositoryImplementationIT {

	@Autowired SpotRepositoryImplementation spotRepositoryImplementation;
	
	@Test
	public void insideTriangleCheckClosestTest(){
		ListSpotArgument argument = ListSpotArgument.builder().latitude(new BigDecimal("14.553016")).longitude(new BigDecimal("121.049116")).build();
		
		List<Spot> spots = spotRepositoryImplementation.getNearbySpots(argument);
		
		assertNotNull(spots);
		assertEquals(9, spots.size());;
		
		Spot closestSpot = spots.get(0);
		assertEquals(new BigDecimal("14.553828"), closestSpot.getLatitude());
		assertEquals(new BigDecimal("121.049462"), closestSpot.getLongitude());
	}
	
	@Test
	public void outsideTriangleCheckClosestTest(){
		ListSpotArgument argument = ListSpotArgument.builder().latitude(new BigDecimal("14.558664")).longitude(new BigDecimal("121.056214")).build();
		
		List<Spot> spots = spotRepositoryImplementation.getNearbySpots(argument);
		
		assertNotNull(spots);
		assertEquals(9, spots.size());
		
		Spot closestSpot = spots.get(0);
		assertEquals(new BigDecimal("14.556754"), closestSpot.getLatitude());
		assertEquals(new BigDecimal("121.054445"), closestSpot.getLongitude());
	}
}
