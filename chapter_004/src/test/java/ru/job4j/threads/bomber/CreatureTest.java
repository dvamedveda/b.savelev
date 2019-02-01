package ru.job4j.threads.bomber;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

import static org.hamcrest.CoreMatchers.is;

/**
 * Класс, реализующий тесты для класса ru.job4j.threads.bomber.Creature.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */

public class CreatureTest {

    /**
     * Проверяется передвижение существа на клетку, которая в пределах игрового поля.
     */
    @Test
    public void whenNextCellInBoardThenMoveOnItIsValid() {
        ReentrantLock[][] board = new ReentrantLock[2][2];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        Cell startPosition = new Cell(0, 0);
        Cell endPosition = new Cell(0, 1);
        Hero hero = new Hero(board, startPosition);
        Thread heroMove = new Thread(hero);
        heroMove.start();
        Assert.assertTrue(hero.moveIsValid(endPosition));
    }

    /**
     * Проверяется передвижение существа на клетку, которая не в пределах игрового поля.
     */
    @Test
    public void whenNextCellNotInBoardThenMoveOnItIsInvalid() {
        ReentrantLock[][] board = new ReentrantLock[2][2];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        Cell startPosition = new Cell(0, 0);
        Cell endPosition = new Cell(0, -1);
        Hero hero = new Hero(board, startPosition);
        Thread heroMove = new Thread(hero);
        heroMove.start();
        Assert.assertFalse(hero.moveIsValid(endPosition));
    }

    /**
     * Проверяется возвращение позиции существа.
     */
    @Test
    public void whenCallPositionThenReturnPosition() {
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
        Assert.assertThat(hero.position, is(startPosition));
    }
}