package com.problem;

import java.util.*;

/*
    Also known as Jump Iterator
 */
public class SkipIterator implements Iterator<Integer> {

    private final Iterator<Integer> it;
    private final Map<Integer, Integer> skipMapCounter;
    private Integer nextEl;

    public SkipIterator(Iterator<Integer> it) {
        this.it = it;
        this.skipMapCounter = new HashMap<>();
        //advance in at the time of construction
        // so that we know in advance the nextElement
        advance();
    }

    @Override
    public boolean hasNext() {
        return nextEl != null;
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new RuntimeException("empty");
        //make local copy of next element
        Integer el = nextEl;
        //advance to find next element
        advance();
        //return local copy
        return el;
    }

    public void skip(int num) {
        if (!hasNext()) throw new RuntimeException("empty");
        //if the element is same as element to skip, then advance to find next element
        if (nextEl == num) {
            advance();
        } else {
            //else add the count
            skipMapCounter.put(num, skipMapCounter.getOrDefault(num, 0) + 1);
        }
    }

    private void advance() {
        //set next element to null, so that it can be used in next() method check
        nextEl = null;
        //if next element is null and internal iterator has element
        while (nextEl == null && it.hasNext()) {
            Integer el = it.next();
            if (skipMapCounter.containsKey(el)) {
                //reduce the counter
                skipMapCounter.put(el, skipMapCounter.get(el) - 1);
                //if the counter is 0 then remove the entry from map
                skipMapCounter.remove(el, 0);
            } else {
                //else next element is the next element of the internal iterator
                nextEl = el;
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

