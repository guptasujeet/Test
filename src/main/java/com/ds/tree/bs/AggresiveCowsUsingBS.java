package com.ds.tree.bs;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;

public class AggresiveCowsUsingBS {


    public static int aggressiveCows(ArrayList<Integer> stalls, int cows) {
        Collections.sort(stalls);
        int max = stalls.get(stalls.size() - 1);
        int min = stalls.get(0);
        int start = 0;
        int end = max - min;

        int mid = start + (end - start) / 2;

        int ans = -1;
        while (start <= end) {
            if (isProbableSolution(stalls, cows, mid)) {
                ans = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
            mid = start + (end - start) / 2;
        }
        return ans;
    }

    private static boolean isProbableSolution(ArrayList<Integer> stalls, int cows, int mid) {
        int cowCount = 1;
        int lastPos = stalls.get(0);
        for (int i = 1; i < stalls.size(); i++) {
            if (stalls.get(i) - lastPos >= mid) {
                lastPos = stalls.get(i);
                cowCount++;
                if (cowCount == cows) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(aggressiveCows(Lists.newArrayList(4, 2, 1, 3, 6), 2));
        System.out.println(aggressiveCows(Lists.newArrayList(0, 4, 3, 7, 10, 9), 3));
    }
}
