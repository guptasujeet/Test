package com.dp;

import com.google.common.collect.Lists;

import java.util.ArrayList;

public class StairNumWaysToClimb {


    public static void main(String[] args) {
        int numberOfSteps = 6;
        ArrayList allowedSteps = Lists.newArrayList(0, 1, 2, 4, 5);
        int waysToClimbUp = getNumberOfWaysToClimbUp(numberOfSteps, allowedSteps);

        System.out.println("Ways to climb up -> " + waysToClimbUp);
    }


    private static int getNumberOfWaysToClimbUp(int numberOfSteps, ArrayList allowedSteps) {
        if (numberOfSteps == 0 || numberOfSteps == 1) {
            //climbing from step 0 to step 0
            // taking this 0 as well 1, taking as 1
            return 1;
        }

        int[] waysPerSteps = new int[numberOfSteps + 1];
        waysPerSteps[0] = 1;
        waysPerSteps[1] = 1;

        for (int stepNum = 2; stepNum <= numberOfSteps; stepNum++) {
            int waysForCurrentStep = 0;
            int nextStep1 = stepNum - 1;
            int nextStep2 = stepNum - 2;

            if (allowedSteps.contains(nextStep1)) {
                waysForCurrentStep += waysPerSteps[nextStep1];
            }
            if (allowedSteps.contains(nextStep2)) {
                waysForCurrentStep += waysPerSteps[nextStep2];
            }


            waysPerSteps[stepNum] = waysForCurrentStep;
        }

        return waysPerSteps[numberOfSteps];
    }

}
