package tenant;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.DelegatingDataSource;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;
import org.springframework.jdbc.datasource.lookup.MapDataSourceLookup;
import org.springframework.stereotype.Component;
import sun.rmi.log.LogHandler;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

/**
 * Created by Julia on 09.05.2017.
 */

@Component
@Qualifier(value = "multiTenantDataSourceLookup")
public class MultiTenantDataSourceLookup extends MapDataSourceLookup {
    private static Logger logger = LoggerFactory.getLogger(MultiTenantDataSourceLookup.class.getSimpleName());

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    int maxPoolSize = 5;

    @Value("${liquibase.context}")
    private String liquibaseContext;

    @Autowired
    public MultiTenantDataSourceLookup(DataSource defaultDataSource, TenantRepository tenantRepository) {
        super();
        init(tenantRepository, defaultDataSource);
    }


    private void init(TenantRepository tenantRepository, DataSource defaultDataSource) {
        addDataSource(TenantContext.DEFAULT_TENANT_ID, defaultDataSource);
        tenantRepository.findAll().forEach(this::addTenantDataSource);
    }

    private void addTenantDataSource(@NotNull Tenant tenant) {
        try {
            TenantContext.set(tenant);
            logger.trace("Adding Tenant datasource {}, db url {}, user {} ", tenant.getName(), tenant.getUrl(), tenant.getDbUser());
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(tenant.getUrl());
            config.setUsername(tenant.getDbUser());
            config.setPassword(tenant.getPassword());
            config.setMaximumPoolSize(maxPoolSize);
            HikariDataSource dataSource = new HikariDataSource(config);
            addDataSource(tenant.getName(), dataSource);
            try {
                executeLiquibase(dataSource);
            } catch (LiquibaseException e) {
                throw new RuntimeException(e);
            }
        }finally {
            TenantContext.remove();
        }
    }

    private void executeLiquibase(DataSource dataSource) throws LiquibaseException {
        String log = "classpath:liquibase/tenantchangelog.xml";
        logger.trace("Liquibase changelog {} ", log);
        SpringLiquibase sl = new SpringLiquibase();
        sl.setDataSource(dataSource);
        sl.setContexts("production");
        sl.setResourceLoader(new DefaultResourceLoader());
        sl.setChangeLog(log);
        sl.setDropFirst(true);
        sl.setShouldRun(true);
        sl.afterPropertiesSet();
    }

}
