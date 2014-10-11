package com.mm.boot.multidb;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@EnableJpaRepositories(
	    entityManagerFactoryRef = "orderEntityManager",
	    transactionManagerRef = "orderTransactionManager",
	    basePackages = {"com.mm.boot.multidb.repository.order"})
public class OrderDbConfig{

	@Bean(name = "orderEntityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] {"com.mm.boot.multidb.model.order"});
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalJpaProperties());
		em.setPersistenceUnitName("orders");

		return em;
	}
	
	//TODO: Can these be ontained from FlywayDB
	Properties additionalJpaProperties(){
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		
		return properties;
	}
	
	//TODO: Change to FlwywayDB
	@Bean
	public DataSource dataSource(){
		return DataSourceBuilder.create()
				.url("jdbc:h2:mem:order:H2")
				.driverClassName("org.h2.Driver")
				.username("sa")
				.password("")
				.build();
	}	
	
	@Bean(name = "orderTransactionManager")
	public JpaTransactionManager transactionManager(EntityManagerFactory orderEntityManager){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(orderEntityManager);
		
		return transactionManager;
	}
}

