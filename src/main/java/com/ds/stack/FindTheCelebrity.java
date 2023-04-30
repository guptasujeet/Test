package com.ds.stack;

import java.util.LinkedList;

// https://www.youtube.com/watch?v=9u2BJfmWNEg&t=4s
// https://practice.geeksforgeeks.org/problems/the-celebrity-problem/1
public class FindTheCelebrity {


    private int findCelebrity(int[][] people) {

        LinkedList<Integer> stack = new LinkedList<>();
        //put 1st person in stack
        stack.push(0);
        for (int nextPersonNum = 1; nextPersonNum < people.length; nextPersonNum++) {

            Integer prevPersonNum = stack.pop();
            if (people[prevPersonNum][nextPersonNum] == 1) {
                //if prevPerson knows nextPerson , then prevPerson for sure is not celebrity
                //eliminate previous person
                //nextPerson could be potential celebrity
                stack.push(nextPersonNum);
            } else {
                //vice-versa
                stack.push(prevPersonNum);
            }

        }

        //if more than one people in stack, then there is no celebrity
        if (stack.size() > 1) {
            return -1; // no celebrity
        }

        //else last person is potential celebrity
        int potentialCelebrity = stack.pop();

        // verify potential celebrity for person in rows and colum
        for (int personNum = 0; personNum < people.length; personNum++) {
            //do not check if potential celebrity and personNum is same
            if (potentialCelebrity != personNum) {
                if (people[potentialCelebrity][personNum] == 1) {
                    //if potential celebrity knows any one then he is not celebrity
                    return -1;
                }
                if (people[personNum][potentialCelebrity] == 0) {
                    //if any one doesn't know potential celebrity then he is not celebrity
                    return -1;
                }
            }
        }

        //finally potential celebrity is the actual celebrity
        return potentialCelebrity;
    }


    public static void main(String[] args) {

        FindTheCelebrity findTheCelebrity = new FindTheCelebrity();

        int[][] peopleGroup1 = new int[][]{
                {0, 1, 0},
                {0, 0, 0},
                {0, 1, 0}
        };

        System.out.println(findTheCelebrity.findCelebrity(peopleGroup1)); //1


        int[][] peopleGroup2 = new int[][]{
                {0, 1},
                {1, 0}
        };

        System.out.println(findTheCelebrity.findCelebrity(peopleGroup2)); //-1


    }

}
