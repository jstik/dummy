package com.example;

import com.example.container.CustomApplicationScopeBean;
import com.example.container.CustomScope;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by Julia on 04.05.2017.
 */
@Service
@Scope("custom")
public class CustomScopeService extends CustomApplicationScopeBean {

    public void doSomethingInteresting() {
        context.getUser();
    }

}
