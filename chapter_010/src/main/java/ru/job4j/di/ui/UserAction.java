package ru.job4j.di.ui;

import ru.job4j.di.tracker.Tracker;

/**
 * Интерфейс, описывающий действия пользователя в меню.
 * Содержит общие для всех пунктов меню методы.
 */
public interface UserAction {

    /**
     * Возвращает номер действия.
     * @return номер действия.
     */
    int key();

    /**
     * Выполнение действия.
     * @param input объект ввода
     * @param tracker объект трекера
     */
    void execute(Input input, Tracker tracker);

    /**
     * Описание действия для меню.
     * @return описение действия.
     */
    String info();
}