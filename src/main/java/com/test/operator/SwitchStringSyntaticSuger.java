package com.test.operator;

/**
 * Created by Sujeet on 04/02/18.
 */
public class SwitchStringSyntaticSuger {

    public static void main(String[] args) {
        String val = "Test1";
        //open class file and see how switch is implemented in class file
        switch (val) {
            case "Test1":
                System.out.println("Test 1 called");
                break;
            case "Test2":
                System.out.println("Test 2 called");
                break;
            default:
                System.out.println("Other called");
        }

        System.out.println("Done");
    }

}
