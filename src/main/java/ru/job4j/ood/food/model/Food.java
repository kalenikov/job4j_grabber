package ru.job4j.ood.food.model;

import java.time.LocalDate;

public class Food extends AbstractNamedItem {
    public Food(String name, LocalDate createDate, LocalDate expiryDate, double price) {
        super(name, createDate, expiryDate, price);
    }
}
