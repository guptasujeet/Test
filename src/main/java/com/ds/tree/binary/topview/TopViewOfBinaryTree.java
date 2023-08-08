package com.ds.tree.binary.topview;

import com.ds.tree.binary.TreeNode;

import java.util.*;

// https://practice.geeksforgeeks.org/problems/top-view-of-binary-tree/1
public class TopViewOfBinaryTree {


    public static ArrayList<String> getTopView(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        Map<TreeNode, Integer> distancePerNode = new HashMap<>();
        Map<Integer, String> nodesPerDistance = new TreeMap<>();
        distancePerNode.put(root, 0);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                Integer distance = distancePerNode.getOrDefault(node, 0);
                nodesPerDistance.putIfAbsent(distance, node.data);
                if (node.left != null) {
                    distancePerNode.put(node.left, distance - 1);
                    queue.add(node.left);
                }
                if (node.right != null) {
                    distancePerNode.put(node.right, distance + 1);
                    queue.add(node.right);
                }
            }
        }

        return new ArrayList<>(nodesPerDistance.values());
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode("1");
        root.left = new TreeNode("2");
        root.right = new TreeNode("3");

        root.left.left = new TreeNode("4");
        root.left.right = new TreeNode("5");

        root.right.left = new TreeNode("6");
        root.right.right = new TreeNode("7");

        System.out.println(getTopView(root)); // 4 2 1 3 7
    }

}
