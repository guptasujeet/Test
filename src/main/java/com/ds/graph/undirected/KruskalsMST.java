package com.ds.graph.undirected;

//https://www.codingninjas.com/codestudio/problems/minimum-spanning-tree_631769
//https://www.youtube.com/watch?v=KxLtIrCyXwE&t=1076s

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Comparator;

public class KruskalsMST {

    public static int minimumSpanningTree(ArrayList<ArrayList<Integer>> edges, int n) {
        //Your code goes here
        int[] parent = new int[n];
        int[] rank = new int[n];

        initParent(parent);

        return createMST(edges, parent, rank);
    }

    private static int createMST(ArrayList<ArrayList<Integer>> edges, int[] parent, int[] rank) {

        edges.sort(Comparator.comparingInt(o -> o.get(2)));

        int minCost = 0;
        for (ArrayList<Integer> edge : edges) {
            int n1 = edge.get(0);
            int n2 = edge.get(1);
            int weight = edge.get(2);

            int parentOfN1 = findParent(n1, parent);
            int parentOfN2 = findParent(n2, parent);

            //if both parents are equal then do nothing
            //else merge them
            if (parentOfN1 != parentOfN2) {
                union(n1, n2, parent, rank);
                minCost += weight;
            }
        }

        return minCost;
    }

    //make each node parent of self in the beginning
    private static void initParent(int[] parent) {
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }


    private static int findParent(int node, int[] parent) {
        if (parent[node] == node) {
            return node;
        }
        //path compression
        parent[node] = findParent(parent[node], parent);
        return parent[node];
    }

    private static void union(int u, int v, int[] parent, int[] rank) {

        //find parent first
        int parentU = findParent(u, parent);
        int parentV = findParent(v, parent);

        // then find the rank of the parent
        // if the rank of parent is high
        // then make higher rank node parent of lesser rank node
        // and then increase rank of the node that was made parent
        if (rank[parentU] > rank[parentV]) {
            parent[parentV] = parentU;
            rank[parentU]++;
        } else if (rank[parentU] < rank[parentV]) {
            parent[parentU] = parentV;
            rank[parentV]++;
        } else {
            // in case of rank are equal, then make any one parent of any node
            parent[parentU] = parentV;
            rank[parentV]++;
        }
    }


    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        edges.add(Lists.newArrayList(0, 1, 3));
        edges.add(Lists.newArrayList(0, 3, 5));
        edges.add(Lists.newArrayList(1, 2, 1));
        edges.add(Lists.newArrayList(2, 3, 8));


        System.out.println(minimumSpanningTree(edges, 4)); //9


        ArrayList<ArrayList<Integer>> edges2 = new ArrayList<>();
        edges2.add(Lists.newArrayList(1, 2, 6));
        edges2.add(Lists.newArrayList(2, 3, 2));
        edges2.add(Lists.newArrayList(1, 3, 2));
        edges2.add(Lists.newArrayList(1, 0, 2));


        System.out.println(minimumSpanningTree(edges2, 4)); //6

    }


}
