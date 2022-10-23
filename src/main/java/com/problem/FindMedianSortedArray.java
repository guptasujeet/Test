package com.problem;

import com.google.common.collect.Lists;

import java.util.List;

public class FindMedianSortedArray {

    public double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {
        int aSize = a.size();
        int bSize = b.size();
        int totalSize = aSize + bSize;
        int mid = totalSize / 2;
        boolean isEven = totalSize % 2 == 0;
        int counter = 0;
        int i = 0, j = 0;
        int prevNumber = 0;
        int currentNumber = 0;
        while (i < aSize && j < bSize) {
            prevNumber = currentNumber;
            int num1 = a.get(i);
            int num2 = b.get(j);
            if (num1 < num2) {
                currentNumber = num1;
                i++;
            } else {
                currentNumber = num2;
                j++;
            }
            counter++;
            if (counter - 1 == mid) {
                return getMedian(isEven, prevNumber, currentNumber);
            }
        }


        while (i < aSize) {
            prevNumber = currentNumber;
            currentNumber = a.get(i);
            i++;
            counter++;
            if (counter - 1 == mid) {
                return getMedian(isEven, prevNumber, currentNumber);
            }
        }

        while (j < bSize) {
            prevNumber = currentNumber;
            currentNumber = b.get(j);
            j++;
            counter++;
            if (counter - 1 == mid) {
                return getMedian(isEven, prevNumber, currentNumber);
            }
        }

        return 0;
    }

    private double getMedian(boolean isEven, int prevNumber, int currentNumber) {
        if (isEven) {
            return (prevNumber + currentNumber) / 2.0;
        } else {
            return currentNumber;
        }
    }


    public static void main(String[] args) {
        FindMedianSortedArray median = new FindMedianSortedArray();
        System.out.println(median.findMedianSortedArrays(
                Lists.newArrayList(-50, -41, -40, -19, 5, 21, 28),
                Lists.newArrayList(-50, -21, -10)));
    }

}

