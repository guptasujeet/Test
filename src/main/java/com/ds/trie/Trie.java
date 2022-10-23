package com.ds.trie;

import java.util.ArrayList;
import java.util.List;

public class Trie {

    TrieNode root = new TrieNode('\0');

    public Trie() {

    }

    public void insert(String word) {
        insertWord(root, word);
    }

    private void insertWord(TrieNode node, String word) {

        if (word.length() == 0) {
            node.isTerminal = true;
            return;
        }

        Character character = word.charAt(0);
        TrieNode child = node.children.get(character);
        if (child == null) {
            child = new TrieNode(character);
            node.children.put(character, child);
        }

        insertWord(child, word.substring(1));
    }


    public boolean search(String word) {
        return searchWord(root, word);
    }

    private boolean searchWord(TrieNode node, String word) {
        if (word.length() == 0) {
            return node.isTerminal;
        }

        Character character = word.charAt(0);
        TrieNode child = node.children.get(character);
        if (child == null) {
            return false;
        }
        return searchWord(child, word.substring(1));
    }

    public void delete(String word) {
        deleteWord(root, word);
    }

    private boolean deleteWord(TrieNode node, String word) {
        if (word.length() == 0) {
            if (node.isTerminal) {
                node.isTerminal = false;
                return true;
            }
            return false;
        }

        Character character = word.charAt(0);
        TrieNode child = node.children.get(character);
        if (child == null) {
            return false;
        }
        boolean deleted = deleteWord(child, word.substring(1));

        if (deleted && node.children.size() == 1) {
            node.children.remove(character);
            return true;
        }

        return false;
    }

    public List<String> suggestWords(String prefix, int retWordCount) {
        List<String> recommendation = new ArrayList<>();
        TrieNode prefixNode = getPrefixNode(prefix);
        computeSuggestedWords(prefixNode, retWordCount, recommendation, prefix);

        return recommendation;

    }

    private void computeSuggestedWords(TrieNode node, int retWordCount,
                                       List<String> recommendation, String currentWord) {

        if (node.isTerminal) {
            recommendation.add(currentWord);
            return;
        }

        if (recommendation.size() >= retWordCount) {
            return;
        }

        for (TrieNode child : node.children.values()) {
            computeSuggestedWords(child, retWordCount, recommendation, currentWord + child.data);
        }

    }


    private TrieNode getPrefixNode(String prefix) {
        TrieNode node = root;
        int i = 0;
        while (node != null && i < prefix.length()) {
            Character character = prefix.charAt(i++);
            node = node.children.get(character);
        }

        return node;
    }

}
