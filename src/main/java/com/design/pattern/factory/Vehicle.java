package com.design.pattern.factory;

/**
 * Created by Sujeet on 16/12/18.
 */
public interface Vehicle {
    boolean start();

    void move();

    void stop();

    void honk();

    int getWheels();

    int getMaxSpeed();
}
