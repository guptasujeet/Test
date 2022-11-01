package com.array;

import com.google.common.collect.Lists;

import java.util.ArrayList;

//interview bit
//https://www.youtube.com/watch?v=PNJoyRaIW7U&t=194s
public class MoveZeros {


    public ArrayList<Integer> solve(ArrayList<Integer> A) {


        int size = A.size();

        //start with left and right one
        int left = 0, right = 0;
        while (right < size) {
            int data = A.get(right);
            //for right pointer next data if all is zero continue to next
            if (data == 0) {
                right++;
            } else {
                //other wise swap , left with right and increment both by 1
                //think of it like moving all non zeros to left, instead of moving all zeros to right
                int temp = A.get(left);
                A.set(left, data);
                A.set(right, temp);
                left++;
                right++;
            }
        }

        return A;
    }


    public static void main(String[] args) {
        MoveZeros moveZeros = new MoveZeros();

        System.out.println(moveZeros.solve(Lists.newArrayList(0, 1, 0, 3, 12)));//1, 3, 12, 0, 0
        System.out.println(moveZeros.solve(Lists.newArrayList(1, 1, 0, 3, 12)));//1, 1, 3, 12, 0
    }


}
