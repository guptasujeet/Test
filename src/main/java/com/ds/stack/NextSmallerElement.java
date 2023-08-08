package com.ds.stack;


import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.LinkedList;

//https://www.codingninjas.com/codestudio/problems/next-smaller-element_1112581
//https://www.youtube.com/watch?v=lJLcqDsmYfg
public class NextSmallerElement {


    static ArrayList<Integer> nextSmallerElement(ArrayList<Integer> arr, int n) {
        ArrayList<Integer> ansList = new ArrayList<>(n);
        LinkedList<Integer> stack = new LinkedList<>();
        //start with -1 for the last element, as there is no element lesser than that
        stack.push(-1);
        //start from reverse side for the next min element
        for (int i = arr.size() - 1; i >= 0; i--) {
            Integer current = arr.get(i);
            //if item in stack is bigger than current number remove that number then
            while (stack.peek() >= current) {
                stack.pop();
            }
            //take top element from stack and that is the answer
            int ans = stack.peek();
            //put the current element into stack
            stack.push(current);
            ansList.add(0, ans);
        }
        return ansList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> int1 = Lists.newArrayList(2, 1, 4, 3); // 1, -1, 3, -1
        System.out.println(nextSmallerElement(int1, int1.size()));

        ArrayList<Integer> int2 = Lists.newArrayList(1, 3, 2);      //-1, 2, -1
        System.out.println(nextSmallerElement(int2, int2.size()));


        ArrayList<Integer> int3 = Lists.newArrayList(3, 3, 1, 1); // 1 1 -1 -1
        System.out.println(nextSmallerElement(int3, int3.size()));


        ArrayList<Integer> int4 = Lists.newArrayList(2, 1, 5, 6, 2, 3); // 1, -1, 2, 2, -1, -1
        System.out.println(nextSmallerElement(int4, int4.size()));

        ArrayList<Integer> int5 = Lists.newArrayList(1, 5, 4, 3); // -1 4 3 -1
        System.out.println(nextSmallerElement(int5, int5.size()));
    }
}
