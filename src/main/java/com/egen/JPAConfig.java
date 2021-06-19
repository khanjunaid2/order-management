package com.egen;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class JPAConfig {

	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean emf() {
		LocalContainerEntityManagerFactoryBean emf= new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setJpaProperties(jpaProperties());
		emf.setPackagesToScan("com.egen.model");
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return emf;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource d= new DriverManagerDataSource();
		d.setDriverClassName("com.mysql.cj.jdbc.Driver");
		d.setUrl("jdbc:mysql://localhost:3306/order_management");
		d.setPassword("root");
		d.setUsername("root");
		return d;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager();
	}

	private Properties jpaProperties() {
		Properties props = new Properties();
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
		props.put("hibernate.hbm2ddl.auto", "create-drop");
		props.put("hibernate.jdbc.time_zone", "UTC");
		return props;

	}
}
