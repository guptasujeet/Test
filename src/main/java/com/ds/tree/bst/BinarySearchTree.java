package com.ds.tree.bst;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@AllArgsConstructor
public class BinarySearchTree {

    BinaryTreeNode root;


    public List<Integer> inOrderTraversal() {
        List<Integer> traversal = new ArrayList<>();
        solveForInOrderTraversal(root, traversal);
        return traversal;
    }

    private void solveForInOrderTraversal(BinaryTreeNode root, List<Integer> traversal) {
        if (root == null) {
            return;
        }
        solveForInOrderTraversal(root.left, traversal);
        traversal.add(root.data);
        solveForInOrderTraversal(root.right, traversal);
    }

    public List<Integer> getLevelOrderTraversal() {
        List<Integer> traversal = new ArrayList<>();
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode current = queue.poll();
            traversal.add(current.data);
            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }
        return traversal;
    }

    public BinaryTreeNode getLowestCommonAncestor(int n1, int n2) {
        return LowestCommonAncestorBST.getLowestCommonAncestor(root, n1, n2);
    }


    public void deleteNode(int data) {
        DeletionInBST.deleteNode(root, data);
    }

    public int getMax() {
        return getMax(root);
    }

    public static int getMax(BinaryTreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node.data;
    }

    public static int getMin(BinaryTreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.data;
    }

    public List<Integer> geInOrderTraversal() {
        ArrayList<Integer> traversal = new ArrayList<>();
        inorderTraversal(root, traversal);
        return traversal;
    }

    public void inorderTraversal(BinaryTreeNode node, ArrayList<Integer> traversal) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, traversal);
        traversal.add(node.data);
        inorderTraversal(node.right, traversal);
    }

    public int getKthSmallestElement(int k) {
        List<Integer> inorderList = new BinarySearchTree(root).geInOrderTraversal();

        return inorderList.get(k);
    }

    public void convertToSortedDoublyLinkedList() {
        new BSTToDoublyLinkedList().convertToSortedDoublyLinkedList(root);
        BinaryTreeNode temp = root;
        while (temp != null) {
            root = temp;
            temp = temp.left;
        }
    }


}
