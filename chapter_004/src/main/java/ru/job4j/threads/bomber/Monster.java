package ru.job4j.threads.bomber;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс, реализующий существо "монстр" для игры бомберман.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Monster extends Creature implements Runnable {

    /**
     * Порядковый номер монстра.
     */
    private int number;

    /**
     * Конструктор монстра.
     *
     * @param board    игровое поле.
     * @param position позиция монстра.
     * @param number   порядковый номер монстра.
     */
    public Monster(ReentrantLock[][] board, Cell position, int number) {
        this.board = board;
        this.position = position;
        this.number = number;
    }

    /**
     * Задача для потока монстра.
     * Монстр постоянно передвигается в случайном направлении на одну клетку раз в 1 секунду.
     */
    public void run() {
        Thread.currentThread().setName(String.format("Monster-%d", number));
        int x = position.getX();
        int y = position.getY();
        String name = Thread.currentThread().getName();
        board[x][y].lock();
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Cell next = next();
                if (next.equals(getPosition())) {
                    System.out.println(String.format("%s: can't move, waiting", name));
                } else {
                    System.out.println(String.format("%s: going to %d, %d ", name, next.getX() + 1, next.getY() + 1));
                    boolean moveSuccess = move(getPosition(), next);
                    if (moveSuccess) {
                        System.out.println(String.format("%s: has come to %d, %d ", name, next.getX() + 1, next.getY() + 1));
                    } else {
                        System.out.println(String.format("%s: next cell locked! Going to another way", name));
                    }
                }
                Thread.sleep(1000);
            }
            Thread.currentThread().interrupt();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
