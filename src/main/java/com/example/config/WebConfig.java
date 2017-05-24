package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.*;
import tenant.TenantInterceptor;
import tenant.TenantRepository;

/**
 * Created by Julia on 10.05.2017.
 */
@Configuration
@EnableWebMvc
@ImportResource("spring.xml")
@ComponentScan("tenant")
public class WebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    TenantRepository tenantRepository;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TenantInterceptor(tenantRepository)).addPathPatterns("/**");
    }

    @Override // equivalents for <mvc:resources/> tags
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/").setCachePeriod(31556926);
    }
    // equivalent for <mvc:default-servlet-handler/> tag
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
