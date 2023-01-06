package com.problem;

import java.util.*;

public class SkipIterator implements Iterator<Integer> {

    private final Iterator<Integer> it;
    private final Map<Integer, Integer> count;
    private Integer nextEl;

    public SkipIterator(Iterator<Integer> it) {
        this.it = it;
        this.count = new HashMap<>();
        advance();
    }

    @Override
    public boolean hasNext() {
        return nextEl != null;
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new RuntimeException("empty");
        //make local copy of next advance to next and return existing local copy
        Integer el = nextEl;
        advance();
        return el;
    }

    public void skip(int num) {
        if (!hasNext()) throw new RuntimeException("empty");
        //if the element to skip is the next element then advance to next element
        if (nextEl == num) {
            advance();
        } else {
            //else add the count
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
    }

    private void advance() {
        //set next element to null, so that it can be used in next() method check
        nextEl = null;
        while (nextEl == null && it.hasNext()) {
            Integer el = it.next();
            if (!count.containsKey(el)) {
                nextEl = el;
            } else {
                count.put(el, count.get(el) - 1);
                count.remove(el, 0);
            }
        }
    }


    public static void main(String[] args) {
        Integer[] ints = {2, 3, 5, 6, 5, 7, 5, -1, 5, 10};
        List<Integer> list = Arrays.asList(ints);
        SkipIterator itr = new SkipIterator(list.iterator());
        System.out.println(itr.hasNext()); // true
        System.out.println(itr.next()); // returns 2
        itr.skip(5);
        System.out.println(itr.next()); // returns 3
        System.out.println(itr.next()); // returns 6 because 5 should be skipped
        System.out.println(itr.next()); // returns 5
        itr.skip(5);
        itr.skip(5);
        System.out.println(itr.next()); // returns 7
        System.out.println(itr.next()); // returns -1
        System.out.println(itr.next()); // returns 10
        System.out.println(itr.hasNext()); // false
        System.out.println(itr.next());
    }
}

