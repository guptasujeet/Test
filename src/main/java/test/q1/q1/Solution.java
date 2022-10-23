package test.q1.q1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Result {


    class StringsCollection {

        int bucketSize = 10;

        private Map<Integer, List<String>> strings = new ConcurrentHashMap<>();

        public void addString(String string) {
            int bucket = string.hashCode() % bucketSize;
            strings.compute(bucket, (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }
                v.add(string);
                return v;
            });
        }

        public List<String> getStringsCollection() {
            Iterator<List<String>> iterator = strings.values().iterator();
            List<String> snapshot = new ArrayList<>();
            while (iterator.hasNext()) {
                snapshot.addAll(iterator.next());
            }
            return snapshot;
        }

    }

    /*
     * Complete the 'plusMinus' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void plusMinus(List<Integer> arr) {
        // Write your code here
        if (arr == null || arr.size() == 0) {
            System.out.printf("%6f\n", 0f);
            System.out.printf("%6f\n", 0f);
            System.out.printf("%6f\n", 0f);
            return;
        }

        int size = arr.size();
        double positiveCount = 0;
        double zeroCount = 0;
        double negativeCount = 0;

        for (Integer integer : arr) {
            if (integer > 0) {
                positiveCount++;
            } else if (integer < 0) {
                negativeCount++;
            } else {
                zeroCount++;
            }
        }

        System.out.printf("%6f\n", positiveCount / size);
        System.out.printf("%6f\n", negativeCount / size);
        System.out.printf("%6f\n", zeroCount / size);
    }

}

public class Solution {
    public static void main2(String[] args) throws IOException {
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(1);
        objects.add(1);
        objects.add(0);
        objects.add(-1);
        objects.add(-1);


        Result.plusMinus(objects);


    }


}
