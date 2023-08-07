package com.problem;


import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/majority-element-ii/

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * <p>
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: [3]
 * <p>
 * Example 2:
 * Input: nums = [1]
 * Output: [1]
 * <p>
 * Example 3:
 * Input: nums = [1,2]
 * Output: [1,2]
 */


//use Moor's Majority element algo
//https://www.youtube.com/watch?v=n5QY3x_GNDg
// https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm#
public class NBy3MajorityNumber {
    // DO NOT MODIFY THE LIST
    public int repeatedNumber(final List<Integer> a) {
        int n = 3;
        int checkSize = n - 1;
        Map<Integer, Integer> counter = new HashMap<>(checkSize);
        int size = a.size();
        for (int num : a) {
            if (counter.containsKey(num)) {
                counter.compute(num, (k, v) -> v + 1);
            } else {
                //N/3 (3-1) i.e 2 elements
                //if the size is 2 then
                //and possibly new element came which is more than checkSize
                if (counter.size() == checkSize) {
                    Iterator<Map.Entry<Integer, Integer>> iterator = counter.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<Integer, Integer> entry = iterator.next();
                        Integer key = entry.getKey();
                        Integer value = entry.getValue();
                        //remove element count which is 1 i.e. only 1 occurrence so far
                        if (value == 1) {
                            iterator.remove();
                        } else {
                            //else decrement it
                            counter.put(key, value - 1);
                        }
                    }
                } else {
                    //else put in the list
                    counter.put(num, 1);
                }
            }
        }
        //find candidate element
        if (!counter.isEmpty()) {
            for (int candidateNum : counter.keySet()) {
                int actualCountOfCandidateNum = 0;
                for (Integer num : a) {
                    if (num == candidateNum) {
                        actualCountOfCandidateNum++;
                    }
                }
                //verify if the candidate element meets criteria
                if (actualCountOfCandidateNum > size / 3) {
                    return candidateNum;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {

        NBy3MajorityNumber majorityNumber = new NBy3MajorityNumber();

        System.out.println(majorityNumber.repeatedNumber(Lists.newArrayList(441, 441, 994))); //441

    }
}
