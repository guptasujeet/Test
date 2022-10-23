package com.ds.tree.bst;

import com.util.Pair;

import java.util.concurrent.atomic.AtomicReference;

import static com.ds.tree.bst.UserInputBinarySearchTree.makeBinarySearchTree;

public class MergeTwoBST {


    public static void main(String[] args) {

        //  10, 6, 20, 4, 8, 15, 25, 7, 12, -1
        //  22, 27, 30, 5, 14, 17, 50, -1

        BinarySearchTree tree1 = makeBinarySearchTree();
        BinarySearchTree tree2 = makeBinarySearchTree();


        BinarySearchTree merged = mergeBST(tree1, tree2);

        System.out.println("Merged");
    }


    public static BinarySearchTree mergeBST(BinarySearchTree tree1, BinarySearchTree tree2) {
        tree1.convertToSortedDoublyLinkedList();
        tree1.root.left = null;
        tree2.convertToSortedDoublyLinkedList();
        tree2.root.left = null;
        Pair<Integer, BinaryTreeNode> pair = mergeSortedLinkedLists(tree1.root, tree2.root);

        int length = pair.getFirst();
        BinaryTreeNode root = pair.getSecond();
        BinaryTreeNode newRoot = binarySearchTreeFromSortedLinkedList(length, new AtomicReference<>(root));
        return new BinarySearchTree(newRoot);
    }

    private static BinaryTreeNode getMid(BinaryTreeNode root, int length) {
        int mid = length / 2;

        int count = 0;
        BinaryTreeNode midNode = root;
        while (count <= mid) {
            count++;
            midNode = midNode.right;
        }
        return midNode;
    }

    private static BinaryTreeNode binarySearchTreeFromSortedLinkedList(int length,
                                                                       AtomicReference<BinaryTreeNode> head) {

        if (length <= 0) {
            return null;
        }

        BinaryTreeNode left = binarySearchTreeFromSortedLinkedList(length / 2, head);

        BinaryTreeNode root = head.get();

        root.left = left;

        //head.set(head.get().right);
        head.set(root.right);


        BinaryTreeNode right = binarySearchTreeFromSortedLinkedList((length - length / 2 - 1), head);

        root.right = right;

        return root;
    }


    //in-place
    public static Pair<Integer, BinaryTreeNode> mergeSortedLinkedLists(BinaryTreeNode node1, BinaryTreeNode node2) {

        int nodeCount = 0;
        BinaryTreeNode head = null;
        BinaryTreeNode tail = null;
        while (node1 != null && node2 != null) {
            nodeCount++;
            if (node1.data < node2.data) {
                if (head == null) {
                    head = node1;
                    tail = node1;
                    head.left = null;
                } else {
                    tail.right = node1;
                    node1.left = tail;
                    tail = node1;
                }
                node1 = node1.right;
            } else {
                if (head == null) {
                    head = node2;
                    tail = node2;
                    head.left = null;
                } else {
                    tail.right = node2;
                    node2.left = tail;
                    tail = node2;
                }
                node2 = node2.right;
            }
        }


        while (node1 != null) {
            nodeCount++;
            if (head == null) {
                head = node1;
                tail = node1;
                head.left = null;
            } else {
                tail.right = node1;
                node1.left = tail;
                tail = node1;
            }
            node1 = node1.right;
        }

        while (node2 != null) {
            nodeCount++;
            if (head == null) {
                head = node2;
                tail = node2;
                head.left = null;
            } else {
                tail.right = node2;
                node2.left = tail;
                tail = node2;
            }
            node2 = node2.right;

        }

        return Pair.of(nodeCount, head);
    }


}
