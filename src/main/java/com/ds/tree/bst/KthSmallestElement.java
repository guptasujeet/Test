package com.ds.tree.bst;

import java.util.List;

public class KthSmallestElement {


    public static int getKthSmallestElement(BinaryTreeNode root, int k) {
        List<Integer> inorderList = new BinarySearchTree(root).geInOrderTraversal();

        return inorderList.get(k);
    }

}
