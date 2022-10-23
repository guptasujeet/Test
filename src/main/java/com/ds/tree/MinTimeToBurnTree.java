package com.ds.tree;

import com.ds.tree.binary.TreeNode;

public class MinTimeToBurnTree {


    public static int timeToBurn(TreeNode root, String firedNode) {
        ResultValue result = solveForBurningTree(root, firedNode);

        if (result.rightHeight == 1) {
            return result.leftHeight - 1;
        }

        if (result.leftHeight == 1) {
            return result.rightHeight - 1;
        }

        if (result.leftBurnNodeDistance > 0) {
            return Math.max(
                    Math.max(result.leftHeight - result.leftBurnNodeDistance, result.leftBurnNodeDistance),
                    (result.rightHeight + result.leftBurnNodeDistance)
            ) - 1;
        } else {
            return Math.max(
                    Math.max(result.rightHeight - result.rightBurnNodeDistance, result.rightBurnNodeDistance),
                    (result.leftHeight + result.rightBurnNodeDistance)
            ) - 1;
        }
    }


    //pair of height and distance of fireNode
    public static ResultValue solveForBurningTree(TreeNode node, String fireNode) {
        if (node == null) {
            return new ResultValue(0, 0, 0, 0);
        }


        ResultValue left = solveForBurningTree(node.left, fireNode);
        ResultValue right = solveForBurningTree(node.right, fireNode);


        int leftHeight = Math.max(left.leftHeight, left.rightHeight) + 1;
        int rightHeight = Math.max(right.leftHeight, right.rightHeight) + 1;
        int leftBurnNodeDistance = Math.max(left.leftBurnNodeDistance, left.rightBurnNodeDistance);
        int rightBurnNodeDistance = Math.max(right.leftBurnNodeDistance, right.rightBurnNodeDistance);
        if (node.data.equals(fireNode)) {
            leftBurnNodeDistance = 1;
            rightBurnNodeDistance = 1;
        } else if (leftBurnNodeDistance > 0) {
            leftBurnNodeDistance++;
        } else if (rightBurnNodeDistance > 0) {
            rightBurnNodeDistance++;
        }

        return new ResultValue(leftHeight, rightHeight, leftBurnNodeDistance, rightBurnNodeDistance);
    }

    static class ResultValue {
        int leftHeight;
        int rightHeight;
        int leftBurnNodeDistance;
        int rightBurnNodeDistance;

        public ResultValue(int leftHeight, int rightHeight, int leftBurnNodeDistance, int rightBurnNodeDistance) {
            this.leftHeight = leftHeight;
            this.rightHeight = rightHeight;
            this.leftBurnNodeDistance = leftBurnNodeDistance;
            this.rightBurnNodeDistance = rightBurnNodeDistance;
        }


        @Override
        public String toString() {
            return "RV{" +
                    "lh=" + leftHeight +
                    ", rh=" + rightHeight +
                    ", lbh=" + leftBurnNodeDistance +
                    ", rbh=" + rightBurnNodeDistance +
                    '}';
        }
    }


}
