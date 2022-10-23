package com.ds.tree.bst;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class UserInputBinarySearchTree {

    public static void main(String[] args) {

        BinarySearchTree tree = makeBinarySearchTree();

        //  10, 6, 20, 4, 8, 15, 25, 7, 12, -1
        //  22, 27, 30, 5, 14, 17, 50
        System.out.println("------------------------------");

        System.out.println("To Sorted Link List -> \n" + toStringLeftRight(tree.root));


        //tree.convertToSortedDoublyLinkedList();
        System.out.println("Kth Smallest Element -> " + tree.getKthSmallestElement(3));
        System.out.println("Lowest Common Ancestor -> " + tree.getLowestCommonAncestor(4, 8));
        System.out.println("Lowest Common Ancestor -> " + tree.getLowestCommonAncestor(12, 25));
        System.out.println("Lowest Common Ancestor -> " + tree.getLowestCommonAncestor(4, 25));
        System.out.println("Lowest Common Ancestor -> " + tree.getLowestCommonAncestor(6, 7));

        System.out.println("InOrder -> " + tree.inOrderTraversal());
        System.out.println("LevelOrder -> " + tree.getLevelOrderTraversal());

        int nodeToDelete = 6;
        System.out.println("Deleting Node -> " + nodeToDelete);

        tree.deleteNode(nodeToDelete);
        System.out.println("InOrder -> " + tree.inOrderTraversal());
        System.out.println("LevelOrder -> " + tree.getLevelOrderTraversal());


    }

    private static String toStringLeftRight(BinaryTreeNode root) {

        StringBuilder right = new StringBuilder();
        BinaryTreeNode node = root;
        while (node != null) {
            right.append(node.data).append(" -> ");
            node = node.right;
        }
        right.append(" NULL ");

        StringBuilder left = new StringBuilder();
        node = root;
        while (node != null) {
            left.append(node.data).append(" -> ");
            node = node.left;
        }
        left.append(" NULL ");

        return right.toString() + " \n" + left.toString();
    }


    public static BinarySearchTree makeBinarySearchTree() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the nodes");
        BinaryTreeNode root = null;
        String data = scanner.nextLine();

        String[] split = StringUtils.split(data, ",");

        for (String singleData : split) {
            int val = Integer.parseInt(singleData.trim());
            if (root == null) {
                root = new BinaryTreeNode(val);
            } else {
                if (val != -1) {
                    insertIntoBinarySearchTree(root, val);
                }
            }
        }
        return new BinarySearchTree(root);
    }

    public static BinaryTreeNode insertIntoBinarySearchTree(BinaryTreeNode node, int data) {
        if (node == null) {
            return new BinaryTreeNode(data);
        }

        if (data < node.data) {
            node.left = insertIntoBinarySearchTree(node.left, data);
        } else {
            node.right = insertIntoBinarySearchTree(node.right, data);
        }
        return node;
    }

}
