package com.test.temp;

import java.util.function.Consumer;

/**
 * Created by Sujeet on 17/03/18.
 */
public class HelloWorldClass {

    HelloWorldClass() {
        System.out.println("constructor");
    }

    public static void main(String[] args) {
        printTest();

        HelloWorldClass worldClass = new HelloWorldClass();
        worldClass.printTestInstance();

        worldClass.getValue();
        System.out.println(worldClass.getPrivateValue());

        worldClass.consume(System.out::println);
    }


    public static void printTest() {
        System.out.println("Test");
    }

    public void printTestInstance() {
        System.out.println("Test");
        System.out.println("Test Instance");
    }

    public int getValue() {
        return 10;
    }

    private int getPrivateValue() {
        return 20;
    }

    public void consume(Consumer<String> consumer) {
        consumer.accept("My Consumer");
    }

}
