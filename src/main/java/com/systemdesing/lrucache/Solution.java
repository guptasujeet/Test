package com.systemdesing.lrucache;

import java.util.LinkedHashMap;
import java.util.Map;

public class Solution {

    private final int capacity;
    private Map<Integer, Integer> cache;

    public Solution(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<Integer, Integer>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return this.size() == capacity + 1;
            }
        };
    }

    public int get(int key) {
        Integer value = cache.remove(key);
        if (value == null) {
            return -1;
        } else {
            cache.put(key, value);
            return value;
        }
    }

    public void set(int key, int value) {
        cache.remove(key);
        cache.put(key, value);
    }


    public static void main(String[] args) {
        Solution solution = new Solution(1);

        solution.set(2, 1);
        solution.set(2, 2);
        System.out.println(solution.get(2));
        solution.set(1, 1);
        solution.set(4, 1);
        System.out.println(solution.get(2));
    }
}
