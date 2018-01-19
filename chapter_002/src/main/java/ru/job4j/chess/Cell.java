package ru.job4j.chess;

/**
 * Реализация шахматной клетки.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Cell {

    /**
     * Координаты клетки шахматной доски.
     */
    int x, y;

    /**
     * Конструктор клетки.
     *
     * @param x координата X
     * @param y координата Y
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Геттер координаты X
     *
     * @return координата X
     */
    public int getX() {
        return x;
    }

    /**
     * Геттер координаты Y
     *
     * @return координата Y
     */
    public int getY() {
        return y;
    }
}