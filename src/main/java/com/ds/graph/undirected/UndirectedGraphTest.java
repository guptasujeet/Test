package com.ds.graph.undirected;

public class UndirectedGraphTest {

    // https://www.baeldung.com/java-graphs

    public static void main(String[] args) {
        UndirectedGraph<String> graph = UndirectedGraphSampleTestData.createSampleUndirectedGraph1();

        graph.printAdjList();

        System.out.println("BFS Traversal");
        System.out.println("-------------");
        System.out.println(graph.getBFSTraversal("Bob"));
        System.out.println("-------------");

        System.out.println("DFS Traversal");
        System.out.println("-------------");
        System.out.println(graph.getDFSTraversal("Bob"));
        System.out.println("-------------");

        System.out.println("DFS Traversal Rec");
        System.out.println("-------------");
        System.out.println(graph.getDFSTraversalRec("Bob"));
        System.out.println("-------------");

        String toRemove = "Mark";
        System.out.println("Removing -> " + toRemove);
        graph.removeVertex(toRemove);

        graph.printAdjList();

        System.out.println("Removing Bob / Rob edge");
        graph.removeEdge("Bob", "Rob");
        graph.printAdjList();


    }

}
