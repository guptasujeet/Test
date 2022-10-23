package test.q1.q3;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Result {

    /*
     * Complete the 'timeConversion' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    private static DateTimeFormatter dateTimeFormatter12 = DateTimeFormatter.ofPattern("hh:mm:ssa");
    private static DateTimeFormatter dateTimeFormatter24 = DateTimeFormatter.ofPattern("HH:mm:ss");


    public static String timeConversion(String s) {
        LocalTime localTime = LocalTime.parse(s, dateTimeFormatter12);
        return dateTimeFormatter24.format(localTime);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        System.out.println(Result.timeConversion("07:05:45PM"));
    }
}
