package io.hatdog.ysf.repository.query_builder;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import io.hatdog.ysf.controller.argument.ListSpotArgument;
import io.hatdog.ysf.domain.Spot;

@Component
public class SpotRepositoryQueryBuilderImplementation implements SpotRepositoryQueryBuilder {

	private final String GET_NEARBY_SPOT_SQL = "SELECT *, (3956 * 2 * ASIN(SQRT( POWER(SIN((:latitude - ABS(dest.latitude)) * pi()/180 / 2),2) + COS(:latitude * pi()/180 ) * COS(ABS(dest.latitude) *  pi()/180) * POWER(SIN((:longitude - dest.longitude) *  pi()/180 / 2), 2) ))) AS distance FROM spot dest ORDER BY distance ASC";
	private final String GET_NEARBY_SPOT_SQL_LATITUDE_PARAMETER = "latitude";
	private final String GET_NEARBY_SPOT_SQL_LONGITUDE_PARAMETER = "longitude";

	@Override
	public SQLQuery buildGetNearbySpotQuery(ListSpotArgument listSpotArgument, Session session) {
		SQLQuery query = session.createSQLQuery(GET_NEARBY_SPOT_SQL);

		query.addEntity(Spot.class);
		query.setBigDecimal(GET_NEARBY_SPOT_SQL_LATITUDE_PARAMETER, listSpotArgument.getLatitude());
		query.setBigDecimal(GET_NEARBY_SPOT_SQL_LONGITUDE_PARAMETER, listSpotArgument.getLongitude());
		query.setMaxResults(listSpotArgument.getRecordLimit());
		
		return query;
	}

}
