package com.test.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Sujeet on 19/06/17.
 */
public class DateTimeParser {

    public static void main(String[] args) throws ParseException {
        String data = "06/19/2017";
        String format = "MM/dd/yyyy";

        LocalDate localDate = LocalDate.parse(data, DateTimeFormatter.ofPattern(format));
        //LocalDateTime localDateTime  = LocalDateTime.parse(data, DateTimeFormatter.ofPattern(format));
        System.out.println("Local Date Epoch : " + localDate.toEpochDay());
        System.out.println("Epoch Seconds\t : " + localDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000);

        Date date = new SimpleDateFormat(format).parse(data);
        System.out.println("Date Epoch\t\t : " + date.getTime());


    }

}
