package test.q1.q2;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

class Result {

    /*
     * Complete the 'miniMaxSum' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */


    public static void miniMaxSum(List<Integer> arr) {
        // Write your code here
        Collections.sort(arr);

        long maxValue = 0;
        long minValue = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (i == 0) {
                minValue += arr.get(i);
            } else if (i == 4) {
                maxValue += arr.get(i);
            } else {
                minValue += arr.get(i);
                maxValue += arr.get(i);
            }
        }

        System.out.print(minValue + " " + maxValue);

    }

}

public class Solution {

    static long getSequenceSum(int i, int j, int k) {

        if (i == j && j == k) {
            return i;
        }
        long sum = 0;

        long seriesSum = Long.MIN_VALUE;
        for (int index = 0; seriesSum < j - 1; index++) {
            seriesSum = i + index;
            sum = sum + seriesSum;
        }

        long seriesDec = Long.MAX_VALUE;
        for (int index = 0; seriesDec > k; index++) {
            seriesDec = j - index;
            sum = sum + seriesDec;
        }

        return sum;
    }


    public static void main3(String[] args) {
        System.out.println();
        //System.out.println(getSequenceSum(0, 5, -1));
        System.out.println(getSequenceSum(-5, -1, -3));
        System.out.println();
        //System.out.println(getSequenceSum(5, 9, 6));
        System.out.println();
    }


    public static List<String> getUsernames(int threshold) {

        List<Map<String, Object>> authorList = new ArrayList<>();

        try {
            ScriptEngineManager engineManager = new ScriptEngineManager();
            ScriptEngine engine = engineManager.getEngineByName("javascript");

            String apiUrl = "https://jsonmock.hackerrank.com/api/article_users?page=";
            String apiContent = getApiContent(apiUrl + "1");//first page
            Map<String, Object> dataMap = parseData(apiContent, engine);

            authorList.addAll(getAuthorList(dataMap));
            long totalPages = (Integer) dataMap.get("total_pages");


            if (totalPages > 1) {
                for (int i = 2; i <= totalPages; i++) {
                    String nextPage = getApiContent(apiUrl + i);
                    Map<String, Object> stringObjectMap = parseData(nextPage, engine);
                    authorList.addAll(getAuthorList(stringObjectMap));
                }
            }
        } catch (Exception e) {
            return Collections.emptyList();
        }

        List<String> filteredAuthors = new ArrayList<>();

        for (Map<String, Object> map : authorList) {
            Integer submissionCount = (Integer) map.get("submission_count");
            if (submissionCount > threshold) {
                filteredAuthors.add((String) map.get("username"));
            }
        }
        return filteredAuthors;
    }

    private static List<Map<String, Object>> getAuthorList(Map<String, Object> dataMap) {
        return (List) dataMap.get("data");
    }

    private static String getApiContent(String apiUrl) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Scanner scanner = null;
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            scanner = new Scanner(urlConnection.getInputStream());
            while (scanner.hasNextLine()) {
                outputStream.write(scanner.nextLine().getBytes());
            }
            return outputStream.toString();
        } catch (Exception e) {
            throw e;
        } finally {
            outputStream.close();
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private static Map<String, Object> parseData(String content, ScriptEngine engine) throws ScriptException {
        String script = "Java.asJSONCompatible(" + content + ")";
        Object result = engine.eval(script);
        return (Map) result;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(getUsernames(10));
    }

    public static void main2(String[] args) throws IOException {
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(1);
        objects.add(3);
        objects.add(5);
        objects.add(7);
        objects.add(9);

        Result.miniMaxSum(objects);

    }
}

