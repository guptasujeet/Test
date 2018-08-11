package com.design.pattern.decorator;


public class Mozzarella extends ToppingDecorator {

    public Mozzarella(Pizza tempPizza) {
        super(tempPizza);
        System.out.println("Adding Mozzarella");
    }

    @Override
    public String getDescription() {
        return "Mozzarella,  " + super.getDescription();
    }

    @Override
    public double getPrice() {
        return 3 + super.getPrice();
    }
}
