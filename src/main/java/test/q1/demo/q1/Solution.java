package test.q1.demo.q1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Result {

    /*
     * Complete the 'minimalHeaviestSetA' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static List<Integer> minimalHeaviestSetA(List<Integer> arr) {
        // Write your code here
        Collections.sort(arr);
        int size = arr.size();


        long sumSetA = 0;
        long sumSetB = 0;
        long[] sumSetAEachStep = new long[size];
        long[] sumSetBEachStep = new long[size];
        for (int i = (size - 1), j = 0; i >= 0 && j < size; i--, j++) {
            Integer aNum = arr.get(i);
            Integer bNum = arr.get(j);
            sumSetA = sumSetA + aNum;
            sumSetB = sumSetB + bNum;
            sumSetAEachStep[j] = sumSetA;
            sumSetBEachStep[j] = sumSetB;

        }
        int breakPoint = -1;
        for (int i = (size - 1), j = 0; i >= 0 && j < size; i--, j++) {
            if (sumSetAEachStep[j] > sumSetBEachStep[i]) {
                breakPoint = i;
                break;
            }
        }
        List<Integer> retValue = new ArrayList<>();
        for (int i = breakPoint + 1; i < size; i++) {
            retValue.add(arr.get(i));
        }
        return retValue;

    }

}

public class Solution {

    public static void main(String[] args) {
        List<Integer> arrList = new ArrayList<>();
        arrList.add(4);
        arrList.add(2);
        arrList.add(5);
        arrList.add(1);
        arrList.add(6);
        System.out.println(Result.minimalHeaviestSetA(arrList));
    }

    public static void main2(String[] args) {

        List<Integer> arrList = new ArrayList<>();
        arrList.add(5);
        arrList.add(3);
        arrList.add(2);
        arrList.add(4);
        arrList.add(1);
        arrList.add(2);
        System.out.println(Result.minimalHeaviestSetA(arrList));
    }
}
