package com.ds.graph.directed;

import java.util.*;

//keeping vertex as simple integer node
public class DirectedGraph {

    Map<Integer, List<Integer>> adjacencyList = new LinkedHashMap<>();


    public void addVertex(Integer vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(Integer from, Integer to) {
        adjacencyList.compute(from, (k, v) -> {
            if (v == null) {
                v = new ArrayList<>();
            }
            v.add(to);
            return v;
        });
        //make sure to vertex is also populated
        adjacencyList.putIfAbsent(to, new ArrayList<>());
    }

    public List<Integer> getNeighbours(Integer vertex) {
        return adjacencyList.getOrDefault(vertex, Collections.emptyList());
    }

    public void printAdjacencyList() {
        StringBuilder output = new StringBuilder();
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            output.append(entry.getKey()).append(" -> ").append(entry.getValue());
        }
        System.out.println(output);
    }

}
