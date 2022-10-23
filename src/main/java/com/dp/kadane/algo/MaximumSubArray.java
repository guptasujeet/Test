package com.dp.kadane.algo;

//Kadane's algorithm
//https://en.wikipedia.org/wiki/Maximum_subarray_problem
public class MaximumSubArray {


    public int maxSubArray(int[] nums) {

        int sum = 0;
        int max = nums[0]; //at least one element is mentioned in question
        for (int i = 0; i < nums.length; i++) {
            //step 1 add to sum
            sum = sum + nums[i];
            //step 2 update max
            max = Math.max(max, sum);
            //step 3 if sum less than 0 set sum = 0
            if (sum < 0) {
                sum = 0;
            }
        }

        return max;

    }

    public static void main(String[] args) {

        MaximumSubArray maximumSubArray = new MaximumSubArray();

        System.out.println(maximumSubArray.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); //6

    }


}
