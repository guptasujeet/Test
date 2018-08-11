package com.design.pattern.proxy.javaassist;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

/**
 * Created by Sujeet on 17/06/18.
 */
public class JavaAssistProxyDemo {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ProxyFactory factory = new ProxyFactory();
        factory.setSuperclass(Dog.class);
        factory.setFilter(method -> Modifier.isAbstract(method.getModifiers()));

        MethodHandler handler = (self, thisMethod, proceed, args1) -> {
            System.out.println("Handling " + thisMethod + " via the method handler");
            return null;
        };

        Dog dog = (Dog) factory.create(new Class<?>[0], new Object[0], handler);

        dog.bark(); // this is real invocation
        dog.fetch(); // this is proxy invocation
    }

}
