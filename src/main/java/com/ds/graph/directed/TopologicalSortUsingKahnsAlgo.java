package com.ds.graph.directed;

import com.google.common.collect.Lists;

import java.util.*;

//i.e using queue
//https://www.youtube.com/watch?v=6XmzL04mlgQ
public class TopologicalSortUsingKahnsAlgo {

    public static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> edges,
                                                     int vertexCount, int edgeCount) {
        // Write your code here


        ArrayList<Integer> topologicalSort = new ArrayList<>();
        boolean[] visited = new boolean[vertexCount];
        DirectedGraph graph = new DirectedGraph();

        int[] inDegreeOfNode = new int[vertexCount];
        for (ArrayList<Integer> edge : edges) {
            graph.addEdge(edge.get(0), edge.get(1));
            inDegreeOfNode[edge.get(1)]++;
        }

        if (vertexCount == 0 || edgeCount == 0) {
            return new ArrayList<>();
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int node = 0; node < vertexCount; node++) {
            if (inDegreeOfNode[node] == 0) {
                queue.add(node);
            }
        }

        solveForTopologicalSortUsingKahn(graph, queue, topologicalSort, visited, inDegreeOfNode);

        return topologicalSort;
    }

    private static void solveForTopologicalSortUsingKahn(DirectedGraph graph, Queue<Integer> queue, Collection<Integer> topologicalSort,
                                                         boolean[] visited, int[] inDegreeOfNode) {

        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            visited[node] = true;

            List<Integer> neighbours = graph.getNeighbours(node);
            for (Integer neighbour : neighbours) {
                if (!visited[neighbour]) {
                    //decrement indegree of the neighbourig node
                    inDegreeOfNode[neighbour]--;

                    //if indegree is now 0 add queue
                    if (inDegreeOfNode[neighbour] == 0) {
                        queue.add(neighbour);
                    }
                }
            }
            //after processing node add node to ans
            topologicalSort.add(node);
        }
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
    }

}
