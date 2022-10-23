package com.ds.tree.binary;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

//https://www.youtube.com/watch?v=ffE1xj51EBQ
public class TreeFromInOrderAndPreOrder {

    public static TreeNode buildTree(String[] preorder, String[] inorder) {
        Map<String, Integer> inOrderIndexMap = inOrderIndex(inorder);
        return solveForOrder(preorder, new AtomicInteger(0), 0, preorder.length - 1, inOrderIndexMap);
    }


    public static TreeNode solveForOrder(String[] preorder,
                                         AtomicInteger preOrderStartIndex,
                                         int inOrderStartIndex, int inOrderEndIndex,

                                         Map<String, Integer> inOrderIndexMap) {

        if (preOrderStartIndex.intValue() >= preorder.length || inOrderStartIndex > inOrderEndIndex) {
            return null;
        }
        String data = preorder[preOrderStartIndex.getAndIncrement()];
        TreeNode root = new TreeNode(data);
        int position = inOrderIndexMap.get(data);
        root.left = solveForOrder(preorder, preOrderStartIndex, inOrderStartIndex, position - 1, inOrderIndexMap);
        root.right = solveForOrder(preorder, preOrderStartIndex, position + 1, inOrderEndIndex, inOrderIndexMap);

        return root;
    }

    private static Map<String, Integer> inOrderIndex(String[] inOrder) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i], i);
        }

        return map;
    }

    public static void main(String[] args) {
        String[] preOrder = {"3", "9", "20", "15", "7"};
        String[] inOrder = {"9", "3", "15", "20", "7"};
        TreeNode root = buildTree(preOrder, inOrder);

        System.out.println("Root Node -> " + root);
    }


}
