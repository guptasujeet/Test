package com.design.pattern.singleton;

/**
 * Created by Sujeet on 16/12/18.
 */
public class Shop {

    public static void main(String[] args) {
        System.out.println(Catalog.areYouReady());  //testing eager initialization
        handleCustomer();
    }

    private static void handleCustomer() {
        Catalog catalog = Catalog.getInstance();

        System.out.println("Cust 1 here is your catalog : " + catalog.getCatalogData());


        Catalog catalog2 = Catalog.getInstance();

        System.out.println("Cust 2 here is your catalog : " + catalog2.getCatalogData());

        Catalog catalog3 = Catalog.getInstance();

        System.out.println("Cust 3 here is your catalog : " + catalog3.getCatalogData());
    }

}
