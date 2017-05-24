package tenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

/**
 * Created by Julia on 11.05.2017.
 */
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {
    @Override
    public String resolveCurrentTenantIdentifier() {
        return TenantContext.get() != null  ? TenantContext.get().getName() : TenantContext.DEFAULT_TENANT_ID;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
