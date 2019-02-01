package ru.job4j.threads.bomber;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс, реализующий игру "бомберман".
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Game {

    /**
     * Размер игрового поля.
     */
    private final int size;

    /**
     * Количество монстров.
     */
    private final int monsterQuantity;

    /**
     * Пул фиксированного размера для потоков монстров.
     */
    private final ExecutorService monsterPool;

    /**
     * Сложность игрокого поля (количество блоков на поле).
     */
    private final double difficulty;

    /**
     * Игровое поле.
     */
    private final ReentrantLock[][] board;

    /**
     * Объект героя.
     */
    private final Hero hero;

    /**
     * Начальная позиция героя.
     */
    private final Cell heroPosition = new Cell(0, 0);


    /**
     * Метод для получения объекта игрового поля.
     * Используется только в тестах.
     *
     * @return игровое поле.
     */
    ReentrantLock[][] getBoard() {
        return this.board;
    }

    /**
     * Конструктор игры.
     * В параметрах задаются:
     * размер стороны квадратного игрового поля,
     * количество монстров,
     * заполненность игрового поля блоками в процентах.
     *
     * @param size       величина стороны квадратного игрового поля.
     * @param monsters   количество монстров.
     * @param difficulty заполненность поля блоками в % от общего числа клеток на поле.
     */
    public Game(int size, int monsters, int difficulty) {
        this.size = size;
        monsterQuantity = monsters;
        this.difficulty = (this.size * this.size * difficulty) / 100;
        board = new ReentrantLock[size][size];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        this.hero = new Hero(board, heroPosition);
        this.monsterPool = Executors.newFixedThreadPool(monsters);
    }

    /**
     * Запуск игры.
     * На игровом поле:
     * запускается поток героя;
     * расставляются блоки;
     * расставляются монстры и запускаются их потоки.
     * Здесь же вызываются команды на перемещение героя.
     */
    public void startGame() {
        Thread heroMove = new Thread(hero);
        heroMove.start();
        setBlocks();
        setMonsters();
    }


    /**
     * Получить случайную незанятую клетку(кроме клетки героя).
     *
     * @return случайная незанятая клетка.
     */
    private Cell randomCell() {
        Cell result = new Cell((int) (Math.random() * size), (int) (Math.random() * this.size));
        while ((result.getX() == 0 && result.getY() == 0) || board[result.getX()][result.getY()].isLocked()) {
            result = new Cell((int) (Math.random() * size), (int) (Math.random() * this.size));
        }
        return result;
    }

    /**
     * Расставить блоки на игровом поле.
     */
    private void setBlocks() {
        Cell nextBlock;
        for (int i = 0; i < difficulty; i++) {
            do {
                nextBlock = randomCell();
                board[nextBlock.getX()][nextBlock.getY()].lock();
            } while (!board[nextBlock.getX()][nextBlock.getY()].isLocked());
            System.out.println(String.format("%d, %d is block", nextBlock.getX(), nextBlock.getY()));
        }
    }

    /**
     * Расставить монстров и запустить их потоки.
     */
    private void setMonsters() {
        Cell monsterPlace;
        for (int i = 0; i < monsterQuantity; i++) {
            monsterPlace = randomCell();
            while (!monsterPlace.isEmptyCell(board, monsterPlace)) {
                monsterPlace = randomCell();
            }
            monsterPool.execute(new Monster(board, monsterPlace, i));
            System.out.println(String.format("%d, %d placed monster-%d", monsterPlace.getX(), monsterPlace.getY(), i));
        }
    }

    /**
     * Метод для остановки игры.
     * Завершает потоки героя и монстров.
     */
    public void stopGame() {
        hero.stopGame();
        monsterPool.shutdownNow();
    }
}
