package com.example.config;

import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import tenant.CurrentTenantIdentifierResolverImpl;
import tenant.MultiTenantConnectionProviderImpl;
import tenant.MultiTenantDataSourceLookup;
import tenant.TenantRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Julia on 10.05.2017.
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.example.dao", "com.example.catalog"},
        entityManagerFactoryRef ="tenantEntityManagerFactory",
        transactionManagerRef = "tenantTransactionManager")
//@EntityScan("com.example.model")
//@ComponentScan("tenant")
@Import(MasterJPAConfig.class)
public class TenantJPAConfig {
    @Autowired
    DataSource defaultDataSource;

    @Autowired
    DataSourceLookup  dataSourceLookup;

    @Bean
    public DataSource tenantDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:oracle:thin:@localhost:1521/pdborcl")
                .username("t1").password("sa")
                .driverClassName("oracle.jdbc.driver.OracleDriver")
                .build();
    }

    @Bean
    public MultiTenantConnectionProvider multiTenantConnectionProvider(DataSource defaultDataSource, DataSourceLookup dataSourceLookup){
        return new MultiTenantConnectionProviderImpl(defaultDataSource, dataSourceLookup);
    }

    @Bean
    public CurrentTenantIdentifierResolver currentTenantIdentifierResolver(){
        return new CurrentTenantIdentifierResolverImpl();
    }

    @Bean(name = "tenantEntityManagerFactory")
    @Qualifier("tenantEntityManagerFactory")
    public EntityManagerFactory tenantEntityManagerFactory() {

        Map<String, Object> properties = new HashMap<>();
        //properties.putAll(jpaProperties.getHibernateProperties(dataSource));
        properties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
        properties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider(defaultDataSource, dataSourceLookup));
        properties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver());
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setJpaPropertyMap(properties);
        lef.setDataSource(tenantDataSource());
        lef.setPersistenceXmlLocation("persistence.xml");
        lef.setPersistenceUnitName("tenants");
        lef.setPackagesToScan("com.example.model");
        lef.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        lef.afterPropertiesSet();

        return lef.getObject();
    }

    @Bean(name = "tenantEntityManager")
    @DependsOn("tenantEntityManagerFactory")
    public EntityManager tenantEntityManager(EntityManagerFactory  tenantEntityManagerFactory){
        return tenantEntityManagerFactory.createEntityManager();
    }

    @Bean(name = "tenantTransactionManager")
    @Qualifier("tenantTransactionManager")
    @DependsOn("tenantEntityManagerFactory")
    public PlatformTransactionManager transactionManager(EntityManagerFactory  tenantEntityManagerFactory) {
        return new JpaTransactionManager(tenantEntityManagerFactory);
    }
}
