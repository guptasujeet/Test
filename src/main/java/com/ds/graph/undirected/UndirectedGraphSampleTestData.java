package com.ds.graph.undirected;

public class UndirectedGraphSampleTestData {

    public static UndirectedGraph<String> createSampleUndirectedGraph1() {
        UndirectedGraph<String> graph = new UndirectedGraph<>();
        graph.addVertex("Bob");
        graph.addVertex("Alice");
        graph.addVertex("Mark");
        graph.addVertex("Rob");
        graph.addVertex("Maria");
        graph.addVertex("Carlos");

        graph.addEdge("Bob", "Alice");
        graph.addEdge("Bob", "Rob");
        graph.addEdge("Alice", "Mark");
        graph.addEdge("Alice", "Maria");
        graph.addEdge("Rob", "Maria");
        graph.addEdge("Rob", "Mark");
        graph.addEdge("Maria", "Carlos");

        return graph;
    }

}
