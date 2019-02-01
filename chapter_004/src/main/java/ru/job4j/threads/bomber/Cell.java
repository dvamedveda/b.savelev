package ru.job4j.threads.bomber;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс, реализующий клетку игрового поля для игры бомберман.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Cell {

    /**
     * Координата на поле по оси X.
     */
    private int x;

    /**
     * Координата на поле по оси Y.
     */
    private int y;

    /**
     * Конструктор клетки.
     *
     * @param x координата X.
     * @param y координата Y.
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Сравнение ячеек между собой, равны они (по координатам) или нет.
     *
     * @param obj объект для сравнения.
     * @return результат сравнения.
     */
    @Override
    public boolean equals(Object obj) {
        Cell cell = (Cell) obj;
        boolean result = false;
        if (x == cell.getX() && y == cell.getY()) {
            result = true;
        }
        return result;
    }

    /**
     * Вычисление хеша.
     *
     * @return хеш ячейки.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Метод для проверки, является ли ячейка с заданными координатами - свободной.
     *
     * @param board игровое поле.
     * @param dest  произвольная ячейка.
     * @return клетка свободна или нет.
     */
    public boolean isEmptyCell(ReentrantLock[][] board, Cell dest) {
        boolean result = true;
        if (board[dest.getX()][dest.getY()].isLocked()) {
            result = false;
        }
        return result;
    }
}
