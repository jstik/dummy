package tenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

/**
 * Created by Julia on 09.05.2017.
 */
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {
    @Override
    public String resolveCurrentTenantIdentifier() {
        Tenant tenant = TenantContext.get();
        return tenant== null ? TenantContext.DEFAULT_TENANT_ID : tenant.getName() ;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
