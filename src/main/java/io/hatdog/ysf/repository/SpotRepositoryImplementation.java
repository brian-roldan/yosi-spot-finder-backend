package io.hatdog.ysf.repository;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import io.hatdog.ysf.controller.argument.ListSpotArgument;
import io.hatdog.ysf.domain.Spot;
import io.hatdog.ysf.repository.query_builder.SpotRepositoryQueryBuilder;

@Repository
public class SpotRepositoryImplementation implements SpotRepository {
	
	private final SessionFactory sessionFactory;
	private final SpotRepositoryQueryBuilder queryBuilder;
	
	public SpotRepositoryImplementation(SessionFactory sessionFactory, SpotRepositoryQueryBuilder queryBuilder) {
		super();
		this.sessionFactory = sessionFactory;
		this.queryBuilder = queryBuilder;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Spot> getNearbySpots(ListSpotArgument argument) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		SQLQuery query = queryBuilder.buildGetNearbySpotQuery(argument, session);
		
		List<Spot> spots = (List<Spot>) query.list();
		
		session.close();
		
		return spots;
	}
	
	
}
