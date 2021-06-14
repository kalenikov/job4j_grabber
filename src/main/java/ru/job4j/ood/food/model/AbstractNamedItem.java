package ru.job4j.ood.food.model;

import java.time.LocalDate;

public abstract class AbstractNamedItem {
    private final String name;
    private final LocalDate expiryDate;
    private final LocalDate createDate;
    private double price;
    private double discount;

    public AbstractNamedItem(String name, LocalDate createDate, LocalDate expiryDate, double price) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return name + " (" + createDate + "--" + expiryDate + ")";
    }
}
