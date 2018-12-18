package com.design.pattern.factory;

/**
 * Created by Sujeet on 16/12/18.
 */
public class Car implements Vehicle {

    private final int wheels;
    private final int maxSpeed;

    public Car(int wheels, int maxSpeed) {
        this.wheels = wheels;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public boolean start() {
        return true;
    }

    @Override
    public void move() {
        System.out.println("Moving car @ speed of 120");
    }

    @Override
    public void stop() {
        System.out.println("Stopped car");
    }

    @Override
    public void honk() {
        System.out.println("Pi pi pi pi pi pi pi pi.......");
    }

    @Override
    public int getWheels() {
        return wheels;
    }

    @Override
    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void moveReverse() {
        System.out.println("Applying reverse gear and moving back");
    }

    @Override
    public String toString() {
        return "Car{" +
                "wheels=" + wheels +
                ", maxSpeed=" + maxSpeed +
                '}';
    }
}
