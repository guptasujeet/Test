package com.ds.graph.directed;

import com.util.Pair;

import java.util.*;

//keeping vertex as simple integer node
public class DirectedGraphWithWeight<T> {

    Map<T, List<Pair<T, Double>>> adjacencyList = new LinkedHashMap<>();


    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(T from, T to, double weight) {
        adjacencyList.compute(from, (k, v) -> {
            if (v == null) {
                v = new ArrayList<>();
            }
            v.add(Pair.of(to, weight));
            return v;
        });
        //make sure to vertex is also populated
        adjacencyList.putIfAbsent(to, new ArrayList<>());
    }

    public List<Pair<T, Double>> getNeighbours(T vertex) {
        return adjacencyList.getOrDefault(vertex, Collections.emptyList());
    }

    public void printAdjacencyList() {
        StringBuilder output = new StringBuilder();
        for (Map.Entry<T, List<Pair<T, Double>>> entry : adjacencyList.entrySet()) {
            output.append(entry.getKey()).append(" -> ").append(entry.getValue());
        }
        System.out.println(output);
    }

}
