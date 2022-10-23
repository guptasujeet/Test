package test.q1.q4;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

class Result {

    /*
     * Complete the 'matchingStrings' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. STRING_ARRAY strings
     *  2. STRING_ARRAY queries
     */

    public static List<Integer> matchingStrings(List<String> strings, List<String> queries) {

        Map<String, List<String>> stringListMap = strings.stream().collect(groupingBy(s -> s));

        List<Integer> retValue = new ArrayList<>(queries.size());

        for (String query : queries) {
            List<String> list = stringListMap.get(query);
            retValue.add(list == null ? 0 : list.size());
        }
        return retValue;

    }

}

public class Solution {
    public static void main(String[] args) {

    }
}
