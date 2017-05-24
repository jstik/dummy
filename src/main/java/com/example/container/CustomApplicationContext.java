package com.example.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Created by Julia on 04.05.2017.
 */
public class CustomApplicationContext  {
    @Autowired
    ApplicationContext context;
    String user;

    public Object getBean(String name){
        return  context.getBean(name);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
