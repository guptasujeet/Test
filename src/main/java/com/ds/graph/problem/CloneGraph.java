package com.ds.graph.problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph {

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode root) {

        Map<Integer, Boolean> visited = new HashMap<>();
        Map<Integer, UndirectedGraphNode> newNodes = new HashMap<>();

        UndirectedGraphNode newRoot = new UndirectedGraphNode(root.label);
        newNodes.put(root.label, newRoot);

        for (UndirectedGraphNode neighbor : root.neighbors) {
            if (!visited.getOrDefault(neighbor.label, false)) {
                dfs(neighbor, visited, newNodes);
            }
        }

        visited.clear();

        for (UndirectedGraphNode neighbor : root.neighbors) {
            if (!visited.getOrDefault(neighbor.label, false)) {
                cloneNew(neighbor, visited, newNodes);
            }
        }

        return newNodes.get(newRoot.label);

    }

    private void cloneNew(UndirectedGraphNode node, Map<Integer, Boolean> visited, Map<Integer, UndirectedGraphNode> newNodes) {
        visited.put(node.label, true);

        UndirectedGraphNode newNode = newNodes.get(node.label);

        for (UndirectedGraphNode neighbor : node.neighbors) {
            newNode.neighbors.add(newNodes.get(neighbor.label));
            if (!visited.getOrDefault(neighbor.label, false)) {
                cloneNew(neighbor, visited, newNodes);
            }
        }

    }


    private void dfs(UndirectedGraphNode node, Map<Integer, Boolean> visited, Map<Integer, UndirectedGraphNode> newNodes) {

        visited.put(node.label, true);

        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        newNodes.put(node.label, newNode);

        for (UndirectedGraphNode neighbor : node.neighbors) {
            if (!visited.getOrDefault(neighbor.label, false)) {
                dfs(neighbor, visited, newNodes);
            }
        }
    }


    static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }

        @Override
        public String toString() {
            return "UndirectedGraphNode{" +
                    "label=" + label +
                    '}';
        }
    }


    public static void main(String[] args) {

        CloneGraph cloneGraph = new CloneGraph();

        UndirectedGraphNode graphNode1 = new UndirectedGraphNode(1);
        UndirectedGraphNode graphNode2 = new UndirectedGraphNode(2);
        UndirectedGraphNode graphNode3 = new UndirectedGraphNode(3);
        UndirectedGraphNode graphNode4 = new UndirectedGraphNode(4);

        graphNode1.neighbors.add(graphNode3);
        graphNode1.neighbors.add(graphNode2);

        graphNode2.neighbors.add(graphNode1);
        graphNode2.neighbors.add(graphNode4);

        graphNode3.neighbors.add(graphNode1);
        graphNode3.neighbors.add(graphNode4);

        graphNode4.neighbors.add(graphNode2);
        graphNode4.neighbors.add(graphNode3);


        UndirectedGraphNode clonedGraph = cloneGraph.cloneGraph(graphNode1);
        System.out.println(clonedGraph);

    }

}
