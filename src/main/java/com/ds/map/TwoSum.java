package com.ds.map;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {

    public ArrayList<Integer> twoSum(final List<Integer> list, int sum) {

        Map<Integer, Integer> numAndIndex = new HashMap<>();

        int index1 = -1;
        int index2 = -1;
        for (int i = 0; i < list.size(); i++) {
            int num = list.get(i);

            if (numAndIndex.containsKey(sum - num)) {
                index2 = i + 1;
                index1 = numAndIndex.get(sum - num) + 1;
                break;
            } else {
                if (!numAndIndex.containsKey(num)) {
                    numAndIndex.put(num, i);
                }
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        if (index1 == -1 || index2 == -1) {
            return ans;
        }

        ans.add(index1);
        ans.add(index2);
        return ans;
    }


    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        System.out.println(twoSum.twoSum(Lists.newArrayList(1, 1, 1), 2));
    }
}
