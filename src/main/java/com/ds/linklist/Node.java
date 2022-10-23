package com.ds.linklist;

public class Node {

    String data;
    Node nextNode;

    public Node(String data) {
        this.data = data;
    }

    public Node(String data, Node nextNode) {
        this.data = data;
        this.nextNode = nextNode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public String toString() {
        return "{" +
                "data='" + data + '\'' +
                ", nextNode=" + nextNode +
                '}';
    }
}
