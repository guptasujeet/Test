package com.array;

import com.google.common.collect.Lists;

import java.util.ArrayList;

//interview bit
//https://www.youtube.com/watch?v=PNJoyRaIW7U&t=194s
public class MoveZerosOrMoveRight {

    public ArrayList<Integer> solve(ArrayList<Integer> A) {
        int size = A.size();
        int left = 0;
        int right = 0;
        while (right < size) {
            Integer element = A.get(right);
            if (element != 0) {
                swap(A, left, right);
                left++;
            }
            right++;
        }
        return A;
    }

    private void swap(ArrayList<Integer> list, int left, int right) {
        Integer temp = list.get(left);
        list.set(left, list.get(right));
        list.set(right, temp);
    }

    public void solve(char[] A) {
        int size = A.length;
        //start with left and right one
        int left = 0, right = 0;
        while (right < size) {
            char data = A[right];
            //for right pointer next data if all is zero continue to next
            if (data == ' ') {
                right++;
            } else {
                //otherwise swap , left with right and increment both by 1
                //think of it like moving all non zeros to left, instead of moving all zeros to right
                char temp = A[left];
                A[left] = data;
                A[right] = temp;
                left++;
                right++;
            }
        }
    }

    //for practice
    public void solveAgain(char[] data) {
        int i = 0, j = 0;
        while (j < data.length) {
            char ch = data[j];
            if (ch == ' ') {
                j++;
            } else {
                char temp = data[i];
                data[i] = data[j];
                data[j] = temp;
                i++;
                j++;
            }
        }
    }

    public static void main(String[] args) {
        MoveZerosOrMoveRight moveZerosOrMoveRight = new MoveZerosOrMoveRight();

        System.out.println(moveZerosOrMoveRight.solve(Lists.newArrayList(0, 1, 0, 3, 12)));//1, 3, 12, 0, 0
        System.out.println(moveZerosOrMoveRight.solve(Lists.newArrayList(1, 1, 0, 3, 12)));//1, 1, 3, 12, 0


        //remove spaces from the text
        String text = "This is a beautiful world !";

        char[] arr = text.toCharArray();
        moveZerosOrMoveRight.solve(arr);
        System.out.println(new String(arr));  //Thisisabeautifulworld!

        char[] arr2 = text.toCharArray();
        moveZerosOrMoveRight.solveAgain(arr2);
        System.out.println(new String(arr2));  //Thisisabeautifulworld!

    }


}
