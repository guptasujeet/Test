package com.ds.graph.directed;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//https://www.youtube.com/watch?v=T_boOrr0rvk
public class TopologicalSortDAG {

    public static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> edges,
                                                     int vertexCount, int edgeCount) {
        // Write your code here


        Stack<Integer> topologicalSort = new Stack<>();
        boolean[] visited = new boolean[vertexCount];
        DirectedGraph graph = new DirectedGraph();
        for (ArrayList<Integer> edge : edges) {
            graph.addEdge(edge.get(0), edge.get(1));
        }

        for (int mainNode = 0; mainNode < vertexCount; mainNode++) {
            if (!visited[mainNode]) {
                solveForTopologicalSort(graph, mainNode, topologicalSort, visited);
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();
        while (!topologicalSort.isEmpty()) {
            answer.add(topologicalSort.pop());
        }
        return answer;
    }

    private static void solveForTopologicalSort(DirectedGraph graph, Integer node, Stack<Integer> topologicalSort,
                                                boolean[] visited) {
        visited[node] = true;
        List<Integer> neighbours = graph.getNeighbours(node);
        for (Integer neighbour : neighbours) {
            if (!visited[neighbour]) {
                solveForTopologicalSort(graph, neighbour, topologicalSort, visited);
            }
        }
        topologicalSort.push(node);
    }


    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        edges.add(Lists.newArrayList(0, 1));
        edges.add(Lists.newArrayList(0, 2));
        System.out.println(topologicalSort(edges, 3, 2));

        ArrayList<ArrayList<Integer>> edges2 = new ArrayList<>();
        edges2.add(Lists.newArrayList(1, 0));
        System.out.println(topologicalSort(edges2, 2, 1));


        ArrayList<ArrayList<Integer>> edges3 = new ArrayList<>();
        System.out.println(topologicalSort(edges3, 1, 0));


        ArrayList<ArrayList<Integer>> edges4 = new ArrayList<>();
        edges4.add(Lists.newArrayList(1, 0));
        edges4.add(Lists.newArrayList(4, 4));
        edges4.add(Lists.newArrayList(0, 1));
        edges4.add(Lists.newArrayList(1, 2));
        edges4.add(Lists.newArrayList(2, 3));
        edges4.add(Lists.newArrayList(3, 1));

        System.out.println(topologicalSort(edges4, 5, edges4.size()));
    }

}
