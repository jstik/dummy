package com.example.container;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * Created by Julia on 04.05.2017.
 */
public class CustomScope implements Scope {
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        CustomApplicationContext context = CustomApplicationContextThreadLocal.get();
        Object result = context.getBean(name);
        if (result == null) {
            result = objectFactory.getObject();
            ICustomApplicationScopeBean syncScopedBean = (ICustomApplicationScopeBean) result;
            syncScopedBean.setContext(context);
          /*  Object oldBean = context.setBean(name, result);
            if (oldBean != null) {
                result = oldBean;
            }*/
        }
        return result;
    }

    @Override
    public Object remove(String name) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return CustomApplicationContextThreadLocal.get().toString();
    }
}
