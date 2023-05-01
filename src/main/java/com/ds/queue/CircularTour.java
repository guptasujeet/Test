package com.ds.queue;

// https://youtu.be/_gJ3to4RyeQ?t=2475

// https://practice.geeksforgeeks.org/problems/circular-tour-1587115620/1
public class CircularTour {

    //shortest approach
    private int getStartingStation(int[] petrol, int[] distance) {

        int startPump = 0;
        int balance = 0;
        int deficit = 0;
        for (int pumpNum = 0; pumpNum < petrol.length; pumpNum++) {
            balance = (petrol[pumpNum] - distance[pumpNum]) + balance;
            if (balance < 0) {
                deficit = balance;
                startPump = pumpNum + 1;
                balance = 0;
            }
        }

        if (deficit + balance >= 0) {
            return startPump;
        }
        return -1;

    }


    public static void main(String[] args) {

        CircularTour tour = new CircularTour();

        int[] petrol1 = new int[]{4, 6, 7, 4};
        int[] distance1 = new int[]{6, 5, 3, 5};

        System.out.println(tour.getStartingStation(petrol1, distance1)); //1


        int[] petrol2 = new int[]{40, 6, 7, 4};
        int[] distance2 = new int[]{6, 5, 3, 5};
        System.out.println(tour.getStartingStation(petrol2, distance2)); //0

        int[] petrol3 = new int[]{4, 3, 70, 4};
        int[] distance3 = new int[]{6, 5, 3, 5};
        System.out.println(tour.getStartingStation(petrol3, distance3)); //2

        int[] petrol4 = new int[]{4, 3, 70, 4};
        int[] distance4 = new int[]{6, 500, 3, 5};
        System.out.println(tour.getStartingStation(petrol4, distance4)); //-1

    }

}
