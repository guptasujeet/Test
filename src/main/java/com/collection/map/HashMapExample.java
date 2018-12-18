
package com.collection.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sujeet on 02/10/18.
 */
public class HashMapExample {

    public static void main(String[] args) {

        testInvalidKey();
        //testValidKey();
    }

    private static void testValidKey() {
        Map<HValidKey, String> map = new HashMap<>();
        map.put(new HValidKey(1, 2), "Key1");
        map.put(new HValidKey(2, 3), "Key2");
        HValidKey key3 = new HValidKey(3, 4);
        map.put(key3, "Key3");

        System.out.println(map);

        String value1 = map.get(new HValidKey(1, 2));
        System.out.println("Value Key1 : " + value1);

        String value3 = map.get(key3);
        System.out.println("Value Key3 : " + value3);
    }

    private static void testInvalidKey() {
        Map<HInvalidKey, String> map = new HashMap<>();
        map.put(new HInvalidKey(1, 2), "Key1");
        map.put(new HInvalidKey(2, 3), "Key2");
        HInvalidKey key3 = new HInvalidKey(3, 4);
        map.put(key3, "Key3");

        System.out.println(map);

        String value1 = map.get(new HInvalidKey(1, 2));
        System.out.println("Value Key1 : " + value1);

        String value3 = map.get(key3);
        System.out.println("Value Key3 : " + value3);
    }

}

class HInvalidKey {
    int key1;
    int key2;

    public HInvalidKey(int key1, int key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    @Override
    public String toString() {
        return "HInvalidKey{" +
                "key1=" + key1 +
                ", key2=" + key2 +
                '}';
    }
}


class HValidKey {
    int key1;
    int key2;

    public HValidKey(int key1, int key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HValidKey hValidKey = (HValidKey) o;

        if (key1 != hValidKey.key1) return false;
        return key2 == hValidKey.key2;
    }

    @Override
    public int hashCode() {
        int result = key1;
        result = 31 * result + key2;
        return result;
    }

    @Override
    public String toString() {
        return "HValidKey{" +
                "key1=" + key1 +
                ", key2=" + key2 +
                '}';
    }
}
