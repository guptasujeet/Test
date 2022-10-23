package com.ds.tree.bst;

public class LowestCommonAncestorBST {

    public static BinaryTreeNode getLowestCommonAncestor(BinaryTreeNode root, int n1Data, int n2Data) {
        return solveForLowestCommonAncestor(root, n1Data, n2Data);
    }

    private static BinaryTreeNode solveForLowestCommonAncestor(BinaryTreeNode node, int n1, int n2) {

        if (node == null) {
            return null;
        }

        if (n1 <= node.data && n2 <= node.data) {
            return solveForLowestCommonAncestor(node.left, n1, n2);
        }

        if (n1 > node.data && n2 > node.data) {
            return solveForLowestCommonAncestor(node.right, n1, n2);
        }

        return node;

    }

}
