package com.ds.graph.directed;

import com.util.Pair;

import java.util.*;

//https://www.youtube.com/watch?v=P_bfy0LOU5g&t=23s
public class ShortestPathDAG {

    private static double getShortestPathDAG(DirectedGraphWithWeight<Integer> graph, int source, int destination, int vertexCount) {

        List<Integer> topologicalSort = getTopologicalSort(graph, vertexCount);
        //Map<Pair<Integer, Integer>, Double> neighbourWeights = getNeighbourWeights(graph);
        double[] distance = new double[vertexCount];
        Arrays.fill(distance, Double.MAX_VALUE);

        distance[source] = 0;
        for (int i = 0; i < vertexCount; i++) {
            Integer fromNode = topologicalSort.get(i);
            if (fromNode < source) {
                continue;
            }
            double fromWeight = distance[fromNode];
            List<Pair<Integer, Double>> neighbours = graph.getNeighbours(fromNode);
            for (Pair<Integer, Double> neighbour : neighbours) {
                double weight = (fromWeight == Double.MAX_VALUE) ? neighbour.getSecond() : fromWeight + neighbour.getSecond();
                if (distance[neighbour.getFirst()] > weight) {
                    distance[neighbour.getFirst()] = weight;
                }
            }
        }

        if (distance[source] > distance[destination]) {
            return distance[source] - distance[destination];
        } else {
            return distance[destination] - distance[source];
        }

    }


    private static Map<Pair<Integer, Integer>, Double> getNeighbourWeights(DirectedGraphWithWeight<Integer> graph) {
        Map<Pair<Integer, Integer>, Double> map = new HashMap<>();
        for (Map.Entry<Integer, List<Pair<Integer, Double>>> entry : graph.adjacencyList.entrySet()) {
            Integer from = entry.getKey();
            for (Pair<Integer, Double> to : entry.getValue()) {
                map.put(Pair.of(from, to.getFirst()), to.getSecond());
            }
        }
        return map;
    }


    private static List<Integer> getTopologicalSort(DirectedGraphWithWeight<Integer> graph, int vertexCount) {

        boolean[] visited = new boolean[vertexCount];

        Stack<Integer> topologicalSortStacked = new Stack<>();


        List<Integer> topologicalSort = new LinkedList<>();

        for (Integer node : graph.adjacencyList.keySet()) {
            if (!visited[node]) {
                computeTopologicalSort(graph, node, visited, topologicalSortStacked);
            }
        }

        while (!topologicalSortStacked.isEmpty()) {
            topologicalSort.add(topologicalSortStacked.pop());
        }

        return topologicalSort;

    }

    private static void computeTopologicalSort(DirectedGraphWithWeight<Integer> graph, Integer node, boolean[] visited,
                                               Stack<Integer> topologicalSortStacked) {
        visited[node] = true;

        List<Pair<Integer, Double>> neighbours = graph.getNeighbours(node);
        for (Pair<Integer, Double> neighbour : neighbours) {
            if (!visited[neighbour.getFirst()]) {
                computeTopologicalSort(graph, neighbour.getFirst(), visited, topologicalSortStacked);
            }
        }
        topologicalSortStacked.push(node);
    }


    public static void main(String[] args) {

        DirectedGraphWithWeight<Integer> graph = new DirectedGraphWithWeight<>();

        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 6);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 4, 4);
        graph.addEdge(2, 5, 2);
        graph.addEdge(3, 4, -1);
        graph.addEdge(4, 5, -2);

        double path = getShortestPathDAG(graph, 1, 5, 6);

        System.out.println("Path between -> " + path); //3.0

    }


}
