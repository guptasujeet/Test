package com.ds.tree.binary.traversal;

import com.ds.tree.binary.BinaryTree;
import com.ds.tree.binary.TreeNode;
import com.ds.tree.binary.TreeSampleData;

import java.util.*;

public class ZigZagTraversal {


    public static List<String> zigZag(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<String> answer = new ArrayList<>();
        boolean isLeftToRight = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            String[] levelAnswer = new String[size];
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                int index = isLeftToRight ? i : size - 1 - i;
                levelAnswer[index] = node.data;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            answer.addAll(Arrays.asList(levelAnswer));
            isLeftToRight = !isLeftToRight;
        }
        return answer;
    }


    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree(TreeSampleData.createSampleData2());
        System.out.println(binaryTree.getZigZagTraversal());
        System.out.println(binaryTree.toString());
    }
}
