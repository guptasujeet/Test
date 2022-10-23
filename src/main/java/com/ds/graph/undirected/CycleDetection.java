package com.ds.graph.undirected;

import java.util.*;

//https://www.youtube.com/watch?v=1cSzxlhxOw8&t=1403s
public class CycleDetection {

    public static String cycleDetection(int[][] edges, int n, int m) {
        // Write your code here.

        UndirectedGraph<Integer> graph = new UndirectedGraph<>();
        for (int i = 0; i < edges.length; i++) {
            graph.addEdge(edges[i][0], edges[i][1]);
        }

        List<Integer> visited = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            boolean hasCycle = detectCycleDFS(i, -1, visited, graph);
            if (hasCycle) {
                return "Yes";
            }
        }
        return "No";
    }

    private static boolean detectCycleDFS(int node, int parent, List<Integer> visited, UndirectedGraph<Integer> graph) {
        visited.add(node);

        Collection<Integer> adjVertices = graph.getAdjVertices(node);
        for (Integer adjVertex : adjVertices) {
            if (!visited.contains(adjVertex)) {
                boolean isCyclic = detectCycleDFS(adjVertex, node, visited, graph);
                if (isCyclic) {
                    return true;
                }
            } else if (node != parent) {
                return true;
            }
        }
        return false;
    }


    //not working properly
    private static boolean detectCycleBFS(UndirectedGraph<Integer> graph, List<Integer> visited, Map<Integer, Integer> parentMap) {

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(graph.getAdjVertices().keySet().iterator().next());
        while (!queue.isEmpty()) {
            Integer parentVertex = queue.poll();
            visited.add(parentVertex);
            if (!parentMap.containsKey(parentVertex)) {
                parentMap.put(parentVertex, -1);
            }
            Collection<Integer> neighbours = graph.getAdjVertices(parentVertex);
            queue.addAll(neighbours);
            for (Integer neighbour : neighbours) {
                if (!parentMap.containsKey(neighbour)) {
                    parentMap.put(neighbour, parentVertex);
                }
                visited.add(neighbour);
                Integer neighboursParent = parentMap.getOrDefault(neighbour, -1);
                if (!neighboursParent.equals(parentVertex)
                        && visited.contains(neighbour)) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {

        //int[][] edges = new int[][]{{1, 2}, {2, 3}};
        //System.out.println(cycleDetection(edges, 3, 2));

        int[][] edges = new int[][]{
                {4, 3},
                {1, 4},

                {3, 1}};
        System.out.println(cycleDetection(edges, 3, 0));
    }

}
