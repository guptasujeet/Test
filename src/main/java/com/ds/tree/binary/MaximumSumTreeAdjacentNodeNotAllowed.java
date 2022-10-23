package com.ds.tree.binary;

import com.util.Pair;

public class MaximumSumTreeAdjacentNodeNotAllowed {


    public static int getMaximumSumNonAdjacentNode(TreeNode root) {
        Pair<Integer, Integer> pair = solveForAdjacentNode(root);
        return Math.max(pair.getFirst(), pair.getSecond());
    }


    public static Pair<Integer, Integer> solveForAdjacentNode(TreeNode node) {

        if (node == null) {
            return Pair.of(0, 0);
        }

        Pair<Integer, Integer> leftPair = solveForAdjacentNode(node.left);
        Pair<Integer, Integer> rightPair = solveForAdjacentNode(node.right);

        int sumIncludingLevel = Integer.parseInt(node.data) + leftPair.getSecond() + rightPair.getSecond();
        int sumExcludingLevel = leftPair.getFirst() + rightPair.getFirst();

        return Pair.of(sumIncludingLevel, sumExcludingLevel);
    }


}
