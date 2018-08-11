package com.design.pattern.proxy.jdk;

import java.lang.reflect.Proxy;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Sujeet on 16/06/18.
 */
public class ConfigFactory {

    private static final ConcurrentMap<Class, Object> dataMap = new ConcurrentHashMap<>();

    public static <T> T getConfig(Class clazz) {
        return (T) dataMap.computeIfAbsent(clazz, ConfigFactory::buildConfig);
    }

    private static <T> T buildConfig(Class clazz) {
        ClassLoader classLoader = ConfigFactory.class.getClassLoader();
        return (T) Proxy.newProxyInstance(classLoader, new Class[]{clazz}, new ConfigInvocationHandler());

    }

}
