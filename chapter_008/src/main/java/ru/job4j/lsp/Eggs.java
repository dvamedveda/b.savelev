package ru.job4j.lsp;

import java.time.LocalDate;

/**
 * Класс, реализующий категорию продуктов Яйца.
 */
public class Eggs extends Food {
    public Eggs(String name, LocalDate createDate, LocalDate expireDate, double price, int maxDiscount) {
        super(name, createDate, expireDate, price, maxDiscount);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public LocalDate getCreateDate() {
        return super.getCreateDate();
    }

    @Override
    public LocalDate getExpireDate() {
        return super.getExpireDate();
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public int getDiscount() {
        return super.getDiscount();
    }

    @Override
    public void setDiscount() {
        super.setDiscount();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}