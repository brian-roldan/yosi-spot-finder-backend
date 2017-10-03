package io.hatdog.ysf.repository.query_builder;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.math.BigDecimal;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import io.hatdog.ysf.controller.argument.ListSpotArgument;
import io.hatdog.ysf.domain.Spot;

public class SpotRepositoryQueryBuilderImplementationTest {

	SpotRepositoryQueryBuilderImplementation queryBuilder = new SpotRepositoryQueryBuilderImplementation();
	
	@Mock Session session;
	@Mock SQLQuery query;

	final BigDecimal TEST_LATITUDE = new BigDecimal(14.553016);
	final BigDecimal TEST_LONGITUDE = new BigDecimal(121.049116);
	final Integer TEST_LIMIT = 5;
	
	@Before
	public void setUp(){
		initMocks(this);
	}
	
	@Test
	public void basicTest(){
		ListSpotArgument listSpotArgument = ListSpotArgument.builder().latitude(TEST_LATITUDE).longitude(TEST_LONGITUDE).recordLimit(TEST_LIMIT).build();
		when(session.createSQLQuery(anyString())).thenReturn(query);
		
		SQLQuery returnQuery = queryBuilder.buildGetNearbySpotQuery(listSpotArgument, session);
		
		assertNotNull(returnQuery);
		
		verify(session, times(1)).createSQLQuery(anyString());
		verify(query, times(1)).addEntity(eq(Spot.class));
		verify(query, times(1)).setBigDecimal(eq("latitude"), eq(listSpotArgument.getLatitude()));
		verify(query, times(1)).setBigDecimal(eq("longitude"), eq(listSpotArgument.getLongitude()));
		verify(query, times(1)).setMaxResults(eq(listSpotArgument.getRecordLimit()));	
	}
	
}
