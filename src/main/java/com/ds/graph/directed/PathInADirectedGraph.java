package com.ds.graph.directed;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://www.interviewbit.com/problems/path-in-directed-graph/
public class PathInADirectedGraph {
    public int solve(int A, ArrayList<ArrayList<Integer>> edges) {


        DirectedGraph graph = new DirectedGraph();
        for (ArrayList<Integer> edge : edges) {
            graph.addEdge(edge.get(0), edge.get(1));
        }


        boolean[] visited = new boolean[A + 1];

        for (int node = 1; node <= A; node++) {
            if (!visited[node]) {
                if (pathExists(A, node, graph, visited)) {
                    return 1;
                }
            }
        }

        return 0;
    }


    private boolean pathExists(int destination, Integer node, DirectedGraph graph, boolean[] visited) {

        visited[node] = true;

        List<Integer> neighbours = graph.getNeighbor(node);
        if (neighbours == null || neighbours.isEmpty()) {
            return false;
        }
        for (Integer neighbor : neighbours) {
            if (neighbor == null) {
                continue;
            }
            if (neighbor.equals(destination)) {
                return true;
            }
            if (!visited[neighbor]) {
                if (pathExists(destination, neighbor, graph, visited)) {
                    return true;
                }
            }
        }

        return false;

    }


    static class DirectedGraph {
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        public List<Integer> getNeighbor(Integer node) {
            return adjList.get(node);
        }

        public void addEdge(Integer from, Integer to) {
            adjList.compute(from, (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }
                v.add(to);
                return v;
            });

            //make sure to add to node as well
            adjList.compute(to, (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }
                return v;
            });
        }
    }


    public static void main(String[] args) {

        PathInADirectedGraph path = new PathInADirectedGraph();

        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        edges.add(Lists.newArrayList(1, 2));
        //System.out.println(path.solve(2, edges));


        ArrayList<ArrayList<Integer>> edges2 = new ArrayList<>();
        edges2.add(Lists.newArrayList(1, 2));
        edges2.add(Lists.newArrayList(2, 3));
        edges2.add(Lists.newArrayList(3, 1));
        System.out.println(path.solve(3, edges2));
    }
}
