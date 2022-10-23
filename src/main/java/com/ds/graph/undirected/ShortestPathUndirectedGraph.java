package com.ds.graph.undirected;

import java.util.*;

//https://www.youtube.com/watch?v=abIEXKFpLNE
//https://www.codingninjas.com/codestudio/problems/shortest-path-in-an-unweighted-graph_981297
public class ShortestPathUndirectedGraph {

    public static LinkedList<Integer> shortestPath(int[][] edges, int vertexCount, int edgeCount, int source, int destination) {

        // Write your code here.
        UndirectedGraph<Integer> graph = new UndirectedGraph<>();
        for (int i = 0; i < edgeCount; i++) {
            int[] edge = edges[i];
            graph.addEdge(edge[0], edge[1]);
        }

        Map<Integer, Integer> parent = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[vertexCount + 1]; //1 based indexing


        queue.add(source);
        //find parent first of each node starting source node
        computeParentUsingBFS(source, graph, queue, parent, visited);


        LinkedList<Integer> shortestPath = new LinkedList<>();

        //find parent from destination node
        shortestPath.push(destination);
        int node = destination;

        while (node != source) {
            node = parent.get(node);
            shortestPath.push(node); //using push instead of add
        }

        return shortestPath;

    }


    private static void computeParentUsingBFS(Integer root, UndirectedGraph<Integer> graph,
                                              Queue<Integer> queue, Map<Integer, Integer> parent, boolean[] visited) {

        queue.add(root);
        visited[root] = true;
        if (!parent.containsKey(root)) {
            parent.put(root, -1);
        }
        while (!queue.isEmpty()) {
            Integer node = queue.poll();

            Collection<Integer> neighbours = graph.getAdjVertices(node);

            for (Integer neighbour : neighbours) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    parent.put(neighbour, node);
                    queue.add(neighbour);
                }
            }
        }
    }


    //https://www.codingninjas.com/codestudio/problems/shortest-path-in-an-unweighted-graph_981297
    public static void main(String[] args) {
        int edgeCount = 9;
        int[][] edges = new int[][]{
                {1, 2},
                {1, 3},
                {1, 4},

                {2, 5},
                {4, 6},
                {3, 8},

                {5, 8},
                {6, 7},

                {7, 8}
        };


        System.out.println(shortestPath(edges, 8, edgeCount, 1, 8));
    }

}
