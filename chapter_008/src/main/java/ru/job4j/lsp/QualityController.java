package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, реализующий сортировку продуктов по хранилищам.
 */
public class QualityController {

    /**
     * Список хранилищ.
     */
    private List<Store> storages;

    /**
     * Конструктор сортировщика.
     *
     * @param storages список доступных хранилищ.
     */
    public QualityController(List<Store> storages) {
        this.storages = storages;
    }

    /**
     * Рассортировать список продуктов по хранилищам.
     *
     * @param foods список продуктов.
     */
    public void sort(List<Food> foods) {
        for (Food food : foods) {
            for (Store store : storages) {
                if (store.isSuitable(food)) {
                    store.add(food);
                    break;
                }
            }
        }
    }

    /**
     * Извлечь и перераспределить все продукты из хранилищ.
     */
    public void resort() {
        List<Food> foodList = new ArrayList<>();
        for (Store store : this.storages) {
            foodList.addAll(store.getFoods());
            store.clearStore();
        }
        this.sort(foodList);
    }
}