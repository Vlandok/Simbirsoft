package com.vlad.lesson4.utils;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.ChronoUnit;

public class Date {
    private final static String DATE_FORMAT = "dd.MM.yyyy";

    public static long getDays(String dateStartCompare, String dateFinishCompare) {
        LocalDate localDate = LocalDate.now();
        LocalDate localDateFinishCompare = LocalDate.parse(dateFinishCompare,
                DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate localDateStartCompare = LocalDate.parse(dateStartCompare,
                DateTimeFormatter.ofPattern(DATE_FORMAT));

        if (localDate.isBefore(localDateFinishCompare) && localDate.isAfter(localDateStartCompare)) {
            return ChronoUnit.DAYS.between(localDate, localDateFinishCompare);
        } else if (localDate.isAfter(localDateFinishCompare)) {
            return -1;
        } else {
            return -2;
        }
    }

    public static String getDayAddition(int num) {
        int preLastDigit = num % 100 / 10;
        if (preLastDigit == 1) {
            return "дней";
        }

        switch (num % 10) {
            case 1:
                return "день";
            case 2:
            case 3:
            case 4:
                return "дня";
            default:
                return "дней";
        }
    }
}
