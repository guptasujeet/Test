package com.ds.tree.binary;

public class HeightOfBinaryTree {


    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree(TreeSampleData.createSampleData());
        System.out.println(tree.toString());

        System.out.println("Diameter -> " + tree.getDiameter());


        BinaryTree tree3 = new BinaryTree(TreeSampleData.createSampleData3());

        System.out.println(tree.getHeight());
        System.out.println(tree3.getHeight());


    }

}
