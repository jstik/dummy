package com.example.config;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import tenant.MultiTenantDataSourceLookup;
import tenant.TenantRepository;

import javax.annotation.Priority;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@ComponentScan("tenant")
@EnableJpaRepositories(basePackages = "tenant",
entityManagerFactoryRef ="defaultEntityManagerFactory",
transactionManagerRef = "defaultTransactionManager")
@EntityScan(basePackages = "tenant")
public class MasterJPAConfig {
    @Value("${liquibase.context}")
    private String liquibaseContext;

    @Autowired
    private ApplicationContext ctx;

    @Bean(name = "defaultDataSource")
    @Scope("singleton")
    public DataSource defaultDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:oracle:thin:@localhost:1521/pdborcl")
                .username("sa").password("sa")
                .driverClassName("oracle.jdbc.driver.OracleDriver")
                .build();
    }
    @Bean(name = "defaultEntityManagerFactory")
    @Scope("singleton")
    public EntityManagerFactory defaultEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(defaultDataSource());
        lef.setPersistenceXmlLocation("persistence.xml");
        lef.setPersistenceUnitName("default");
        lef.setPackagesToScan("tenant");
        lef.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        lef.afterPropertiesSet();
        return lef.getObject();
    }

    @Bean(name = "defaultEntityManager")
    @DependsOn("defaultEntityManagerFactory")
    public EntityManager defaultEntityManager(EntityManagerFactory defaultEntityManagerFactory){
      return defaultEntityManagerFactory.createEntityManager();
    }

    @Bean(name = "defaultTransactionManager")
    @Scope("singleton")
    public PlatformTransactionManager transactionManager(EntityManagerFactory defaultEntityManagerFactory) {
        return new JpaTransactionManager(defaultEntityManagerFactory);
    }


    @Bean(name = "masterDbCreator")
    @Autowired
    public SpringLiquibase liquibase(DataSource defaultDataSource) throws LiquibaseException {
        SpringLiquibase sl = new SpringLiquibase();
        sl.setDataSource(defaultDataSource);
        sl.setContexts(liquibaseContext);
        sl.setResourceLoader(new DefaultResourceLoader());
        sl.setDropFirst(true);
        sl.setChangeLog("classpath:liquibase/masterdbchangelog.xml");
        sl.setShouldRun(true);
        sl.afterPropertiesSet();
        return sl;
    }

    @Autowired
    @Bean(name = "dataSourceLookup")
    @DependsOn("masterDbCreator")
    public DataSourceLookup dataSourceLookup(TenantRepository tenantRepository, DataSource defaultDataSource){
       return new MultiTenantDataSourceLookup(defaultDataSource, tenantRepository);
    }


}
