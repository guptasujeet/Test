package com.ds.tree.binary;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class UserInputBinaryTree {


    public static void main(String[] args) {
        BinaryTree tree = makeBinaryTree();


        System.out.println("------------------------------");

        System.out.println("Sum root to leaf -> " + tree.sumRootToLeaf());

        if (true) {
            System.exit(0);
        }

        System.out.println("Max BST length in Binary Tree  -> " + tree.largestBSTLengthInBinaryTree());
        System.out.println("Morris InOrder -> " + tree.morrisInOrderTraversal());


        System.out.println("Time to burn tree node -> " + tree.getTimeToBurnNode("8"));

        System.out.println("Non Adjacent Node Max Sum -> " + tree.getMaximumSumNonAdjacentNode());
        System.out.println("Kth Ancestor -> " + tree.getKthAncestor(new TreeNode("5"), 2));

        System.out.println("Sum K Path -> " + tree.getSumKPath(5));


        System.out.println(tree.toString());


        System.out.println("Lowest Common Ancestor -> " + tree.getLowestCommonAncestor(new TreeNode("5"), new TreeNode("4")));
        System.out.println("Longest Path Sum -> " + tree.getLongestPathSum());

        System.out.println("Is Sum Tree -> " + tree.isSumTree());
        System.out.println("Is Balanced -> " + tree.isBalanced());
        System.out.println("Height -> " + tree.getHeight());
        System.out.println("Diameter -> " + tree.getDiameter());

        tree.printLevelOrderTraversal();
        tree.printPreTraversalData();
        tree.printInTraversalData();
        tree.printPostTraversalData();


    }

    private static BinaryTree makeBinaryTree() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter blank to skip");
        System.out.println("Enter the root node");

        String data = scanner.nextLine();

        TreeNode root = new TreeNode(data);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if (node == null) {
                break;
            }
            System.out.println("Enter left of " + node.data);
            data = scanner.nextLine();
            if (StringUtils.isNotBlank(data) && !"--1".equals(data)) {
                node.left = new TreeNode(data);
                queue.add(node.left);
            }

            System.out.println("Enter right of " + node.data);
            data = scanner.nextLine();
            if (StringUtils.isNotBlank(data) && !"--1".equals(data)) {
                node.right = new TreeNode(data);
                queue.add(node.right);
            }
        }
        return new BinaryTree(root);
    }

}
