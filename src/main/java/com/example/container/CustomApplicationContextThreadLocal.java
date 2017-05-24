package com.example.container;

/**
 * Created by Julia on 04.05.2017.
 */
public class CustomApplicationContextThreadLocal {
    private static ThreadLocal<CustomApplicationContext> customerContext = new ThreadLocal<>();

    public static void set(CustomApplicationContext ctx) {
        if(ctx == null)
            clear();
        customerContext.set(ctx);
    }

    public static CustomApplicationContext get() {
        return customerContext.get();
    }

    public static void clear() {
        customerContext.remove();
    }

}
