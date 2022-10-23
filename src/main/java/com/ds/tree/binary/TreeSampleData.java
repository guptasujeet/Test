package com.ds.tree.binary;

public class TreeSampleData {


    public static TreeNode createSampleData() {

        TreeNode n1 = new TreeNode("1");
        TreeNode n2 = new TreeNode("2");
        TreeNode n3 = new TreeNode("3");
        TreeNode n4 = new TreeNode("4");
        TreeNode n5 = new TreeNode("5");
        /*TreeNode n6 = new TreeNode("6");
        TreeNode n7 = new TreeNode("7");*/

        TreeNode root = n1;

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        n2.right = n5;

       /* n3.left = n6;
        n3.right = n7;*/
        return root;
    }

    public static TreeNode createSampleData2() {
        TreeNode n1 = new TreeNode("1");
        TreeNode n2 = new TreeNode("2");
        TreeNode n3 = new TreeNode("3");
        TreeNode n4 = new TreeNode("4");
        TreeNode n5 = new TreeNode("5");
        TreeNode n6 = new TreeNode("6");
        TreeNode n7 = new TreeNode("7");

        TreeNode root = n1;

        n1.left = n2;
        n1.right = n3;

        //n2.left = n4;
        n2.right = n5;

        n3.left = n6;
        n3.right = n7;

        return root;
    }


    public static TreeNode createSampleData3() {
        TreeNode n1 = new TreeNode("1");
        TreeNode n2 = new TreeNode("2");
        TreeNode n3 = new TreeNode("3");
        TreeNode n4 = new TreeNode("4");
        TreeNode n5 = new TreeNode("5");
        TreeNode n6 = new TreeNode("6");
        TreeNode n7 = new TreeNode("7");
        TreeNode n8 = new TreeNode("8");
        TreeNode n9 = new TreeNode("9");

        TreeNode root = n1;

        n1.left = n2;
        n1.right = n3;

        //n2.left = n4;
        n2.right = n5;

        n3.left = n6;
        n3.right = n7;

        n5.left = n8;
        n8.left = n9;

        return root;
    }


}
