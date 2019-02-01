package ru.job4j.threads.bomber;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

import static org.hamcrest.CoreMatchers.is;

/**
 * Класс, реализующий тесты для класса ru.job4j.threads.bomber.Game.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class GameTest {

    /**
     * Проверяется расстановка правильного количества блоков на поле.
     */
    @Test
    public void whenCreateGameThenBlocksSetSuccess() throws InterruptedException {
        Game game = new Game(10, 1, 10);
        game.startGame();
        Thread.sleep(2000);
        ReentrantLock[][] board = game.getBoard();
        int expectedBlocks = 11;
        int resultBlocks = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].isLocked()) {
                    if (i != 0 || j != 0) {
                        resultBlocks++;
                    }
                }
            }
        }
        game.stopGame();
        Assert.assertThat(resultBlocks, is(expectedBlocks));
    }

    /**
     * Проверяется расстановка правильного количества монстров на поле.
     */
    @Test
    public void whenCreateGameThenMonsterSetSuccess() throws InterruptedException {
        Game game = new Game(2, 2, 0);
        game.startGame();
        Thread.sleep(2000);
        ReentrantLock[][] board = game.getBoard();
        int expectedMonsters = 2;
        int resultMonsters = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].isLocked()) {
                    if (i != 0 || j != 0) {
                        resultMonsters++;
                    }
                }
            }
        }
        game.stopGame();
        Assert.assertThat(resultMonsters, is(expectedMonsters));
    }
}