package com.design.pattern.singleton;

/**
 * Created by Sujeet on 16/12/18.
 */
public class Shop {

    public static void main(String[] args) {
        System.out.println(CatalogDoubleCheckLocking.areYouReady());  //testing eager initialization
        handleCustomer();
    }

    private static void handleCustomer() {
        CatalogDoubleCheckLocking catalogDoubleCheckLocking = CatalogDoubleCheckLocking.getInstance();

        System.out.println("Customer 1 here is your catalog : " + catalogDoubleCheckLocking.getCatalogData());


        CatalogDoubleCheckLocking catalogDoubleCheckLocking2 = CatalogDoubleCheckLocking.getInstance();

        System.out.println("Customer 2 here is your catalog : " + catalogDoubleCheckLocking2.getCatalogData());

        CatalogDoubleCheckLocking catalogDoubleCheckLocking3 = CatalogDoubleCheckLocking.getInstance();

        System.out.println("Customer 3 here is your catalog : " + catalogDoubleCheckLocking3.getCatalogData());
    }

}
