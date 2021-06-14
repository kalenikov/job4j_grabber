package ru.job4j.ood.food;

import ru.job4j.ood.food.model.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtils {
    public static long getCountDaysBetweenDates(LocalDate dateFrom, LocalDate dateTo) {
        return ChronoUnit.DAYS.between(dateFrom, dateTo);
    }

    public static int getPercentRemainToExpiration(Food food, LocalDate now) {
        long expirationDays = DateUtils.getCountDaysBetweenDates(food.getCreateDate(), food.getExpiryDate());
        long remainDays = DateUtils.getCountDaysBetweenDates(now, food.getExpiryDate());
        return (int) (remainDays < 0 ? 0 : (1 - (double) remainDays / expirationDays) * 100);
    }

}

