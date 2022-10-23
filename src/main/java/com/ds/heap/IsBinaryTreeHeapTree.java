package com.ds.heap;

import com.ds.tree.bst.BinaryTreeNode;

public class IsBinaryTreeHeapTree {

    public static String isBinaryHeap(BinaryTreeNode root) {
        int nodeCount = nodeCount(root);
        // Write your code here.
        if (isCBT(root, 0, nodeCount) && isHeap(root)) {
            return "True";
        } else {
            return "False";
        }
    }

    private static int nodeCount(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + nodeCount(root.left) + nodeCount(root.right);
    }


    private static boolean isHeap(BinaryTreeNode node) {

        //added extra below check
        if (node == null) {
            return true;
        }

        if (node.left == null && node.right == null) {
            return true;
        }


        if (node.right == null) {
            return node.data >= node.left.data;
        } else {
            if (isHeap(node.left) && isHeap(node.right)) {
                return node.data >= node.left.data && node.data >= node.right.data;
            }
        }
        return false;
    }

    public static boolean isCBT(BinaryTreeNode node, int index, int nodeCount) {
        if (node == null) {
            return true;
        }

        if (index >= nodeCount) {
            return false;
        }

        return isCBT(node.left, 2 * index + 1, nodeCount) && isCBT(node.right, 2 * index + 2, nodeCount);
    }


    public static void main(String[] args) {
        BinaryTreeNode root = getSampleTree();

        System.out.println("Is Heap -> " + isBinaryHeap(root));
    }


    public static BinaryTreeNode getSampleTree() {
        BinaryTreeNode root = new BinaryTreeNode(40);


        BinaryTreeNode n36 = new BinaryTreeNode(36);
        BinaryTreeNode n23 = new BinaryTreeNode(23);

        BinaryTreeNode n10 = new BinaryTreeNode(10);
        BinaryTreeNode n23_2 = new BinaryTreeNode(23);


        BinaryTreeNode n1 = new BinaryTreeNode(1);
        BinaryTreeNode n14 = new BinaryTreeNode(14);


        BinaryTreeNode n5 = new BinaryTreeNode(5);
        BinaryTreeNode n6 = new BinaryTreeNode(6);

        root.left = n36;
        root.right = n23;

        n36.left = n10;
        n36.right = n23_2;

        n10.left = n1;
        n10.right = n14;


        n23.left = n5;
        n23.right = n6;
        return root;
    }


}
