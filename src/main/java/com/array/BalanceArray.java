package com.array;

import com.google.common.collect.Lists;

import java.util.ArrayList;

//https://www.interviewbit.com/problems/balance-array/
public class BalanceArray {


    private int solveOptimized(ArrayList<Integer> A) {

        int size = A.size();

        //naming it right because we will be moving subtracting it so it will contain element sum till right
        //in the 2nd loop
        int oddRightSum = 0, evenRightSum = 0;
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                evenRightSum += A.get(i);
            } else {
                oddRightSum += A.get(i);
            }
        }


        int oddLeftSum = 0;
        int evenLeftSum = 0;

        int count = 0;
        for (int i = 0; i < size; i++) {
            int currentElement = A.get(i);
            if (i % 2 == 0) {
                evenRightSum -= currentElement; //subtract
                if (evenRightSum + oddLeftSum == oddRightSum + evenLeftSum) {
                    count++;
                }
                evenLeftSum += currentElement;  //add
            } else {
                oddRightSum -= currentElement;  //subtract
                if (oddRightSum + evenLeftSum == evenRightSum + oddLeftSum) {
                    count++;
                }
                oddLeftSum += currentElement;   //add
            }
        }
        return count;
    }


    private int solveN2(ArrayList<Integer> A) {
        int size = A.size();
        int count = 0;
        //in the first loop assuming that we are removing that element
        for (int i = 0; i < size; i++) {
            long evenSum = 0;
            long oddSum = 0;
            for (int j = 0; j < size; j++) {
                if (j < i) {
                    if (j % 2 == 0) {
                        evenSum += A.get(j);
                    } else {
                        oddSum += A.get(j);
                    }
                } else if (j > i) {
                    //if based on assumption element is remove then the next element in the array
                    //will be shifted by 1 , even index will become odd and odd will become even
                    //hence adding differently here
                    if (j % 2 == 0) {
                        oddSum += A.get(j);
                    } else {
                        evenSum += A.get(j);
                    }
                }
                //if equal ignore that element as we are not adding that to sum
            }
            if (evenSum == oddSum) {
                count++;
            }
        }
        return count;

    }


    public static void main(String[] args) {

        ArrayList<Integer> integers1 = Lists.newArrayList(2, 1, 6, 4);
        ArrayList<Integer> integers2 = Lists.newArrayList(5, 5, 2, 5, 8);
        ArrayList<Integer> integers3 = Lists.newArrayList(9, 9, 3, 5, 6);
        ArrayList<Integer> integers4 = Lists.newArrayList(8, 5);


        BalanceArray balanceArray = new BalanceArray();

        System.out.println(balanceArray.solveN2(integers1)); //1
        System.out.println(balanceArray.solveN2(integers2)); //2
        System.out.println(balanceArray.solveN2(integers3)); //0
        System.out.println(balanceArray.solveN2(integers4)); //0

        System.out.println(balanceArray.solveOptimized(integers1)); //1
        System.out.println(balanceArray.solveOptimized(integers2)); //2
        System.out.println(balanceArray.solveOptimized(integers3)); //0
        System.out.println(balanceArray.solveOptimized(integers4)); //0

    }

}
