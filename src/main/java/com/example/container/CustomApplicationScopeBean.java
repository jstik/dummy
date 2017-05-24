package com.example.container;

/**
 * Created by Julia on 04.05.2017.
 */
public class CustomApplicationScopeBean implements ICustomApplicationScopeBean {
    protected CustomApplicationContext context;

    @Override
    public void setContext(CustomApplicationContext context) {
        this.context = context;
    }
}
