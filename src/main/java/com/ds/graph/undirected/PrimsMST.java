package com.ds.graph.undirected;

import com.google.common.collect.Lists;
import com.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;


//https://www.codingninjas.com/codestudio/problems/prim-s-mst_1095633
//https://www.youtube.com/watch?v=rnYBi9N_vw4&t=6s

public class PrimsMST {

    public static ArrayList<ArrayList<Integer>> calculatePrimsMST(int nodes, int m, ArrayList<ArrayList<Integer>> g) {
        // Write your code here.

        //1 based indexing so +1

        UndirectedWeightedGraph<Integer> graph = new UndirectedWeightedGraph<>();
        for (ArrayList<Integer> edge : g) {
            graph.addEdge(edge.get(0), edge.get(1), edge.get(2));
        }

        double[] weights = new double[nodes + 1];
        int[] parent = new int[nodes + 1];

        boolean[] visited = new boolean[nodes + 1];

        Arrays.fill(weights, Double.MAX_VALUE);
        Arrays.fill(parent, -1);

        weights[1] = 0;
        for (int node = 1; node < nodes + 1; node++) {
            int min = Integer.MAX_VALUE;
            int minNode = 1;
            for (int iNode = 1; iNode < nodes + 1; iNode++) {
                if (!visited[iNode] && weights[iNode] < min) {
                    min = (int) weights[iNode];
                    minNode = iNode;
                }
            }

            computePrimsData(graph, weights, visited, parent, minNode);
        }

        return getPrimsMST(parent, weights);

    }

    public static ArrayList<ArrayList<Integer>> getPrimsMST(int[] parent, double[] weights) {
        HashSet<ArrayList<Integer>> mst = new HashSet<>();

        //starting from 2 as it is 1 based indexing and 1st node already processed
        for (int node = 2; node < parent.length; node++) {
            ArrayList<Integer> edge = new ArrayList<>();
            edge.add(parent[node]);
            edge.add(node);
            edge.add((int) weights[node]);
            mst.add(edge);
        }

        return new ArrayList<>(mst);
    }


    public static void computePrimsData(UndirectedWeightedGraph<Integer> graph, double[] weights,
                                        boolean[] visited, int[] parent, int node) {

        visited[node] = true;

        Collection<Pair<Integer, Double>> neighbours = graph.getAdjVertices(node);
        for (Pair<Integer, Double> neighbour : neighbours) {
            int neighbourNode = neighbour.getFirst();
            if (!visited[neighbourNode]) {
                double weight = neighbour.getSecond();
                if (weights[neighbourNode] > weight) {
                    weights[neighbourNode] = weight;
                    parent[neighbourNode] = node;
                }
            }
        }
    }


    public static void main(String[] args) {


        ArrayList<ArrayList<Integer>> edges1 = new ArrayList<>();
        edges1.add(Lists.newArrayList(1, 2, 2));
        edges1.add(Lists.newArrayList(1, 4, 6));
        edges1.add(Lists.newArrayList(2, 1, 2));
        edges1.add(Lists.newArrayList(2, 3, 3));
        edges1.add(Lists.newArrayList(2, 4, 8));
        edges1.add(Lists.newArrayList(2, 5, 5));
        edges1.add(Lists.newArrayList(3, 2, 3));
        edges1.add(Lists.newArrayList(3, 5, 7));
        edges1.add(Lists.newArrayList(4, 1, 6));
        edges1.add(Lists.newArrayList(4, 2, 8));
        edges1.add(Lists.newArrayList(4, 5, 9));
        edges1.add(Lists.newArrayList(5, 2, 5));
        edges1.add(Lists.newArrayList(5, 3, 7));
        edges1.add(Lists.newArrayList(5, 4, 9));

        ArrayList<ArrayList<Integer>> mst = calculatePrimsMST(5, edges1.size(), edges1);

        for (ArrayList<Integer> edge : mst) {
            System.out.println(edge);
        }


        System.out.println("---------------");
        ArrayList<ArrayList<Integer>> edges2 = new ArrayList<>();

        edges2.add(Lists.newArrayList(1, 2, 21));
        edges2.add(Lists.newArrayList(1, 4, 16));
        edges2.add(Lists.newArrayList(2, 1, 12));
        edges2.add(Lists.newArrayList(2, 3, 13));
        edges2.add(Lists.newArrayList(2, 4, 18));
        edges2.add(Lists.newArrayList(2, 5, 15));
        edges2.add(Lists.newArrayList(3, 2, 13));
        edges2.add(Lists.newArrayList(3, 5, 17));
        edges2.add(Lists.newArrayList(4, 1, 16));
        edges2.add(Lists.newArrayList(4, 2, 18));
        edges2.add(Lists.newArrayList(4, 5, 19));
        edges2.add(Lists.newArrayList(5, 1, 18));
        edges2.add(Lists.newArrayList(5, 2, 15));
        edges2.add(Lists.newArrayList(5, 3, 17));
        edges2.add(Lists.newArrayList(5, 4, 19));

        ArrayList<ArrayList<Integer>> mst2 = calculatePrimsMST(5, edges2.size(), edges2);

        for (ArrayList<Integer> edge : mst2) {
            System.out.println(edge);
        }

    }
}
