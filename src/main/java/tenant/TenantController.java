package tenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Julia on 08.05.2017.
 */
@RestController
@RequestMapping("tenant")
public class TenantController {
    @Autowired
    private TenantRepository tenantRepository;

    @RequestMapping("/list")
    public Object getTenants(){
        return tenantRepository.findAll();
    }
}
