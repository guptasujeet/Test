package com.ds.tree.bs;


import com.google.common.collect.Lists;

import java.util.ArrayList;

//https://www.interviewbit.com/problems/painters-partition-problem/
public class PainterPartition {
    public int paint(int numPainters, int unitTime, ArrayList<Integer> blocks) {

        long totalBlocks = blocks.stream().mapToLong(e -> e).sum();
        long totalWorkTime = totalBlocks * unitTime;

        long start = 0;
        long end = totalWorkTime;

        long ans = -1;
        long midWorkTime = start + (end - start) / 2;
        while (start <= end) {
            if (canPaint(numPainters, unitTime, blocks, midWorkTime)) {
                end = midWorkTime - 1;
                ans = midWorkTime;
            } else {
                start = midWorkTime + 1;
            }
            midWorkTime = start + (end - start) / 2;
        }
        return (int) (ans % 10000003);
    }

    private boolean canPaint(int numPainters, int unitTime, ArrayList<Integer> blocks, long midWorkTime) {

        long perPainterAllocation = 0;
        int currentPainerCount = 1;
        for (int blockNum = 0; blockNum < blocks.size(); blockNum++) {
            long currentWork = blocks.get(blockNum) * (long) unitTime;
            if (perPainterAllocation + currentWork <= midWorkTime) {
                perPainterAllocation += currentWork;
            } else {
                perPainterAllocation = currentWork;
                currentPainerCount++;
            }
            if (currentPainerCount > numPainters) {
                return false;
            }
            if (currentWork > midWorkTime) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PainterPartition painterPartition = new PainterPartition();
        System.out.println(painterPartition.paint(1, 1000000, Lists.newArrayList(1000000)));
    }

}

