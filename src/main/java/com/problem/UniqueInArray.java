package com.problem;

public class UniqueInArray {

    public static int findUnique(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }
        int res = arr[0];
        for (int i = 1; i < arr.length; i++) {
            res = res ^ arr[i];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(findUnique(new int[]{1, 2, 3, 4, 5, 4, 3, 2, 1}));
        System.out.println(findUnique(new int[]{1, 2, 3, 4, 5, 5, 4, 3, 2, 1}));
        System.out.println(findUnique(new int[]{1, 2, 3, 4, 5, 7, 5, 4, 3, 2, 1}));
    }


}
