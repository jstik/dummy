package tenant;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by Julia on 09.05.2017.
 */
@Component(value = "multiTenantConnectionProvider")
public class MultiTenantConnectionProviderImpl  extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
    private static final long serialVersionUID = 14535345L;
    private final DataSource defaultDataSource;
    private final DataSourceLookup dataSourceLookup;

    @Autowired
    public MultiTenantConnectionProviderImpl(DataSource defaultDataSource,
                                             DataSourceLookup dataSourceLookup) {
        this.defaultDataSource = defaultDataSource;
        this.dataSourceLookup = dataSourceLookup;
    }


    @Override
    protected DataSource selectAnyDataSource() {
        return defaultDataSource;
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        return dataSourceLookup.getDataSource(tenantIdentifier);
    }
}
