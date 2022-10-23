package com.ds.tree.bst;

public class DeletionInBST {

    public static void deleteNode(BinaryTreeNode root, int data) {
        delete(root, data);
    }

    public static BinaryTreeNode delete(BinaryTreeNode node, int data) {
        if (node == null) {
            return null;
        }

        if (node.data == data) {
            //case 1 delete leaf node
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left != null && node.right == null) {
                return node.left;
            } else if (node.left == null && node.right != null) {
                return node.right;
            } else {
                //get the minimum from right side
                int min = BinarySearchTree.getMin(node.right);
                //copy the data to current node
                node.data = min;
                //delete the copied node from right side now
                node.right = delete(node.right, min);
            }

        } else {
            if (data > node.data) {
                node.right = delete(node.right, data);
            } else {
                node.left = delete(node.left, data);
            }
        }
        return node;
    }

}
