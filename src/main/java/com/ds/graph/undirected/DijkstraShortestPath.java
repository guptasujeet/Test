package com.ds.graph.undirected;

import com.google.common.collect.Lists;
import com.util.Pair;

import java.util.*;


//https://www.codingninjas.com/codestudio/problems/dijkstra-s-shortest-path_920469?leftPanelTab=0&utm_source=youtube&utm_medium=affiliate&utm_campaign=Lovebabbar
//https://www.youtube.com/watch?v=dVUR3Rm6biE&t=806s
public class DijkstraShortestPath {


    public static ArrayList<Integer> dijkstra(ArrayList<ArrayList<Integer>> vec, int vertices, int edges, int source) {
        // Write your code here.
        UndirectedWeightedGraph<Integer> graph = new UndirectedWeightedGraph<>();
        for (ArrayList<Integer> integers : vec) {
            graph.addEdge(integers.get(0), integers.get(1), integers.get(2));
        }

        boolean[] visited = new boolean[vertices];
        double[] distance = new double[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0.0;

        computePath(graph, distance, visited, source);


        ArrayList<Integer> distanceList = new ArrayList<>();
        for (double i : distance) {
            distanceList.add((int) i);
        }

        return distanceList;
    }

    private static void computePath(UndirectedWeightedGraph<Integer> graph, double[] distance, boolean[] visited, int source) {

        PriorityQueue<Pair<Double, Integer>> minDistanceQueue = new PriorityQueue<>(Comparator.comparingDouble(Pair::getFirst));
        minDistanceQueue.add(Pair.of(0.0, source));

        while (!minDistanceQueue.isEmpty()) {
            Pair<Double, Integer> currentNodePair = minDistanceQueue.poll();
            double currentNodeWeight = currentNodePair.getFirst();
            Integer currentNode = currentNodePair.getSecond();
            visited[currentNode] = true;

            Collection<Pair<Integer, Double>> neighbours = graph.getAdjVertices(currentNode);
            if (neighbours != null) {
                for (Pair<Integer, Double> neighbour : neighbours) {
                    Integer neighbourNode = neighbour.getFirst();
                    if (!visited[neighbourNode]) {
                        double newWeight = currentNodeWeight + neighbour.getSecond();
                        if (newWeight < distance[neighbourNode]) {
                            distance[neighbourNode] = newWeight;
                            minDistanceQueue.add(Pair.of(newWeight, neighbour.getFirst()));
                        }
                    }
                }
            }
        }
    }


    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        edges.add(Lists.newArrayList(0, 1, 5));
        edges.add(Lists.newArrayList(0, 2, 8));
        edges.add(Lists.newArrayList(1, 2, 9));
        edges.add(Lists.newArrayList(1, 3, 2));
        edges.add(Lists.newArrayList(2, 3, 6));

        ArrayList<Integer> dijkstraPath = dijkstra(edges, 4, edges.size(), 0);

        System.out.println(dijkstraPath);

        ArrayList<ArrayList<Integer>> edges2 = new ArrayList<>();

        edges2.add(Lists.newArrayList(0, 1, 7));
        edges2.add(Lists.newArrayList(0, 2, 1));
        edges2.add(Lists.newArrayList(0, 3, 2));
        edges2.add(Lists.newArrayList(1, 2, 3));
        edges2.add(Lists.newArrayList(1, 3, 5));
        edges2.add(Lists.newArrayList(1, 4, 1));
        edges2.add(Lists.newArrayList(3, 4, 7));

        ArrayList<Integer> dijkstraPath2 = dijkstra(edges2, 5, edges.size(), 0);

        System.out.println(dijkstraPath2);


    }

}
