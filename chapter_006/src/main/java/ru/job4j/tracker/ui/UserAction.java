package ru.job4j.tracker.ui;

import ru.job4j.tracker.tracker.SqlTracker;

/**
 * Интерфейс, описывающий действия пользователя в меню.
 * Содержит общие для всех пунктов меню методы.
 */
public interface UserAction {

    /**
     * Возвращает номер действия.
     *
     * @return номер действия.
     */
    int key();

    /**
     * Выполнение действия.
     *
     * @param input      объект ввода
     * @param sqlTracker объект трекера
     */
    void execute(Input input, SqlTracker sqlTracker);

    /**
     * Описание действия для меню.
     *
     * @return описение действия.
     */
    String info();
}