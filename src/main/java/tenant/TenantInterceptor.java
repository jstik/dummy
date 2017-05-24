package tenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Julia on 08.05.2017.
 */
@Component
public class TenantInterceptor extends HandlerInterceptorAdapter {

    private TenantRepository tenantRepository;
    @Value("${jwt.header}")
    private String tokenHeader;

    public TenantInterceptor(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //String authToken = request.getHeader(this.tokenHeader);
        //Jwt jwt = JwtHelper.decode(authToken);
        Tenant tenant1 = tenantRepository.findTenantByName("tenant1");
        //String tenantId = jwtTokenUtil.getTenantIdFromToken(authToken);
        TenantContext.set(tenant1 );
        return true;
    }
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView)
            throws Exception {
        TenantContext.remove();
    }
}
