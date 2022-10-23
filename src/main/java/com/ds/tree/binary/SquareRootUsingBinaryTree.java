package com.ds.tree.binary;

public class SquareRootUsingBinaryTree {


    public static int getSquareRootIntegerPart(int number) {
        int start = 0;
        int end = number;
        int mid = start + (end - start) / 2;
        int ans = -1;
        while (start <= end) {
            if (mid * mid > number) {
                end = mid - 1;
            } else {
                ans = mid;
                start = mid + 1;
            }
            mid = start + (end - start) / 2;
        }
        return ans;
    }

    public static void main(String[] args) {
        int number = 99;
        System.out.println("Sqrt integer part of " + number + " is -> " + getSquareRootIntegerPart(number));
        number = 100;
        System.out.println("Sqrt integer part of " + number + " is -> " + getSquareRootIntegerPart(number));
        number = 110;
        System.out.println("Sqrt integer part of " + number + " is -> " + getSquareRootIntegerPart(number));
    }
}
