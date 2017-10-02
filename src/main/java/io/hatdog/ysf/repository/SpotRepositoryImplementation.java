package io.hatdog.ysf.repository;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import io.hatdog.ysf.controller.argument.ListSpotArgument;
import io.hatdog.ysf.domain.Spot;

@Repository
public class SpotRepositoryImplementation implements SpotRepository {

	private final String GET_NEARBY_SPOT_SQL = "SELECT *, (3956 * 2 * ASIN(SQRT( POWER(SIN((:latitude - ABS(dest.latitude)) * pi()/180 / 2),2) + COS(:latitude * pi()/180 ) * COS(ABS(dest.latitude) *  pi()/180) * POWER(SIN((:longitude - dest.longitude) *  pi()/180 / 2), 2) ))) AS distance FROM spot dest ORDER BY distance ASC";
	private final String GET_NEARBY_SPOT_SQL_LATITUDE_PARAMETER = "latitude";
	private final String GET_NEARBY_SPOT_SQL_LONGITUDE_PARAMETER = "longitude";
	
	private SessionFactory sessionFactory;
	
	public SpotRepositoryImplementation(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spot> getNearbySpots(ListSpotArgument argument) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		SQLQuery query = session.createSQLQuery(GET_NEARBY_SPOT_SQL);
		
		query.addEntity(Spot.class);
		query.setBigDecimal(GET_NEARBY_SPOT_SQL_LATITUDE_PARAMETER, argument.getLatitude());
		query.setBigDecimal(GET_NEARBY_SPOT_SQL_LONGITUDE_PARAMETER, argument.getLongitude());
		
		List<Spot> spots = (List<Spot>) query.list();
		
		session.close();
		
		return spots;
	}
	
	
}
