package ru.job4j.tracker.ui;

/**
 * Класс, реализующий основные методы пунктов меню.
 */
public abstract class BaseAction implements UserAction {
    /**
     * Тут хранится номер пункта меню.
     */
    private final int key;

    /**
     * Тут хранится название пункта меню.
     */
    private final String name;

    /**
     * Конструктор, принимающий номер и название пункта меню.
     * @param key номер пункта меню.
     * @param name имя пункта меню.
     */
    public BaseAction(int key, String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * Геттер для получения номера пункта меню.
     * @return номер пункта меню.
     */
    public int key() {
        return this.key;
    }

    /**
     * Информация о пункте меню из номера и названия.
     * @return строка для пункта меню.
     */
    public String info() {
        return String.format("%s. %s", this.key(), this.name);
    }
}