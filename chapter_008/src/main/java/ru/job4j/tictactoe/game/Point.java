package ru.job4j.tictactoe.game;

/**
 * Интерфейс, описывающий координаты поля.
 */
public interface Point {

    /**
     * Получить координату X из текущих координат.
     *
     * @return координата X.
     */
    int getX();

    /**
     * Получить координату Y из текущий координат.
     *
     * @return координата Y.
     */
    int getY();
}