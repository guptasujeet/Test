package com.ds.tree.binary.traversal;

import com.ds.tree.binary.BinaryTree;
import com.ds.tree.binary.TreeNode;
import com.ds.tree.binary.TreeSampleData;

import java.util.*;

public class VerticalOrderTraversal {

    public static ArrayList<String> verticalOrderTraversal(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        Map<TreeNode, Integer> distancePerNode = new HashMap<>();
        Map<Integer, List<String>> nodesPerDistance = new TreeMap<>();
        distancePerNode.put(root, 0);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            Integer distance = distancePerNode.getOrDefault(node, 0);
            addNode(nodesPerDistance, distance, node.data);
            if (node.left != null) {
                distancePerNode.put(node.left, distance - 1);
                queue.add(node.left);
            }
            if (node.right != null) {
                distancePerNode.put(node.right, distance + 1);
                queue.add(node.right);
            }
        }

        ArrayList<String> ans = new ArrayList<>();
        for (List<String> nodes : nodesPerDistance.values()) {
            ans.addAll(nodes);
        }
        return ans;
    }

    public static void addNode(Map<Integer, List<String>> nodesPerDistance, Integer distance, String data) {
        if (!nodesPerDistance.containsKey(distance)) {
            nodesPerDistance.put(distance, new ArrayList<>());
        }
        nodesPerDistance.get(distance).add(data);
    }


    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(TreeSampleData.createSampleData3());

        System.out.println(tree.getVerticalTraversal());


        System.out.println(tree.toString());
    }


}
