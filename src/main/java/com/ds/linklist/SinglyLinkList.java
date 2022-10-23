package com.ds.linklist;

public class SinglyLinkList {

    protected Node root;

    public Node getRoot() {
        return root;
    }

    public SinglyLinkList() {
    }

    public SinglyLinkList(Node root) {
        this.root = root;
    }

    public void add(String data) {
        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            while (current.nextNode != null) {
                current = current.nextNode;
            }
            current.setNextNode(newNode);
        }
    }

    public void reverse() {
        Node current = root;
        Node prev = null, next = null;
        while (current != null) {
            next = current.nextNode;
            current.nextNode = prev;
            prev = current;
            current = next;
        }
        root = prev;
    }

    public void reverseAgain() {
        Node current = root;
        Node prev = null;
        Node next = null;
        while (current != null) {
            next = current.nextNode;
            current.nextNode = prev;

            prev = current;
            current = next;

        }
        root = prev;
    }


    @Override
    public String toString() {
        StringBuilder printData = new StringBuilder();
        Node current = root;
        while (current != null) {
            printData.append(current.data).append(" -> ");
            current = current.nextNode;
        }
        printData.append("null");
        return printData.toString();
    }
}
