package com.problem;


import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NBy3MajorityNumber {
    // DO NOT MODIFY THE LIST
    public int repeatedNumber(final List<Integer> a) {
        Map<Integer, Integer> counter = new HashMap<>(2);
        int size = a.size();
        for (int i = 0; i < size; i++) {
            int num = a.get(i);
            if (counter.containsKey(num)) {
                counter.compute(num, (k, v) -> v + 1);
            } else {
                if (counter.size() == 2) {
                    Iterator<Map.Entry<Integer, Integer>> iterator = counter.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<Integer, Integer> entry = iterator.next();
                        Integer key = entry.getKey();
                        Integer value = entry.getValue();
                        if (value == 1) {
                            iterator.remove();
                        } else {
                            counter.put(key, value - 1);
                        }
                    }
                } else {
                    counter.put(num, 1);
                }
            }
        }
        if (!counter.isEmpty()) {
            for (int candidateNum : counter.keySet()) {
                int actualCountOfCandidateNum = 0;
                for (int i = 0; i < size; i++) {
                    if (a.get(i) == candidateNum) {
                        actualCountOfCandidateNum++;
                    }
                }
                if (actualCountOfCandidateNum > size / 3) {
                    return candidateNum;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {

        NBy3MajorityNumber majorityNumber = new NBy3MajorityNumber();

        System.out.println(majorityNumber.repeatedNumber(Lists.newArrayList(1000441, 1000441, 1000994)));

    }
}
