package ru.job4j.di;

/**
 * Основной класс для работы программы.
 */
public class StartUI {

    /**
     * Хранилище данных.
     */
    private Store store;

    /**
     * Класс для получения данных.
     */
    private ConsoleInput input;

    /**
     * Конструктор главного класса.
     *
     * @param store хранилище данных.
     * @param input класс ввода данных.
     */
    public StartUI(Store store, ConsoleInput input) {
        this.store = store;
        this.input = input;
    }

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