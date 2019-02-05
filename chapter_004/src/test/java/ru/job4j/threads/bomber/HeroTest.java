package ru.job4j.threads.bomber;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс, реализующий тесты для класса ru.job4j.threads.bomber.Hero.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class HeroTest {

    /**
     * Проверяется передвижение героя вниз.
     */
    @Test
    public void whenHeroMoveDownThenMoveIsSuccess() throws InterruptedException {
        ReentrantLock[][] board = new ReentrantLock[2][2];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        Cell startPosition = new Cell(0, 0);
        Hero hero = new Hero(board, startPosition);
        Thread heroMove = new Thread(hero);
        heroMove.start();
        hero.moveDown();
        Thread.sleep(2000);
        Assert.assertFalse(board[0][0].isLocked());
        Assert.assertTrue(board[0][1].isLocked());
    }

    /**
     * Проверяется передвижение героя вверх.
     */
    @Test
    public void whenHeroMoveUpThenMoveIsSuccess() throws InterruptedException {
        ReentrantLock[][] board = new ReentrantLock[2][2];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        Cell startPosition = new Cell(0, 1);
        Hero hero = new Hero(board, startPosition);
        Thread heroMove = new Thread(hero);
        heroMove.start();
        hero.moveUp();
        Thread.sleep(2000);
        Assert.assertFalse(board[0][1].isLocked());
        Assert.assertTrue(board[0][0].isLocked());
    }

    /**
     * Проверяется передвижение героя вправо.
     */
    @Test
    public void whenHeroMoveRightThenMoveIsSuccess() throws InterruptedException {
        ReentrantLock[][] board = new ReentrantLock[2][2];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        Cell startPosition = new Cell(0, 0);
        Hero hero = new Hero(board, startPosition);
        Thread heroMove = new Thread(hero);
        heroMove.start();
        hero.moveRight();
        Thread.sleep(2000);
        Assert.assertFalse(board[0][0].isLocked());
        Assert.assertTrue(board[1][0].isLocked());
    }

    /**
     * Проверяется передвижение героя влево.
     */
    @Test
    public void whenHeroMoveLeftThenMoveIsSuccess() throws InterruptedException {
        ReentrantLock[][] board = new ReentrantLock[2][2];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        Cell startPosition = new Cell(1, 1);
        Hero hero = new Hero(board, startPosition);
        Thread heroMove = new Thread(hero);
        heroMove.start();
        hero.moveLeft();
        Thread.sleep(2000);
        Assert.assertFalse(board[1][1].isLocked());
        Assert.assertTrue(board[0][1].isLocked());
    }

    /**
     * Проверяется передвижение героя два раза подряд.
     */
    @Test
    public void whenMovesTwoTimesThenMoveIsSuccess() throws InterruptedException {
        ReentrantLock[][] board = new ReentrantLock[2][2];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        Cell startPosition = new Cell(0, 0);
        Hero hero = new Hero(board, startPosition);
        Thread heroMove = new Thread(hero);
        heroMove.start();
        hero.moveRight();
        Thread.sleep(2000);
        Assert.assertFalse(board[0][0].isLocked());
        Assert.assertTrue(board[1][0].isLocked());
        hero.moveDown();
        Thread.sleep(2000);
        Assert.assertFalse(board[1][0].isLocked());
        Assert.assertTrue(board[1][1].isLocked());
    }

    /**
     * Проверяется поведение героя при попытке переместиться на занятую клетку - он должен остаться на месте.
     */
    @Test
    public void whenNextCellBlockedThenMoveIsFailed() throws InterruptedException {
        ReentrantLock[][] board = new ReentrantLock[2][2];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        Cell startPosition = new Cell(0, 0);
        board[0][1].lock();
        Hero hero = new Hero(board, startPosition);
        Thread heroMove = new Thread(hero);
        heroMove.start();
        hero.moveRight();
        Thread.sleep(2000);
        Assert.assertTrue(hero.position.equals(startPosition));
    }

    /**
     * Проверяется остановка потока героя при завершении игры.
     */
    @Test
    public void whenCallStopThenHeroTerminating() throws InterruptedException {
        ReentrantLock[][] board = new ReentrantLock[2][2];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        Cell startPosition = new Cell(0, 0);
        Hero hero = new Hero(board, startPosition);
        Thread heroMove = new Thread(hero);
        heroMove.start();
        hero.stopGame();
        Thread.sleep(2000);
        Assert.assertFalse(heroMove.isAlive());
    }

    /**
     * Проверяется остановка потока героя при съедании его монстром.
     */
    @Test
    public void whenMonsterLockHeroPositionThenGameTerminating() throws InterruptedException {
        ReentrantLock[][] board = new ReentrantLock[2][2];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        Cell startPosition = new Cell(0, 0);
        Hero hero = new Hero(board, startPosition);
        Thread heroMove = new Thread(hero);
        Thread monster = new Thread(new Monster(board, new Cell(1, 1), 1));
        heroMove.start();
        monster.start();
        while (heroMove.isAlive()) {
            Thread.sleep(100);
        }
        Assert.assertFalse(heroMove.isInterrupted());
    }
}