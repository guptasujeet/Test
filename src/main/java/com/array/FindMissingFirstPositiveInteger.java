package com.array;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.BitSet;

// https://www.interviewbit.com/problems/first-missing-integer/
public class FindMissingFirstPositiveInteger {

    public int firstMissingPositive(ArrayList<Integer> arr) {
        int size = arr.size();
        if (arr.isEmpty()) {
            return 1;
        }

        BitSet bitSet = new BitSet();
        for (Integer element : arr) {
            if (element >= 0) {
                bitSet.set(element);
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= size; i++) {
            if (!bitSet.get(i)) {
                return i;
            } else {
                max = Math.max(max, i);
            }
        }

        return max + 1;
    }

    public static void main(String[] args) {
        FindMissingFirstPositiveInteger find = new FindMissingFirstPositiveInteger();
        int result = find.firstMissingPositive(Lists.newArrayList(1, 2, 0));
        System.out.println(result);
        assert result == 3;

        result = find.firstMissingPositive(Lists.newArrayList(3, 4, -1, 1));
        System.out.println(result);
        assert result == 2;

        result = find.firstMissingPositive(Lists.newArrayList(-8, -7, -6));
        System.out.println(result);
        assert result == 1;

        result = find.firstMissingPositive(Lists.newArrayList(1));
        System.out.println(result);
        assert result == 2;

        result = find.firstMissingPositive(Lists.newArrayList(1, 2, 3, 4, 5, 6));
        System.out.println(result);
        assert result == 7;
    }
}
