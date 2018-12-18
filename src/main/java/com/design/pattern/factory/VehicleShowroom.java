package com.design.pattern.factory;

/**
 * Created by Sujeet on 16/12/18.
 */
public class VehicleShowroom {

    private static SalesDept salesDept = new SalesDept();

    public static void main(String[] args) {
        bikeCustomerEntry();
        System.out.println();
        carCustomerEntry();
    }


    public static void bikeCustomerEntry() {
        //purchased bike
        Bike bike = (Bike) salesDept.purchaseVehicle("bike");
        System.out.println(bike);
        bike.start();
        bike.move();
        bike.honk();
        bike.stop();
    }

    public static void carCustomerEntry() {
        Car car = (Car) salesDept.purchaseVehicle("car");
        System.out.println(car);
        car.start();
        car.move();
        car.honk();
        //parking at home
        car.moveReverse();
        car.stop();
    }

    public static void createVehicleLegacyWay() {
        Bike bike = new Bike(2, 80);
        System.out.println(bike);
        bike.start();
        bike.move();
        bike.honk();
        bike.stop();

        Car car = new Car(4, 180);
        System.out.println(car);
        car.start();
        car.move();
        car.honk();
        car.stop();

    }


}
