package com.design.pattern.decorator;


public class PlainPizza implements Pizza {

    public PlainPizza() {
        System.out.println("Preparing Dough");
    }

    @Override
    public String getDescription() {
        return "Thin Dough";
    }


    @Override
    public double getPrice() {
        return 10;
    }
}
