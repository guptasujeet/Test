package com.datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Created by Sujeet on 21/10/18.
 */
public class InstantToLocalDate {

    public static void main(String[] args) {
        Instant instant1 = Instant.now();
        System.out.println("Instant 1 : " + instant1);

        LocalDate localDate1 = instant1.atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println("LocalDate1 : " + localDate1);

        LocalDate localDate2 = instant1.atZone(ZoneId.of("Etc/GMT+12")).toLocalDate();
        System.out.println("LocalDate2 : " + localDate2);

        LocalDate localDate3 = instant1.atZone(ZoneId.of("Pacific/Pago_Pago")).toLocalDate();
        System.out.println("LocalDate3 : " + localDate3);

        LocalDate localDate4 = instant1.atZone(ZoneId.of("Etc/GMT-12")).toLocalDate();
        System.out.println("LocalDate4 : " + localDate4);

        LocalDate localDate5 = instant1.atZone(ZoneId.of("Pacific/Apia")).toLocalDate();
        System.out.println("LocalDate5 : " + localDate5);

    }

}
