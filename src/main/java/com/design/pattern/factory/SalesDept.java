package com.design.pattern.factory;

/**
 * Created by Sujeet on 16/12/18.
 */
public class SalesDept {

    public Vehicle purchaseVehicle(String type) {
        //do order processing
        Vehicle vehicle = VehicleFactory.createVehicle(type);
        doVehicleRegistration(vehicle);
        return vehicle;
    }


    public void doVehicleRegistration(Vehicle vehicle) {
        System.out.println("Vehicle registered ");
    }

}
