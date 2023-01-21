package com.waiyanhtet.spring.data.jpa.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.waiyanhtet.spring.data.jpa.repo.custom.generic.BaseRepositoryImpl;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@ComponentScan(basePackages = "com.waiyanhtet.spring.data.jpa")
@EnableJpaRepositories(basePackages = "com.waiyanhtet.spring.data.jpa.repo",
						repositoryBaseClass = BaseRepositoryImpl.class)
@EnableTransactionManagement
public class JpaConfiguration {
	
	@Bean
	DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.HSQL)
				.build();
	}
	
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource datasource) throws Exception {
		var bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(datasource);
		bean.setPackagesToScan("com.waiyanhtet.spring.data.jpa.entity");
		bean.setJpaProperties(jpaProperties());
		bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return bean;
	}
	
	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
	
	private Properties jpaProperties() throws Exception {
		var properties = new Properties();
		properties.load(getClass().getResourceAsStream("/jpa.properties"));
		return properties;
	}

}
