package com.ds.tree;

import java.util.*;

public class MinTimeToBurnTreeUsingQueue {


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return "{" + val + '}';
        }
    }


    public int solve(TreeNode root, int toBurn) {
        Map<Integer, TreeNode> nodeToParent = getNodeToParentMapping(root);
        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();

        int time = -1;
        TreeNode parentOfToBurn = nodeToParent.get(toBurn);
        TreeNode nodeToBurn = parentOfToBurn.left.val == toBurn ? parentOfToBurn.left : parentOfToBurn.right;

        queue.add(nodeToBurn);

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean counted = false;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null && !visited.contains(node)) {
                    counted = true;
                    visited.add(node);
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                    if (nodeToParent.containsKey(node.val)) {
                        queue.add(nodeToParent.get(node.val));
                    }
                }
            }
            if (counted) {
                time++;
            }
        }
        return time;
    }


    public Map<Integer, TreeNode> getNodeToParentMapping(TreeNode root) {
        Map<Integer, TreeNode> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                map.put(node.left.val, node);
                queue.add(node.left);
            }

            if (node.right != null) {
                map.put(node.right.val, node);
                queue.add(node.right);
            }
        }

        return map;
    }


    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);

        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);

        MinTimeToBurnTreeUsingQueue burnTime = new MinTimeToBurnTreeUsingQueue();
        System.out.println(burnTime.solve(root, 4));

    }


}
