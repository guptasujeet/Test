package com.ds.heap;

import com.ds.linklist.Node;
import com.ds.linklist.SinglyLinkList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLinkedList {


    private static Node mergeKSortedLinkedList(Node[] linkLists, int k) {

        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> Integer.parseInt(o.getData())));

        minHeap.addAll(Arrays.asList(linkLists));

        Node root = null;
        Node tail = null;

        while (!minHeap.isEmpty()) {
            Node newNode = minHeap.poll();
            if (root == null) {
                root = tail = newNode;
            } else {
                //Node current = tail;
                tail.setNextNode(newNode);
                tail = newNode;
            }
            if (newNode.getNextNode() != null) {
                minHeap.add(newNode.getNextNode());
            }
        }
        return root;
    }


    public static void main(String[] args) {
        Node[] sortedLinkedList = getSortedLinkedList();
        Node root = mergeKSortedLinkedList(sortedLinkedList, sortedLinkedList.length);
        System.out.println(new SinglyLinkList(root));
    }

    private static Node[] getSortedLinkedList() {

        Node[] nodes = new Node[3];

        SinglyLinkList list1 = new SinglyLinkList();
        list1.add("5");
        list1.add("6");
        list1.add("7");
        list1.add("8");


        SinglyLinkList list2 = new SinglyLinkList();
        list2.add("9");
        list2.add("12");
        list2.add("13");
        list2.add("17");


        SinglyLinkList list3 = new SinglyLinkList();
        list3.add("1");
        list3.add("2");
        list3.add("3");
        list3.add("4");

        nodes[0] = list1.getRoot();
        nodes[1] = list2.getRoot();
        nodes[2] = list3.getRoot();

        return nodes;
    }


}
