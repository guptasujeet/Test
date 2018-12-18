package com.collection.map;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Sujeet on 02/10/18.
 */
public class TreeMapExample {

    public static void main(String[] args) {

        testInvalidKey();
        //testValidKey();
    }

    private static void testValidKey() {
        Map<TValidKey, String> map = new TreeMap<>();
        map.put(new TValidKey(1, 2), "Key1");
        map.put(new TValidKey(2, 3), "Key2");
        TValidKey key3 = new TValidKey(3, 4);
        map.put(key3, "Key3");

        System.out.println(map);

        String value1 = map.get(new TValidKey(1, 2));
        System.out.println("Value Key1 : " + value1);

        String value3 = map.get(key3);
        System.out.println("Value Key3 : " + value3);
    }

    private static void testInvalidKey() {
        Map<TInvalidKey, String> map = new TreeMap<>();
        /*Map<TInvalidKey, String> map = new TreeMap<>((o1, o2) -> {
            int cmp1 = Integer.compare(o1.key1, o2.key1);
            int cmp2 = Integer.compare(o1.key2, o2.key2);
            return Integer.compare(cmp1, cmp2);
        });*/
        map.put(new TInvalidKey(1, 2), "Key1");
        map.put(new TInvalidKey(2, 3), "Key2");
        TInvalidKey key3 = new TInvalidKey(3, 4);
        map.put(key3, "Key3");

        System.out.println(map);

        String value1 = map.get(new TInvalidKey(1, 2));
        System.out.println("Value Key1 : " + value1);

        String value3 = map.get(key3);
        System.out.println("Value Key3 : " + value3);
    }

}

class TInvalidKey /*implements Comparable*/ {
    int key1;
    int key2;

    public TInvalidKey(int key1, int key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    @Override
    public String toString() {
        return "TInvalidKey{" +
                "key1=" + key1 +
                ", key2=" + key2 +
                '}';
    }
}

//For Tree Map only comparable is sufficient
class TValidKey implements Comparable {
    int key1;
    int key2;

    public TValidKey(int key1, int key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    @Override
    public int compareTo(Object o) {
        TValidKey key = (TValidKey) o;
        int cmp1 = Integer.compare(key1, key.key1);
        int cmp2 = Integer.compare(key2, key.key2);

        return Integer.compare(cmp1, cmp2);
    }


    @Override
    public String toString() {
        return "TValidKey{" +
                "key1=" + key1 +
                ", key2=" + key2 +
                '}';
    }
}
