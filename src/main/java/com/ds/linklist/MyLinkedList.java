package com.ds.linklist;

// https://leetcode.com/problems/design-linked-list/
public class MyLinkedList {

    static class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
        }
    }

    int size;
    ListNode dummyHead;

    public MyLinkedList() {
        dummyHead = new ListNode(-1);
        size = 0;
    }

    public int get(int index) {
        if (index >= size) {
            return -1;
        }

        ListNode cur = dummyHead;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur.data;
    }

    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        node.next = dummyHead.next;
        dummyHead.next = node;
        size++;
    }

    public void addAtTail(int val) {
        ListNode node = new ListNode(val);
        ListNode cur = dummyHead;
        for (int i = 0; i < size; i++) {
            cur = cur.next;
        }
        cur.next = node;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        ListNode node = new ListNode(val);
        ListNode cur = dummyHead;

        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        node.next = cur.next;
        cur.next = node;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index >= size) {
            return;
        }
        ListNode cur = dummyHead;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        size--;
    }

    @Override
    public String toString() {
        StringBuilder printData = new StringBuilder();
        ListNode current = dummyHead.next;
        while (current != null) {
            printData.append(current.data).append(" -> ");
            current = current.next;
        }
        printData.append("null");
        return printData.toString();
    }


    public static void main5(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        System.out.println(myLinkedList);
        myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
        System.out.println(myLinkedList);
        System.out.println(myLinkedList.get(1));              // return 2
        System.out.println(myLinkedList);
        myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
        System.out.println(myLinkedList);
        System.out.println(myLinkedList.get(1));              // return 3
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(7);
        myLinkedList.addAtHead(2);
        myLinkedList.addAtHead(1);

        System.out.println(myLinkedList);
        myLinkedList.addAtIndex(3, 0);
        System.out.println(myLinkedList);
        myLinkedList.deleteAtIndex(2);
        System.out.println(myLinkedList);
        myLinkedList.addAtHead(6);
        System.out.println(myLinkedList);
        myLinkedList.addAtTail(4);
        System.out.println(myLinkedList);
        System.out.println(myLinkedList.get(4));
        myLinkedList.addAtHead(4);
        myLinkedList.addAtIndex(5, 0);
        myLinkedList.addAtHead(6);
        System.out.println(myLinkedList);
    }

    public static void main4(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1, 2);
        System.out.println(myLinkedList);
        System.out.println(myLinkedList.get(1));
        myLinkedList.deleteAtIndex(0);
        System.out.println(myLinkedList);
        System.out.println(myLinkedList.get(1));
    }

    public static void main6(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtIndex(0, 10);
        System.out.println(myLinkedList);
        myLinkedList.addAtIndex(0, 20);
        System.out.println(myLinkedList);
        myLinkedList.addAtIndex(1, 30);
        System.out.println(myLinkedList);
        System.out.println(myLinkedList.get(0));
    }

}
