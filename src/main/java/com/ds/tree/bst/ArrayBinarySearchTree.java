package com.ds.tree.bst;

public class ArrayBinarySearchTree {


    public static int searchBST(int key, int[] elements) {

        if (elements.length == 0 && elements[0] == key) {
            return 0;
        }

        int start = 0;
        int end = elements.length - 1;
        int mid = start + (end - start) / 2;
        while (start <= end) {
            if (elements[mid] == key) {
                return mid;
            } else if (key < elements[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            mid = start + (end - start) / 2;
        }

        return -1;

    }

    public static int searchBST(int key, int[] elements, int start, int end) {

        if (elements.length == 0 && elements[0] == key) {
            return 0;
        }

        int mid = start + (end - start) / 2;
        while (start <= end) {
            if (elements[mid] == key) {
                return mid;
            } else if (key < elements[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            mid = start + (end - start) / 2;
        }

        return -1;

    }

    public static int searchBSTLeftMost(int key, int[] elements) {

        if (elements.length == 0 && elements[0] == key) {
            return 0;
        }

        int start = 0;
        int end = elements.length - 1;
        int mid = start + (end - start) / 2;
        int res = -1;
        while (start <= end) {
            if (elements[mid] == key) {
                res = mid;
                end = mid - 1;
            } else if (key < elements[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            mid = start + (end - start) / 2;
        }

        return res;

    }

    //sorted rotated array
    public static int getPivotElementIndex(int[] elements) {
        int start = 0;
        int end = elements.length - 1;
        int mid = start + (end - start) / 2;
        while (start < end) {
            if (elements[mid] > elements[0]) {
                start = mid + 1;
            } else {
                end = mid;
            }
            mid = start + (end - start) / 2;
        }
        return start;
    }

    public static int searchInRotateSortedArray(int key, int[] elements) {
        int pivot = getPivotElementIndex(elements);
        if (pivot == elements.length - 1) {
            return searchBST(key, elements, 0, elements.length - 1);
        }
        if (key > elements[pivot] && key > elements[elements.length - 1]) {
            return searchBST(key, elements, 0, pivot - 1);
        }
        return searchBST(key, elements, pivot, elements.length - 1);
    }


    public static void main(String[] args) {
        System.out.println(searchBSTLeftMost(2, new int[]{1, 2, 2, 2, 2, 2, 4, 6, 8, 10, 12}));
        System.out.println(searchBSTLeftMost(10, new int[]{1, 2, 2, 2, 2, 2, 4, 6, 8, 10, 10, 10, 12}));
        System.out.println(searchBST(4, new int[]{1, 2, 4, 6, 8, 10, 12}));
        System.out.println(searchBST(12, new int[]{1, 2, 4, 6, 8, 10, 12}));
        System.out.println(searchBST(1, new int[]{1, 2, 4, 6, 8, 10, 12}));
        System.out.println(searchBST(20, new int[]{1, 2, 4, 6, 8, 10, 12}));
        //System.out.println(getPiovtElementIndex(new int[]{8, 10, 12, 14, 15, 2, 4, 5, 6}));
        System.out.println(searchInRotateSortedArray(1, new int[]{1, 7, 67, 133, 178}));
        System.out.println(searchInRotateSortedArray(12, new int[]{8, 10, 12, 14, 15, 2, 4, 5, 6}));
        System.out.println(searchInRotateSortedArray(15, new int[]{8, 10, 12, 14, 15, 2, 4, 5, 6}));
        System.out.println(searchInRotateSortedArray(5, new int[]{8, 10, 12, 14, 15, 2, 4, 5, 6}));
        System.out.println(searchInRotateSortedArray(6, new int[]{8, 10, 12, 14, 15, 2, 4, 5, 6}));
        System.out.println(searchInRotateSortedArray(20, new int[]{8, 10, 12, 14, 15, 2, 4, 5, 6}));

    }

}
