package ru.job4j.lsp;

import java.time.LocalDate;

/**
 * Класс, реализующий хранилище Мусорка.
 * В мусорку попадают продукты, у которых срок годности истек.
 */
public class Trash extends Store {

    private LocalDate now = LocalDate.now();

    public Trash() {
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
        long from = food.getCreateDate().toEpochDay();
        long to = food.getExpireDate().toEpochDay();
        long now = this.now.toEpochDay();
        double percent = (to - from) / 100.0;
        double remainPercents = (to - now) / percent;
        return (int) remainPercents < 0;
    }
}