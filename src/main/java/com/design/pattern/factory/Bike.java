package com.design.pattern.factory;

/**
 * Created by Sujeet on 16/12/18.
 */
public class Bike implements Vehicle {

    private final int wheels;
    private final int maxSpeed;

    public Bike(int wheels, int maxSpeed) {
        this.wheels = wheels;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public boolean start() {
        return true;
    }

    @Override
    public void move() {
        System.out.println("Moving bike @ speed of 60");
    }

    @Override
    public void stop() {
        System.out.println("Bike stopped");
    }

    @Override
    public void honk() {
        System.out.println("Ti ti ti ti ti ti ti ti ti......");
    }

    @Override
    public int getWheels() {
        return wheels;
    }

    @Override
    public int getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "wheels=" + wheels +
                ", maxSpeed=" + maxSpeed +
                '}';
    }
}
