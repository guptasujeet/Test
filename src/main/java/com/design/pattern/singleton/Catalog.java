package com.design.pattern.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sujeet on 16/12/18.
 */
public class Catalog {

    //private static Catalog catalog = new Catalog(); //eager initialization
    private static Catalog catalog = null; // lazy initialization


    private Map<String, String> catalogData;

    private Catalog() {
        System.out.println("Initializing Catalog");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        init();

    }

    public static Catalog getInstance() {
        if (catalog == null) { //single threaded //need synchronization for multi-threaded
            synchronized (Catalog.class) {
                if (catalog == null) { // double check locking
                    catalog = new Catalog();
                }
            }
        }
        return catalog;
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
