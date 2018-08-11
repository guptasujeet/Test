package com.test.collection;


import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class EnumMapTest {

    public static void main(String[] args) {

        EnumMap<MyEnum, String> map = new EnumMap<MyEnum, String>(MyEnum.class);

        map.put(MyEnum.SUMMER, "Summer");
        map.put(MyEnum.WINTER, "Winter");

        System.out.println(Arrays.toString(MyEnum.values()));


        for (Map.Entry entry : map.entrySet()) {
            System.out.println("key : " + entry.getKey() + ", value : " + entry.getValue());
            map.remove(MyEnum.WINTER); //no concurrent modification exception
        }

        System.out.println(Arrays.toString(MyEnum.values()));

    }


    enum MyEnum {
        SUMMER, WINTER, FALL, RAIN
    }

}
