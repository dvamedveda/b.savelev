package ru.job4j.threads.bomber;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс, реализующий существо "герой" для игры бомберман.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Hero extends Creature implements Runnable {

    /**
     * Флаг, показывающий, нужно ли переместиться герою.
     */
    private boolean needMove = false;

    /**
     * Направление для движения героя при следующем ходе.
     */
    private Direction direction = Direction.STAY;

    /**
     * Флаг завершения игры.
     */
    private boolean exit = false;

    /**
     * Конструктор героя.
     *
     * @param board    игровое поле.
     * @param position позиция героя.
     */
    public Hero(ReentrantLock[][] board, Cell position) {
        this.board = board;
        this.position = position;
    }

    /**
     * Задача для потока героя.
     * Пока игра не завершена, поток ждет указания на перемещение.
     * После чего пытается перейти на одну клетку в заданном направлении.
     */
    public void run() {
        Thread.currentThread().setName("Hero");
        board[position.getX()][position.getY()].lock();
        System.out.println(String.format("%d, %d placed hero", position.getX() + 1, position.getY() + 1));
        try {
            while (!exit) {
                if (needMove && direction == Direction.UP) {
                    tryMove(new Cell(position.getX(), position.getY() - 1));
                } else if (needMove && direction == Direction.RIGHT) {
                    tryMove(new Cell(position.getX() + 1, position.getY()));
                } else if (needMove && direction == Direction.DOWN) {
                    tryMove(new Cell(position.getX(), position.getY() + 1));
                } else if (needMove && direction == Direction.LEFT) {
                    tryMove(new Cell(position.getX() - 1, position.getY()));
                }
                Thread.sleep(1000);
                if (board[position.getX()][position.getY()].hasQueuedThreads()) {
                    System.out.println(String.format("%s: monster catched me!", Thread.currentThread().getName()));
                    stopGame();
                }
            }
            Thread.currentThread().interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        System.out.println("Game stopped, exit.");
    }

    /**
     * Метод для передвижения героя вправо.
     */
    public void moveRight() {
        needMove = true;
        direction = Direction.RIGHT;
    }

    /**
     * Метод для передвижения героя влево.
     */
    public void moveLeft() {
        needMove = true;
        direction = Direction.LEFT;
    }

    /**
     * Метод для передвижения героя вверх.
     */
    public void moveUp() {
        needMove = true;
        direction = Direction.UP;
    }

    /**
     * Метод для передвижения героя вниз.
     */
    public void moveDown() {
        needMove = true;
        direction = Direction.DOWN;
    }

    /**
     * Внутренний перечень возможных направлений движения героя.
     * Четыре стороны, STAY - оставаться на месте.
     */
    private enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        STAY
    }

    /**
     * Попробовать переместиться в заданную ячейку.
     *
     * @param nextCell конечная ячейка.
     * @throws InterruptedException в случае, когда поток прерван во время захвата блокировки.
     */
    private void tryMove(Cell nextCell) throws InterruptedException {
        String name = Thread.currentThread().getName();
        System.out.println(String.format("%s: going to %d, %d ", name, nextCell.getX(), nextCell.getY()));
        boolean moveSuccess = move(getPosition(), nextCell);
        if (moveSuccess) {
            position = nextCell;
        } else {
            System.out.println(String.format("%s: next cell locked! Going to another way", name));
        }
        direction = Direction.STAY;
        needMove = false;
    }

    /**
     * Метод для завершения потока героя.
     * Вызывается при завершении игры.
     */
    public void stopGame() {
        this.exit = true;
    }
}
