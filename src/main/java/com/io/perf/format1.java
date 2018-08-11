package com.io.perf;

public class format1 {
    public static void main(String args[]) {
        final int COUNT = 25000;
        for (int i = 1; i <= COUNT; i++) {
            String s = "The square of 5 is 25\n";
            System.out.print(s);
        }
    }
}

