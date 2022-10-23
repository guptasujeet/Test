package com.ds.tree.binary;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//https://www.youtube.com/watch?v=QG0hE0R_ng4&t=2822s
//https://practice.geeksforgeeks.org/problems/k-sum-paths/1
public class SumKPath {

    public static int sumKPath(TreeNode node, int kPathSum) {
        List<String> queue = new LinkedList<>();

        AtomicInteger count = new AtomicInteger(0);

        solveSumKPatch(node, kPathSum, queue, count);

        return count.intValue();
    }

    private static void solveSumKPatch(TreeNode node, int kPathSum, List<String> queue, AtomicInteger count) {
        if (node == null) {
            return;
        }

        queue.add(node.data);

        solveSumKPatch(node.left, kPathSum, queue, count);
        solveSumKPatch(node.right, kPathSum, queue, count);

        countForPath(queue, count, kPathSum);
        queue.remove(node.data);
    }

    private static void countForPath(List<String> queue, AtomicInteger count, int kPathSum) {
        int sum = 0;
        for (int i = queue.size() - 1; i >= 0; i--) {
            String s = queue.get(i);
            sum += Integer.parseInt(s);
            if (sum == kPathSum) {
                count.incrementAndGet();
            }
        }
    }


    public static void main(String[] args) {

    }

}
