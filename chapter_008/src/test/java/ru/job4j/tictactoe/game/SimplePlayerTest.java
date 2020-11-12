package ru.job4j.tictactoe.game;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;

/**
 * Тесты класса ru.job4j.tictactoe.game.SimplePlayer.
 */
public class SimplePlayerTest {

    /**
     * Здесь проверяется создание игрока с заданной фишкой.
     */
    @Test
    public void whenCreatePlayerThenMarkGetsCorrect() {
        Board board = new GameBoard(2, 2);
        Rules rules = new GameRules(board);
        Mark x = new XMark();
        Player player1 = new SimplePlayer(x, rules, board);
        Assert.assertThat(player1.getMark(), is(x));
    }

    /**
     * Здесь проверяется смена очередности хода, если игрок выполнил ход.
     */
    @Test
    public void whenDoMoveCorrectThenNextPlayer() {
        Board board = new GameBoard(2, 2);
        Rules rules = new GameRules(board);
        Mark x = new XMark();
        Mark o = new OMark();
        Player player1 = new SimplePlayer(x, rules, board);
        Player player2 = new SimplePlayer(o, rules, board);
        rules.registerPlayer(player1);
        rules.registerPlayer(player2);
        Assert.assertThat(rules.currentPlayer().getMark(), is(player1.getMark()));
        player1.doMove(new GamePoint(0, 0));
        Assert.assertThat(rules.currentPlayer().getMark(), is(player2.getMark()));
        Assert.assertThat(rules.moveAvailable(new GamePoint(0, 0)), is(false));
        Assert.assertThat(rules.moveAvailable(new GamePoint(0, 1)), is(true));
        Assert.assertThat(rules.moveAvailable(new GamePoint(1, 0)), is(true));
        Assert.assertThat(rules.moveAvailable(new GamePoint(1, 1)), is(true));
    }

    /**
     * Здесь проверяется отсутствие смены очередности хода, если игрок не выполнил ход.
     */
    @Test
    public void whenDoMoveNotCorrectThenSamePlayerAndError() {
        OutputStream byteout = new ByteArrayOutputStream();
        PrintStream stdout = System.out;
        System.setOut(new PrintStream(byteout));
        Board board = new GameBoard(2, 2);
        Rules rules = new GameRules(board);
        Mark x = new XMark();
        Mark o = new OMark();
        Player player1 = new SimplePlayer(x, rules, board);
        Player player2 = new SimplePlayer(o, rules, board);
        rules.registerPlayer(player1);
        rules.registerPlayer(player2);
        Assert.assertThat(rules.currentPlayer().getMark(), is(player1.getMark()));
        player1.doMove(new GamePoint(3, 3));
        Assert.assertThat(rules.currentPlayer().getMark(), is(player1.getMark()));
        Assert.assertThat(rules.moveAvailable(new GamePoint(0, 0)), is(true));
        Assert.assertThat(rules.moveAvailable(new GamePoint(0, 1)), is(true));
        Assert.assertThat(rules.moveAvailable(new GamePoint(1, 0)), is(true));
        Assert.assertThat(rules.moveAvailable(new GamePoint(1, 1)), is(true));
        String actual = byteout.toString();
        String expected = "This position not empty or out, choose other field!" + System.lineSeparator();
        Assert.assertThat(actual, is(expected));
        System.setOut(stdout);
    }

    /**
     * Здесь проверяется возможности хода, если ожидается ход от другого игрока.
     */
    @Test
    public void whenDoMoveWhileOtherPlayerThenNextPlayerCorrectAndError() {
        OutputStream byteout = new ByteArrayOutputStream();
        PrintStream stdout = System.out;
        System.setOut(new PrintStream(byteout));
        Board board = new GameBoard(2, 2);
        Rules rules = new GameRules(board);
        Mark x = new XMark();
        Mark o = new OMark();
        Player player1 = new SimplePlayer(x, rules, board);
        Player player2 = new SimplePlayer(o, rules, board);
        rules.registerPlayer(player1);
        rules.registerPlayer(player2);
        Assert.assertThat(rules.currentPlayer().getMark(), is(player1.getMark()));
        player2.doMove(new GamePoint(0, 0));
        Assert.assertThat(rules.currentPlayer().getMark(), is(player1.getMark()));
        Assert.assertThat(rules.moveAvailable(new GamePoint(0, 0)), is(true));
        Assert.assertThat(rules.moveAvailable(new GamePoint(0, 1)), is(true));
        Assert.assertThat(rules.moveAvailable(new GamePoint(1, 0)), is(true));
        Assert.assertThat(rules.moveAvailable(new GamePoint(1, 1)), is(true));
        String actual = byteout.toString();
        String expected = "Now must move other player!" + System.lineSeparator();
        Assert.assertThat(actual, is(expected));
        System.setOut(stdout);
    }
}