package io.hatdog.ysf.repository;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import io.hatdog.ysf.controller.argument.ListSpotArgument;
import io.hatdog.ysf.domain.Spot;
import io.hatdog.ysf.repository.SpotRepositoryImplementation;

public class SpotRepositoryImplementationTest {

	SpotRepositoryImplementation spotRepository;
	
	@Mock SessionFactory sessionFactory;
	@Mock Session session;
	@Mock SQLQuery sqlQuery;
	
	final BigDecimal TEST_LATITUDE = new BigDecimal(14.553016);
	final BigDecimal TEST_LONGITUDE = new BigDecimal(121.049116);
	
	@Before	
	public void setUp(){
		initMocks(this);
		spotRepository = new SpotRepositoryImplementation(sessionFactory);
	}
	
	@Test
	public void basicPositiveTest(){
		
		ListSpotArgument argument = ListSpotArgument.builder().latitude(TEST_LATITUDE).longitude(TEST_LONGITUDE).build();
		
		when(sessionFactory.openSession()).thenReturn(session);
		when(session.createSQLQuery(anyString())).thenReturn(sqlQuery);
		when(sqlQuery.list()).thenReturn(Arrays.asList(new Spot(), new Spot()));
		
		List<Spot> spots = spotRepository.getNearbySpots(argument);
		
		assertNotNull(spots);
		
		verify(sessionFactory, times(1)).openSession();
		verify(session, times(1)).beginTransaction();
		verify(session, times(1)).close();
		
		verify(session, times(1)).createSQLQuery(anyString());
		verify(sqlQuery, times(1)).addEntity(eq(Spot.class));
		verify(sqlQuery, atLeast(1)).setBigDecimal(anyString(), eq(argument.getLongitude()));
		verify(sqlQuery, atLeast(1)).setBigDecimal(anyString(), eq(argument.getLatitude()));
		verify(sqlQuery, times(1)).list();
		
	}
	
	@Test(expected=NullPointerException.class)
	public void nullArgument(){
		when(sessionFactory.openSession()).thenReturn(session);
		when(session.createSQLQuery(anyString())).thenReturn(sqlQuery);
		
		spotRepository.getNearbySpots(null);
	}
	
}
