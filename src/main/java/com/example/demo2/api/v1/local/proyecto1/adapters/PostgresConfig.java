
package com.example.demo2.api.v1.local.proyecto1.adapters;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
/*
@EnableJpaRepositories(
    entityManagerFactoryRef = "postgresEntityManagerFactory", 
    transactionManagerRef = "postgresTransactionManager", 
	basePackages = { "com.example.demo2.api.v1.local.proyecto1"}
    //basePackages = {
    //"com.example.demo2.api.v1.local.proyecto1",
    //includeFilters = @Filter(type = FilterType.REGEX, pattern="com.example.demo2.api.v1.local.proyecto1.*.adapters",
    //} 
)
*/
@EnableJpaRepositories(
    entityManagerFactoryRef = "postgresEntityManagerFactory", 
    transactionManagerRef = "postgresTransactionManager"
    , basePackages = { "com.example.demo2.api.v1.local.proyecto1"}
    , includeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = {"com.example.demo2.api.v1.local.proyecto1.*.adapters.bd1.*"})
        //@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.example.demo2.api.v1.local.proyecto1.*.adapters.bd1.*")
    }
)
/*
@ComponentScan(
    basePackages = {"com.example.demo2.api.v1.local.proyecto1"},
    includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern =         "com.example.demo2.api.v1.local.proyecto1.*.adapters.bd1")
 ) */
public class PostgresConfig {
    
    //public String packages_models = "com.example.demo2.api.v1.local.proyecto1";
    public String packages_models = "com.example.demo2.api.v1.local.proyecto1.*.adapters.bd1";

    
    @Autowired
	private Environment env;
	
	@Bean(name = "postgresDataSource")
	public DataSource postgresDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("postgres.datasource.url"));
		dataSource.setUsername(env.getProperty("postgres.datasource.username"));
		dataSource.setPassword(env.getProperty("postgres.datasource.password"));
		dataSource.setDriverClassName(env.getProperty("postgres.datasource.driver-class-name"));
		return dataSource;
	}
    
    
    @Primary
    @Bean(name = "postgresEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(postgresDataSource());
		em.setPackagesToScan( this.packages_models );
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("postgres.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.show-sql", env.getProperty("postgres.jpa.show-sql"));
		//properties.put("hibernate.dialect", env.getProperty("postgres.jpa.database-platform"));
        properties.put("hibernate.dialect", env.getProperty("postgres.jpa.properties.hibernate.dialect"));
        //properties.put("spring.jpa.generate-ddl", env.getProperty("spring.jpa.generate-ddl"));
        //properties.put("logging.level.org.hibernate.SQL", env.getProperty("logging.level.org.hibernate.SQL"));
        //properties.put("logging.level.org.hibernate.type.descriptor.sql.BasicBinder", env.getProperty("logging.level.org.hibernate.type.descriptor.sql.BasicBinder"));
		
		em.setJpaPropertyMap(properties);
		return em;
	}
    
    
    @Primary
	@Bean(name = "postgresTransactionManager")
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
    
}
