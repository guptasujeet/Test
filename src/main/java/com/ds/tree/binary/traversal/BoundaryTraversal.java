package com.ds.tree.binary.traversal;

import com.ds.tree.binary.BinaryTree;
import com.ds.tree.binary.TreeNode;
import com.ds.tree.binary.TreeSampleData;

import java.util.ArrayList;
import java.util.List;

public class BoundaryTraversal {


    public static List<String> boundaryTraversal(TreeNode node) {
        List<String> answer = new ArrayList<>();

        answer.add(node.data);
        //traverse left first except leaf  node
        traverseLeft(node.left, answer);

        //traverse all leaf node
        traverseLeafNode(node.left, answer);
        traverseLeafNode(node.right, answer);

        //traverse right except root node and leaf node
        //notice it is node.right and NOT node which is passed from here
        traverseRightNode(node.right, answer);
        return answer;
    }

    private static void traverseLeft(TreeNode node, List<String> answer) {

        if ((node == null) || (node.left == null && node.right == null)) {
            return;
        }
        answer.add(node.data);
        if (node.left != null) {
            traverseLeft(node.left, answer);
        } else {
            traverseLeft(node.right, answer);
        }
    }

    private static void traverseLeafNode(TreeNode node, List<String> answer) {
        if (node == null) {
            return;
        }
        if ((node.left == null && node.right == null)) {
            answer.add(node.data);
        }
        traverseLeafNode(node.left, answer);
        traverseLeafNode(node.right, answer);
    }

    private static void traverseRightNode(TreeNode node, List<String> answer) {
        if ((node == null) || (node.left == null && node.right == null)) {
            return;
        }

        if (node.right != null) {
            traverseRightNode(node.right, answer);
        } else {
            traverseRightNode(node.left, answer);
        }
        answer.add(node.data);
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(TreeSampleData.createSampleData3());

        System.out.println(tree.getBoundaryTraversal());

        BinaryTree tree2 = getSampleBinaryTree();
        System.out.println(tree2.getBoundaryTraversal());
        System.out.println(tree2);

    }


    private static BinaryTree getSampleBinaryTree() {
        TreeNode root = new TreeNode("4");
        root.right = new TreeNode("5");
        root.right.left = new TreeNode("8");
        root.right.right = new TreeNode("6");
        root.right.right.left = new TreeNode("7");
        return new BinaryTree(root);
    }
}
