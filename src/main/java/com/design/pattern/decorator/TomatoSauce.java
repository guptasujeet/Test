package com.design.pattern.decorator;


public class TomatoSauce extends ToppingDecorator {

    public TomatoSauce(Pizza pizza) {
        super(pizza);
        System.out.println("Adding Tomato Sauce");
    }

    @Override
    public String getDescription() {
        return "Tomato, " + super.getDescription();
    }

    @Override
    public double getPrice() {
        return 3 + super.getPrice();
    }
}
