package com.ds.graph.undirected;

import com.util.Pair;

import java.util.*;


public class UndirectedWeightedGraph<T> {

    private final Map<T, Collection<Pair<T, Double>>> adjVertices = new HashMap<>();

    public Collection<Pair<T, Double>> getAdjVertices(T label) {
        return adjVertices.getOrDefault(label, Collections.emptyList());
    }


    public void addVertex(T label) {
        adjVertices.putIfAbsent(label, new ArrayList<>());
    }


    public void addEdge(T v1, T v2, double weight) {
        if (!adjVertices.containsKey(v1)) {
            addVertex(v1);
        }
        adjVertices.get(v1).add(Pair.of(v2, weight));

        if (!adjVertices.containsKey(v2)) {
            addVertex(v2);
        }
        adjVertices.get(v2).add(Pair.of(v1, weight));
    }


    public void printAdjList() {
        for (Map.Entry<T, Collection<Pair<T, Double>>> entry : adjVertices.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public Map<T, Collection<Pair<T, Double>>> getAdjVertices() {
        return adjVertices;
    }
}