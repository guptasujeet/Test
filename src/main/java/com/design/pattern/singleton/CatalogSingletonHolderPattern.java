package com.design.pattern.singleton;

public class CatalogSingletonHolderPattern {

    private CatalogSingletonHolderPattern() {
        System.out.println("Initializing CatalogSingletonHolderPattern");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // lazy initialization
    private static final class CatalogHolder {
        private static final CatalogSingletonHolderPattern catalog = new CatalogSingletonHolderPattern();
    }

    public static CatalogSingletonHolderPattern getInstance() {
        return CatalogHolder.catalog;
    }


}
