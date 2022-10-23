package com.ds.stack;

import java.util.LinkedList;

public class FindTheCelebrity {


    private int findCelebrity(int[][] people) {

        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(0);
        for (int nextPeopleNum = 1; nextPeopleNum < people.length; nextPeopleNum++) {

            Integer prevPeopleNum = stack.pop();
            if (people[prevPeopleNum][nextPeopleNum] == 1) {
                stack.push(nextPeopleNum);
            } else {
                stack.push(prevPeopleNum);
            }

        }

        //more than one people
        if (stack.size() > 1) {
            return -1; // no celebrity
        }

        int potentialCelebrity = stack.pop();

        for (int i = 0; i < people.length; i++) {
            if (potentialCelebrity != i && people[potentialCelebrity][i] == 1) {
                return -1;
            }
            if (potentialCelebrity != i && people[i][potentialCelebrity] == 0) {
                return -1;
            }
        }

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
