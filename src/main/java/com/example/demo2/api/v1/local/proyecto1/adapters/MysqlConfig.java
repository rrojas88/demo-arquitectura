
package com.example.demo2.api.v1.local.proyecto1.adapters;


//public class MysqlConfig { }

/*  */
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
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
@EnableJpaRepositories(
    entityManagerFactoryRef = "mysqlEntityManagerFactory", 
    transactionManagerRef = "mysqlTransactionManager"
    //, basePackages = { "com.example.demo2.api.v1.local.proyecto1"}
    , basePackages = "com.example.demo2.api.v1.local.proyecto1"
    , includeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = {"com.example.demo2.api.v1.local.proyecto1.*.adapters.bd2.*"})
    } 
    , excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = {"com.example.demo2.api.v1.local.proyecto1.*.adapters.bd1s.*"})
    }
)
public class MysqlConfig {
    
    public String packages_models = "com.example.demo2.api.v1.local.proyecto1.*.adapters.bd2";
    
    @Autowired
    private Environment env;
    
    @Bean(name = "mysqlDataSource")
	public DataSource mysqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("mysql.datasource.url"));
		dataSource.setUsername(env.getProperty("mysql.datasource.username"));
		dataSource.setPassword(env.getProperty("mysql.datasource.password"));
		//dataSource.setDriverClassName(env.getProperty("mysql.datasource.driver-class-name"));
		return dataSource;
	}
    
    
    @Bean(name = "mysqlEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(mysqlDataSource());
		em.setPackagesToScan( this.packages_models );
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("mysql.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.show-sql", env.getProperty("mysql.jpa.show-sql"));
		properties.put("hibernate.dialect", env.getProperty("mysql.jpa.database-platform"));
		
		em.setJpaPropertyMap(properties);
		return em;
	}
    
    
    @Bean(name = "mysqlTransactionManager")
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		
		return transactionManager;
	}
    
}
/*    */
