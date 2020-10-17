package ru.job4j.lsp;

import java.time.LocalDate;

/**
 * Класс, реализующий хранилище Магазин.
 * В магазине хранятся продукты, у которых срок годности менее 75%.
 * Если срок годности продукта менее 25%, он будет иметь скидку.
 */
public class Shop extends Store {

    /**
     * Текущая дата для сравнения сроков годности продукта.
     */
    private LocalDate now = LocalDate.now();

    public Shop() {
        super();
    }

    @Override
    public void add(Food food) {
        super.add(food);
    }

    @Override
    public void replace(Food food, Store store) {
        super.replace(food, store);
    }

    @Override
    public boolean isSuitable(Food food) {
        boolean result = false;
        long from = food.getCreateDate().toEpochDay();
        long to = food.getExpireDate().toEpochDay();
        long now = this.now.toEpochDay();
        double percent = (to - from) / 100.0;
        double remainPercents = (to - now) / percent;
        if ((int) remainPercents < 75 && (int) remainPercents > 0) {
            result = true;
            if ((int) remainPercents < 25) {
                food.setDiscount();
            }
        }
        return result;
    }
}