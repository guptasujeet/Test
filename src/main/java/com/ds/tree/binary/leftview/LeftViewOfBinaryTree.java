package com.ds.tree.binary.leftview;

import com.ds.tree.binary.BinaryTree;
import com.ds.tree.binary.TreeNode;
import com.ds.tree.binary.TreeSampleData;

import java.util.List;

public class LeftViewOfBinaryTree {


    public static void main(String[] args) {

        TreeNode root = TreeSampleData.createSampleData();
        BinaryTree binaryTree = new BinaryTree(root);
        binaryTree.printInTraversalData();
        binaryTree.printPreTraversalData();
        binaryTree.printPostTraversalData();

        binaryTree.printLevelOrderTraversal();
        List<String> leftViewOfTree = binaryTree.getLeftViewOfTree();
        leftViewOfTree.forEach(e -> System.out.print(e + " -> "));
        System.out.println();

        BinaryTree binaryTree2 = new BinaryTree(TreeSampleData.createSampleData2());

        binaryTree2.printLevelOrderTraversal();

        List<String> leftViewOfTree2 = binaryTree2.getLeftViewOfTree();
        leftViewOfTree2.forEach(e -> System.out.print(e + " -> "));
    }


}
