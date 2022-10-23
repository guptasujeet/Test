package com.ds.tree.bst;

public class BSTToDoublyLinkedList {


    private BinaryTreeNode head = null;


    public void convertToSortedDoublyLinkedList(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        convertToSortedDoublyLinkedList(root.right);

        root.right = head;


        if (root.right != null) {
            root.right.left = root;
        }

        head = root;

        convertToSortedDoublyLinkedList(root.left);

    }


}
