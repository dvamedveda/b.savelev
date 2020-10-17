package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс-абстракция для хранилища продуктов.
 */
public abstract class Store {

    /**
     * Список хранящихся в хранилище продуктов.
     */
    private List<Food> foods = new ArrayList<>();

    /**
     * Получить список продуктов из хранилища.
     *
     * @return список хранящихся продуктов.
     */
    public List<Food> getFoods() {
        return this.foods;
    }

    /**
     * Добавить продукт в хранилище.
     *
     * @param food добавляемый продукт.
     */
    public void add(Food food) {
        this.foods.add(food);
    }

    /**
     * Перенести продукт в другое хранилище.
     *
     * @param food  переносящийся продукт.
     * @param store хранилище для переноса.
     */
    public void replace(Food food, Store store) {
        for (Food item : foods) {
            if (item.equals(food)) {
                Food replacing = item;
                foods.remove(item);
                store.add(replacing);
                break;
            }
        }
    }

    /**
     * Проверка, подходит ли продукт для хранения в данном хранилище.
     *
     * @param food продукт для хранения.
     * @return подходит ли хранилище.
     */
    public abstract boolean isSuitable(Food food);
}