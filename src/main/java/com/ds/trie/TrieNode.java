package com.ds.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {

    Character data;
    boolean isTerminal;
    Map<Character, TrieNode> children;


    public TrieNode(Character data) {
        this.data = data;
        this.children = new HashMap<>();
    }

    @Override
    public String toString() {
        return "TrieNode{" +
                "data=" + data +
                ", isTerminal=" + isTerminal +
                ", children=" + children.keySet() +
                '}';
    }
}
