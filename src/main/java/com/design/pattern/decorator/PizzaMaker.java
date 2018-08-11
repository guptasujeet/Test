package com.design.pattern.decorator;


public class PizzaMaker {

    public static void main(String[] args) {

        Pizza plainPizza = new PlainPizza();
        printPizzaInfo(plainPizza);

        Pizza veggiePizza = new VeggieDelight(new PlainPizza());
        printPizzaInfo(veggiePizza);

        Pizza veggieTomatoMozzarella = new VeggieDelight(new TomatoSauce(new Mozzarella(plainPizza)));
        printPizzaInfo(veggieTomatoMozzarella);


    }

    public static void printPizzaInfo(Pizza pizza) {
        System.out.println("Type : " + pizza.getClass().getSimpleName());
        System.out.println("Description : " + pizza.getDescription());
        System.out.println("Price : " + pizza.getPrice());
        System.out.println();
    }

}
