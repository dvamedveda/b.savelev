package ru.job4j.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Основной класс для работы программы.
 */
@Component
@Scope("prototype")
public class StartUI {

    /**
     * Хранилище данных.
     */
    @Autowired
    private Store store;

    /**
     * Класс для получения данных.
     */
    @Autowired
    private ConsoleInput input;

    /**
     * Получить от пользователя строку и добавить в хранилище.
     */
    public void askAndAdd() {
        String value = input.ask();
        store.add(value);
        System.out.println("String added: " + value);
    }

    /**
     * Напечатать все хранящиеся строки.
     */
    public void print() {
        System.out.println("All added strings:");
        for (String value : store.getAll()) {
            System.out.println(value);
        }
    }
}