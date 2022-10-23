package com.ds.graph.problem;


import com.google.common.collect.Lists;

import java.util.*;

//https://www.interviewbit.com/problems/word-ladder-ii/
//https://www.youtube.com/watch?v=mIZJIuMpI2M
public class WordLetterII {
    public int solve(String begin, String end, ArrayList<String> dict) {


        Set<String> words = new HashSet<>();
        boolean endWordPresent = false;
        for (String word : dict) {
            if (word.equals(end)) {
                endWordPresent = true;
            }
            words.add(word);
        }

       /* if (!endWordPresent) {
            return 0;
        }*/

        Queue<String> queue = new LinkedList<String>();
        queue.add(begin);

        return shortestPath(queue, end, words);
    }

    private int shortestPath(Queue<String> queue, String end, Set<String> words) {

        int depth = 0;
        while (!queue.isEmpty()) {
            depth = depth + 1;
            int levelSize = queue.size();
            while (levelSize > 0) {
                String currWord = queue.poll();
                StringBuilder wordBuilder = new StringBuilder(currWord);
                for (int j = 0; j < currWord.length(); j++) {
                    char origChar = wordBuilder.charAt(j);
                    for (char c = 'a'; c <= 'z'; c++) {
                        wordBuilder.setCharAt(j, c);
                        String newWord = wordBuilder.toString();
                        if (newWord.equals(currWord)) {
                            continue;
                        }
                        if (newWord.equals(end)) {
                            return depth + 1;
                        }
                        if (words.contains(newWord)) {
                            queue.add(newWord);
                            words.remove(newWord);
                        }
                    }
                    //reset char
                    wordBuilder.setCharAt(j, origChar);
                }

                levelSize--;
            }
        }

        return depth + 1;
    }

    public static void main(String[] args) {
        WordLetterII wordLetterI = new WordLetterII();


        ArrayList<String> dict = Lists.newArrayList("hot", "dot", "dog", "lot", "log");

        System.out.println(wordLetterI.solve("hit", "cog", dict));
    }


}

