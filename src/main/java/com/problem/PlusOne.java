package com.problem;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Iterator;

//https://www.interviewbit.com/problems/add-one-to-number/
public class PlusOne {

    public ArrayList<Integer> plusOne(ArrayList<Integer> a) {
        int lastDigit = a.get(a.size() - 1);
        if (lastDigit < 9) {
            a.set(a.size() - 1, lastDigit + 1);
            return a;
        }

        //set last value to 0 as addition will cause it 0
        a.set(a.size() - 1, 0);


        boolean hasCarry = true;

        for (int i = a.size() - 2; i >= 0; i--) {
            int number = a.get(i);
            if (hasCarry) {
                if (number == 9) {
                    a.set(i, 0);
                    hasCarry = true;
                } else {
                    a.set(i, number + 1);
                    hasCarry = false;
                }
            } else {
                break;
            }
        }
        if (hasCarry) {
            a.add(0, 1);
        }
        //remove leading 0
        Iterator<Integer> iterator = a.iterator();
        while (iterator.hasNext()) {
            int digit = iterator.next();
            if (digit == 0) {
                iterator.remove();
            } else {
                break;
            }
        }
        return a;

    }


    public static void main(String[] args) {
        PlusOne plusOne = new PlusOne();
        System.out.println(plusOne.plusOne(Lists.newArrayList(0, 1, 2, 3)));
    }

}
