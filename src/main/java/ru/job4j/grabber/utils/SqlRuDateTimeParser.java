package ru.job4j.grabber.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class SqlRuDateTimeParser implements DateTimeParser {

    static final List<String> months = List.of("", "янв", "фев", "мар", "апр", "май", "июн", "июл", "авг", "сен", "окт", "ноя", "дек");

    @Override
    public LocalDateTime parse(String target) {
        String[] dateTimeAr = target.split(", ");
        String date = dateTimeAr[0];
        String time = dateTimeAr[1];
        if ("сегодня".equals(date)) {
            return LocalDateTime.of(LocalDate.now(), LocalTime.parse(time));
        } else if ("вчера".equals(date)) {
            return LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.parse(time));
        } else {
            String[] dmy = date.split("\\s+");
            return LocalDateTime.of(
                    LocalDate.of(
                            Integer.parseInt(dmy[2]) + 2000,
                            months.indexOf(dmy[1]),
                            Integer.parseInt(dmy[0])),
                    LocalTime.parse(time));
        }
    }
}