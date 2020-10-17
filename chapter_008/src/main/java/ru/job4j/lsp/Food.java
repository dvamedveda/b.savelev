package ru.job4j.lsp;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Класс-абстракция некоего продукта.
 */
public abstract class Food {

    /**
     * Название продукта.
     */
    private String name;

    /**
     * Срок выпуска продукта.
     */
    private LocalDate createDate;

    /**
     * Срок годности продукта.
     */
    private LocalDate expireDate;

    /**
     * Цена продукта.
     */
    private double price;

    /**
     * Действующая скидка на продукт.
     */
    private int discount = 0;

    /**
     * Возможная скидка на продукт.
     */
    private int maxDiscount;

    /**
     * Признак залежавшегося товаря.
     */
    private boolean needDiscount = false;

    /**
     * Конструктор продукта.
     *
     * @param name        название продукта.
     * @param createDate  срок выпуска продукта.
     * @param expireDate  срок годности продукта.
     * @param price       цена продукта.
     * @param maxDiscount возможная скидка на продукт.
     */
    public Food(String name, LocalDate createDate, LocalDate expireDate, double price, int maxDiscount) {
        this.name = name;
        this.createDate = createDate;
        this.expireDate = expireDate;
        this.price = price;
        this.maxDiscount = maxDiscount;
    }

    /**
     * Получить имя продукта.
     *
     * @return имя продукта.
     */
    public String getName() {
        return name;
    }

    /**
     * Получить срок выпуска продукта.
     *
     * @return срок выпуска
     */
    public LocalDate getCreateDate() {
        return createDate;
    }

    /**
     * Получить срок годности продукта.
     *
     * @return срок годности
     */
    public LocalDate getExpireDate() {
        return expireDate;
    }

    /**
     * Получить текущую цену на продукт с учетом скидки.
     *
     * @return цена с учетом скидки.
     */
    public double getPrice() {
        return (price / 100) * (100 - discount);
    }

    /**
     * Получить текущую скидку на продукт.
     *
     * @return действующая на продукт скидка
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * Установить скидку на продукт.
     */
    public void setDiscount() {
        this.discount = maxDiscount;
    }

    /**
     * Метод для сравнения продукта с объектом.
     *
     * @param o объект для сравнения.
     * @return результат сравнения.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return createDate == food.createDate
                && expireDate == food.expireDate
                && Objects.equals(name, food.name);
    }

    /**
     * Хешкод продукта, вычисляется по названию, дате выпуска, дате годности.
     *
     * @return хешкод продукта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, createDate, expireDate);
    }
}