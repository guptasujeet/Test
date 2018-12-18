package com.design.pattern.factory;

/**
 * Created by Sujeet on 16/12/18.
 */
public class VehicleFactory {

    public static Vehicle createVehicle(String type) {
        switch (type) {
            case "car":
                return new Car(4, 180);
            case "bike":
                return new Bike(2, 80);
            default:
                throw new IllegalArgumentException("Argument not supported" + type);
        }
    }

}
