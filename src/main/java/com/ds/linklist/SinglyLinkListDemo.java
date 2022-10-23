package com.ds.linklist;

public class SinglyLinkListDemo {

    public static void main(String[] args) {
        SinglyLinkList linkList = new SinglyLinkList();

        linkList.add("A");
        linkList.add("1");
        linkList.add("B");
        linkList.add("2");
        linkList.add("C");
        linkList.add("3");

        System.out.println("Data -> " + linkList.toString());
        linkList.reverse();
        System.out.println("Reversed Data -> " + linkList.toString());


        SinglyLinkList linkList2 = new TwoPointerSinglyLinkList();

        linkList2.add("A");
        linkList2.add("1");
        linkList2.add("B");
        linkList2.add("2");
        linkList2.add("C");
        linkList2.add("3");


        System.out.println("Data -> " + linkList2.toString());
        linkList.reverse();
        //System.out.println("Reversed Data -> " + linkList2.toString());
    }


}
