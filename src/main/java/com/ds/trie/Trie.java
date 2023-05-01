package com.ds.trie;

import org.apache.commons.lang3.StringUtils;

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
        //base case , if no word left, then set is terminal true and return
        if (StringUtils.isEmpty(word)) {
            node.isTerminal = true;
            return;
        }

        Character character = word.charAt(0);
        TrieNode child = node.children.get(character);
        if (child == null) {
            child = new TrieNode(character);
            node.children.put(character, child);
        }
        //insert next character in the string in the child node
        insertWord(child, word.substring(1));
    }


    public boolean search(String word) {
        return searchWord(root, word);
    }

    private boolean searchWord(TrieNode node, String word) {
        //if the world is empty, then check if status is terminal node status
        //whether it is present or not
        if (StringUtils.isEmpty(word)) {
            return node.isTerminal;
        }

        Character character = word.charAt(0);
        TrieNode child = node.children.get(character);
        //if not child node, then return false, as it is not present
        if (child == null) {
            return false;
        }
        //search for next character in the string in the child node
        return searchWord(child, word.substring(1));
    }

    public void delete(String word) {
        deleteWord(root, word);
    }

    private boolean deleteWord(TrieNode node, String word) {
        if (StringUtils.isEmpty(word)) {
            //if node is terminal then, mark deleted as true, because that means it was present
            //and set the node status as not terminal, as other node, will be there down in the hierarchy
            if (node.isTerminal) {
                node.isTerminal = false;
                return true;
            }
            //if it is not terminal then return false, becasue tht means it was not present
            return false;
        }

        Character character = word.charAt(0);
        TrieNode child = node.children.get(character);
        if (child == null) {
            return false;
        }
        //delete from child node for next character
        boolean deleted = deleteWord(child, word.substring(1));

        //if it is successfully deleted, and it was the only node present, then reclaim that space
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
