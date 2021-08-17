package ru.job4j.di;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для хранения данных.
 */
public class Store {

    /**
     * Хранилище данных
     */
    private List<String> data = new ArrayList<>();

    /**
     * Добавление строки в хранилище.
     *
     * @param value добавляемая строка.
     */
    public void add(String value) {
        data.add(value);
    }

    /**
     * Получить все строки из хранилища.
     *
     * @return содержимое хранилища.
     */
    public List<String> getAll() {
        return data;
    }
}