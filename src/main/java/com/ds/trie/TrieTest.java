package com.ds.trie;

public class TrieTest {


    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("time");
        trie.insert("timer");
        trie.insert("tiger");


        System.out.println("Insert Done");

        System.out.println("Searching " + trie.search("test"));
        System.out.println("Searching " + trie.search("time"));
        System.out.println("Searching " + trie.search("tim"));
        System.out.println("Searching " + trie.search("tiger"));

        System.out.println("Deleting : ");
        trie.delete("tiger");

        System.out.println("Searching " + trie.search("tiger"));
        System.out.println("Searching " + trie.search("time"));

        trie.delete("tim");
        trie.delete("time");

        System.out.println("Searching " + trie.search("time"));
        System.out.println("Searching " + trie.search("tim"));


        trie.insert("high");
        trie.insert("higher");
        trie.insert("highest");
        trie.insert("height");
        trie.insert("hifen");
        trie.insert("hifi");
        trie.insert("helicopter");


        System.out.println(trie.suggestWords("he", 7));
    }
}
