package ru.job4j.grabber.utils;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class SqlRuDateTimeParserTest {

    @Test
    public void whenParse() {
        SqlRuDateTimeParser parser = new SqlRuDateTimeParser();
        assertEquals(LocalDateTime.of(2021, 5, 25, 8, 28), parser.parse("25 май 21, 08:28"));
        assertEquals(LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(8, 28)), parser.parse("вчера, 08:28"));
        assertEquals(LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 28)), parser.parse("сегодня, 08:28"));
    }

}