package test.q1.hr;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistinctDigitNumber {

    public static void countNumbers(List<List<Integer>> arr) {
        Map<Integer, Boolean> numberRepeatMap = new HashMap<>();
        int noRepeatCount;
        for (List<Integer> numRange : arr) {
            noRepeatCount = 0;
            int start = numRange.get(0);
            int end = numRange.get(1);

            for (int i = start; i <= end; i++) {
                Boolean hasRepeatDigits = numberRepeatMap.get(i);
                if (hasRepeatDigits == null) {
                    hasRepeatDigits = numberHasRepeatDigits(i);
                    if (!hasRepeatDigits) {
                        noRepeatCount++;
                    }
                    numberRepeatMap.put(i, hasRepeatDigits);
                } else if (!hasRepeatDigits) {
                    noRepeatCount++;
                }
            }
            System.out.println(noRepeatCount);
        }

    }

    private static boolean numberHasRepeatDigits(int number) {
        String numStr = String.valueOf(number);
        for (int i = 0; i < numStr.length(); i++) {
            char ch = numStr.charAt(i);
            int index = numStr.indexOf(ch, i + 1);
            if (index > i) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        List<List<Integer>> arr = new ArrayList<>();
        //arr.add(Lists.newArrayList(1, 20)); //19
        //arr.add(Lists.newArrayList(9, 19)); //10
        arr.add(Lists.newArrayList(80, 120)); //27
        countNumbers(arr);
    }

}
