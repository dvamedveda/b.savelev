package ru.job4j.threads.bomber;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс, реализующий некое существо для игры бомберман.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
class Creature {

    /**
     * Игровое поле.
     */
    ReentrantLock[][] board;

    /**
     * Позиция существа.
     */
    Cell position;

    /**
     * Метод для передвижения существа с исходной ячейки в указанную.
     *
     * @param source исходная клетка.
     * @param dest   конечная клетка.
     * @return удалось перейти на клетку или нет.
     * @throws InterruptedException в случае, если поток прерван во время получения блокировки на ячейку.
     */
    public boolean move(Cell source, Cell dest) throws InterruptedException {
        boolean result = true;
        if (board[dest.getX()][dest.getY()].tryLock(500, TimeUnit.MILLISECONDS)) {
            board[source.getX()][source.getY()].unlock();
            position.setX(dest.getX());
            position.setY(dest.getY());
        } else {
            result = false;
        }
        return result;
    }

    /**
     * Метод для получения текущей позиции.
     *
     * @return текущая позиция.
     */
    public Cell getPosition() {
        return position;
    }

    /**
     * Метод для получения случайной следующей клетки, для передвижения.
     *
     * @return следующая клетка для движения.
     */
    public Cell next() {
        int x = position.getX();
        int y = position.getY();
        Cell result;
        do {
            int random = (int) (Math.random() * 100);
            if (random < 25) {
                result = new Cell(x + 1, y);
            } else if (random < 50) {
                result = new Cell(x, y + 1);
            } else if (random < 75) {
                result = new Cell(x - 1, y);
            } else if (random < 100) {
                result = new Cell(x, y - 1);
            } else {
                result = new Cell(x, y);
            }
        } while (!moveIsValid(result));
        return result;
    }

    /**
     * Метод для проверки, находится ли клетка в игровом поле.
     * Используется при передвижении.
     *
     * @param cell проверяемая клетка.
     * @return результат проверки.
     */
    public boolean moveIsValid(Cell cell) {
        return (cell.getX() >= 0 && cell.getX() < board.length
                && cell.getY() >= 0 && cell.getY() < board.length);
    }
}
