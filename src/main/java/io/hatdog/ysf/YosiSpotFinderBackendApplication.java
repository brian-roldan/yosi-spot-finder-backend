package io.hatdog.ysf;

import javax.persistence.EntityManagerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

@SpringBootApplication
public class YosiSpotFinderBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(YosiSpotFinderBackendApplication.class, args);
	}
	
	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory entityManagerFactory) {
	    HibernateJpaSessionFactoryBean sessionFactoryBean = new HibernateJpaSessionFactoryBean();
	    sessionFactoryBean.setEntityManagerFactory(entityManagerFactory);
	    return sessionFactoryBean;
	}
}
