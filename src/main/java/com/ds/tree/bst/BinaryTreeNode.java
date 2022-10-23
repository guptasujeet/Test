package com.ds.tree.bst;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BinaryTreeNode {

    public int data;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BTN{" +
                "data=" + data +
                '}';
    }
}
