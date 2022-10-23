package com.ds.tree.bs;

import com.google.common.collect.Lists;

import java.util.ArrayList;

public class BookAllocationUsingBS {


    public static int allocateBooks(ArrayList<Integer> arr, int n, int m) {
        // Write your code here.
        int start = 0;
        int end = 0;
        for (Integer page : arr) {
            end += page;
        }

        int mid = start + (end - start) / 2;
        int res = -1;
        while (start <= end) {
            if (isProbableSolution(arr, n, m, mid)) {
                res = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            mid = start + (end - start) / 2;
        }

        return res;
    }

    private static boolean isProbableSolution(ArrayList<Integer> arr, int bookCount, int studentCount, int mid) {
        int pagePerStudent = 0;
        int studentNumber = 1;

        for (int i = 0; i < bookCount; i++) {
            if (pagePerStudent + arr.get(i) <= mid) {
                pagePerStudent += arr.get(i);
            } else {
                studentNumber++;
                pagePerStudent = arr.get(i);
                if (studentNumber > studentCount || arr.get(i) > mid) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(allocateBooks(Lists.newArrayList(10, 20, 30, 40), 4, 2));
    }

}
