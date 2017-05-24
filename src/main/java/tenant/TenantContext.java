package tenant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Created by Julia on 05.05.2017.
 */
public class  TenantContext {
    private static Logger logger = LoggerFactory.getLogger(TenantContext.class.getName());
    private static ThreadLocal<Tenant> ctx = new ThreadLocal<>();

    public static String DEFAULT_TENANT_ID = "master";
    public static String TENANT ="TENANT";

    public static Tenant get() {
        return ctx.get();
    }

    public static void set(Tenant tenant){
        if(tenant == null)
            remove();
        else {
            ctx.set(tenant);
            MDC.put(TENANT, tenant.getName());
        }
    }

    public static void remove(){
        ctx.remove();
        MDC.remove(TENANT);
    }
}
