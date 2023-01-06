package com.design.pattern.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sujeet on 16/12/18.
 */
public class CatalogDoubleCheckLocking {

    //private static Catalog catalog = new Catalog(); //eager initialization
    private static CatalogDoubleCheckLocking catalogDoubleCheckLocking = null; // lazy initialization

    private Map<String, String> catalogData;

    private CatalogDoubleCheckLocking() {
        System.out.println("Initializing Catalog");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        init();

    }

    public static CatalogDoubleCheckLocking getInstance() {
        if (catalogDoubleCheckLocking == null) { //single threaded //need synchronization for multi-threaded
            synchronized (CatalogDoubleCheckLocking.class) {
                if (catalogDoubleCheckLocking == null) { // double check locking
                    catalogDoubleCheckLocking = new CatalogDoubleCheckLocking();
                }
            }
        }
        return catalogDoubleCheckLocking;
    }


    private void init() {
        catalogData = new HashMap<>();
        catalogData.put("Item1", "Desc1");
    }

    public Map<String, String> getCatalogData() {
        return catalogData;
    }

    public static boolean areYouReady() {
        return true;
    }
}
