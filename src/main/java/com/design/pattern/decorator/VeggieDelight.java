package com.design.pattern.decorator;


public class VeggieDelight extends ToppingDecorator {

    public VeggieDelight(Pizza pizza) {
        super(pizza);
        System.out.println("Adding Veggies");
    }

    @Override
    public String getDescription() {
        return "Veggies, " + super.getDescription();
    }

    @Override
    public double getPrice() {
        return 5 + super.getPrice();
    }
}
