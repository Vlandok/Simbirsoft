package com.vlad.lesson4.utils;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.ChronoUnit;

public final class Date {
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
}
