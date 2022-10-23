package com.ds.linklist;

public class TwoPointerSinglyLinkList extends SinglyLinkList {

    private Node tail;

    @Override
    public void add(String data) {
        Node newNode = new Node(data);
        if (root == null) {
            root = tail = newNode;
        }
        //Node current = tail;
        //current.nextNode = newNode;

        tail.nextNode = newNode;
        tail = newNode;
    }
}
