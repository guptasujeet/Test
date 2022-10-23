package com.ds.graph.undirected;

import java.util.*;


public class UndirectedGraph<T> {

    private final Map<T, Collection<T>> adjVertices = new HashMap<>();

    public Collection<T> getAdjVertices(T label) {
        return adjVertices.get(label);
    }


    public void addVertex(T label) {
        adjVertices.putIfAbsent(label, new ArrayList<>());
    }


    public void addEdge(T v1, T v2) {
        if (!adjVertices.containsKey(v1)) {
            addVertex(v1);
        }
        adjVertices.get(v1).add(v2);

        if (!adjVertices.containsKey(v2)) {
            addVertex(v2);
        }
        adjVertices.get(v2).add(v1);
    }

    public void removeVertex(T label) {
        Collection<T> neighbours = adjVertices.remove(label);

        for (T neighbour : neighbours) {
            Collection<T> vertices = adjVertices.get(neighbour);
            if (vertices != null) {
                vertices.remove(label);
            }
        }
    }

    public void removeEdge(T label1, T label2) {
        Collection<T> vertices1 = adjVertices.get(label1);

        if (vertices1 != null) {
            vertices1.remove(label2);
        }
        Collection<T> vertices2 = adjVertices.get(label2);
        if (vertices2 != null) {
            vertices2.remove(label1);
        }
    }

    public void printAdjList() {
        for (Map.Entry<T, Collection<T>> entry : adjVertices.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public Queue<T> getBFSTraversal(T rootLabel) {
        Queue<T> toVisit = new LinkedList<>();
        Queue<T> visited = new LinkedList<>();
        toVisit.add(rootLabel);
        while (!toVisit.isEmpty()) {
            T vertex = toVisit.remove();
            if (!visited.contains(vertex)) {
                Collection<T> vertices = adjVertices.get(vertex);
                visited.add(vertex);
                toVisit.addAll(vertices);
            }
        }
        return visited;
    }

    //not correct for DFS
    public Set<T> getDFSTraversal(T rootLabel) {
        Set<T> visited = new LinkedHashSet<>();
        Stack<T> stack = new Stack<>();
        stack.push(rootLabel);
        while (!stack.isEmpty()) {
            T vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                for (T v : getAdjVertices(vertex)) {
                    stack.push(v);
                }
            }
        }
        return visited;
    }


    public Set<T> getDFSTraversalRec(T rootLabel) {
        Set<T> visited = new LinkedHashSet<>();
        getDFSTraversalRec(rootLabel, visited);
        return visited;
    }


    private void getDFSTraversalRec(T vertex, Set<T> visited) {
        visited.add(vertex);
        for (T v : getAdjVertices(vertex)) {
            if (!visited.contains(v)) {
                getDFSTraversalRec(v, visited);
            }
        }
    }

    public Map<T, Collection<T>> getAdjVertices() {
        return adjVertices;
    }
}