package ru.job4j.strategy;

/**
 * Интерфейс описывающий способность фигур быть нарисованными.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public interface Shape {

    /**
     * Метод, который рисует фигуру.
     *
     * @return нарисованная фигура.
     */
    String draw();
}