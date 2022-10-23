package com.ds.tree.bst;

import com.ds.tree.binary.TreeNode;

public class LargestBSTInBinaryTree {

    private int maxBstSize = -1;


    public int largestBSTLength(TreeNode root) {
        maxBstSize = -1;
        NodeInfo info = findLargestBST(root);
        return maxBstSize;
    }


    public NodeInfo findLargestBST(TreeNode root) {

        if (root == null) {
            return new NodeInfo(Integer.MAX_VALUE, Integer.MIN_VALUE, true, 0);
        }

        int data = Integer.valueOf(root.data);

        NodeInfo left = findLargestBST(root.left);


        NodeInfo right = findLargestBST(root.right);

        int size = left.size + right.size + 1;

        boolean isBst = false;
        if (left.isBst && right.isBst && (data > left.max && data < right.min)) {
            isBst = true;
        }
        if (isBst) {
            maxBstSize = Math.max(maxBstSize, size);
        }

        int min = Math.min(data, left.min);
        int max = Math.max(data, right.max);


        return new NodeInfo(min, max, isBst, size);
    }


    static class NodeInfo {
        int min;
        int max;
        boolean isBst;
        int size;

        public NodeInfo(int min, int max, boolean isBst, int size) {
            this.min = min;
            this.max = max;
            this.isBst = isBst;
            this.size = size;
        }
    }

}
