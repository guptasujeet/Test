package com.ds.tree.binary;

public class TreeNode {

    public String data;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "data='" + data + '\'' +
                '}';
    }
}
