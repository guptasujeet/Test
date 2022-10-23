package com.ds.tree.binary;

import com.util.Pair;

public class LongestPathSum {


    public static Pair<Integer, Integer> getLongestPathSum(TreeNode node) {
        if (node == null) {
            return Pair.of(0, 0);
        }

        Pair<Integer, Integer> leftPair = getLongestPathSum(node.left);
        int leftSum = leftPair.getFirst();
        int leftHeight = leftPair.getSecond();

        Pair<Integer, Integer> rightPair = getLongestPathSum(node.right);
        int rightSum = rightPair.getFirst();
        int rightHeight = rightPair.getSecond();

        int retSum, retHeight;
        if (leftHeight > rightHeight) {
            retSum = leftSum;
            retHeight = leftHeight;
        } else {
            retSum = rightSum;
            retHeight = rightHeight;
        }

        return Pair.of(retSum + Integer.valueOf(node.data), retHeight + 1);
    }


    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(TreeSampleData.createSampleData3());

        System.out.println(tree.getLongestPathSum());


        System.out.println(tree.toString());
    }

}
