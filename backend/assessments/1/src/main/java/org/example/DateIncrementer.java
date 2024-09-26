package org.example;

import java.time.LocalDate;

public class DateIncrementer {
    static public String addOneDay(String date) {
        return LocalDate.parse(date).plusDays(1).toString();
    }
}