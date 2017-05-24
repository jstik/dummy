package com.example;

import com.example.dao.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tenant.TenantRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Map;

/**
 * Created by Julia on 04.05.2017.
 */
@Controller
public class WelcomeController {
    // inject via application.properties
    @Value("${welcome.message:test}")
    private String message = "Hello World";
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TenantRepository tenantRepository;

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("message", this.message);
        return "welcome";
    }
    @RequestMapping(value = "/customers", produces = "application/json")
    public @ResponseBody
    Object getAllCustomers(){
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"name" );
        Sort sort = new Sort(order);
        PageRequest pageRequest = new PageRequest(0,3, sort);
        return customerRepository.findAll(pageRequest);
    }

    @RequestMapping(value = "/tenant", produces = "application/json")
    public @ResponseBody
    Object getAllTenants(){
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"name" );
        Sort sort = new Sort(order);
        PageRequest pageRequest = new PageRequest(0,3, sort);
        return tenantRepository.findAll(pageRequest);
    }


}
