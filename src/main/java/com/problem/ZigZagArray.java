package com.problem;


import java.util.Arrays;

public class ZigZagArray {

    public static void main(String[] args) {
        int[] data = {1, 3, 6, 9, -3}; //output // 9, -3, 6, 1, 3
        //int[] data = {-44, 77, 54, 19, -3, 99, 22}; //output // 99  -44  77  -3  54  19  22
        printZigZag(data);
    }


    private static void printZigZag(int[] data) {
        Arrays.sort(data);
        for (int start = 0, end = data.length - 1; start < end; start++, end--) {
            System.out.print(data[end] + "  " + data[start] + "  ");
        }
        if (data.length % 2 == 1) {
            int mid = data.length / 2;
            System.out.println(data[mid]);
        }
    }

}
