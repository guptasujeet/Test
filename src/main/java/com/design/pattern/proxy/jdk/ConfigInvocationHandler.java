package com.design.pattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Sujeet on 16/06/18.
 */
public class ConfigInvocationHandler implements InvocationHandler {

    private ConcurrentMap<Method, Object> dataMap = new ConcurrentHashMap<>();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object returnValue;
        if (dataMap.containsKey(method)) {
            returnValue = dataMap.get(method);
        } else {
            if (method.getName().equals("toString")) {
                returnValue = proxy.getClass().getSuperclass().getName() + "->" + proxy.getClass().getName() + "@" + System.identityHashCode(proxy);
            } else {
                returnValue = "500";
            }
            dataMap.put(method, returnValue);
        }
        return returnValue;
    }


}
