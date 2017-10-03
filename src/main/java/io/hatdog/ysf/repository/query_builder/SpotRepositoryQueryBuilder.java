package io.hatdog.ysf.repository.query_builder;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import io.hatdog.ysf.controller.argument.ListSpotArgument;

public interface SpotRepositoryQueryBuilder {

	public SQLQuery buildGetNearbySpotQuery(ListSpotArgument ListSpotArgument, Session session);
	
}
