package ru.job4j.tictactoe.game;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.tictactoe.io.ConsoleInput;
import ru.job4j.tictactoe.io.ConsolePrinter;
import ru.job4j.tictactoe.io.Input;
import ru.job4j.tictactoe.io.Printer;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;

/**
 * Тесты класса ru.job4j.tictactoe.game.GameRules.
 */
public class GameRulesTest {

    /**
     * Здесь проверяется возможность хода в любую ячейку на пустом поле.
     */
    @Test
    public void whenCreateRulesThenAllMovesAvailable() {
        Board board = new GameBoard(2, 2);
        Rules rules = new GameRules(board);
        Assert.assertThat(rules.moveAvailable(new GamePoint(0, 0)), is(true));
        Assert.assertThat(rules.moveAvailable(new GamePoint(0, 1)), is(true));
        Assert.assertThat(rules.moveAvailable(new GamePoint(1, 0)), is(true));
        Assert.assertThat(rules.moveAvailable(new GamePoint(1, 1)), is(true));
    }

    /**
     * Здесь проверяется, что первым ходит игрок, зарегистрированный первым.
     */
    @Test
    public void whenRegisterPlayerThenFirstPlayerDoMove() {
        Board board = new GameBoard(2, 2);
        Rules rules = new GameRules(board);
        Mark x = new XMark();
        Mark o = new OMark();
        Player player1 = new SimplePlayer(x, rules, board);
        rules.registerPlayer(player1);
        Player player2 = new SimplePlayer(o, rules, board);
        rules.registerPlayer(player2);
        Assert.assertThat(rules.currentPlayer().getMark(), is(x));
    }

    /**
     * Здесь проверяется завершение игры выигрышем, если есть заполненная линия.
     */
    @Test
    public void whenHaveLineThenGameOver() {
        Mark x = new XMark();
        Mark o = new OMark();
        Board board = new GameBoard(3, 3);
        board.setField(new GamePoint(1, 0), o);
        board.setField(new GamePoint(0, 1), x);
        board.setField(new GamePoint(1, 1), x);
        board.setField(new GamePoint(2, 1), x);
        board.setField(new GamePoint(1, 2), o);
        Rules rules = new GameRules(board);
        Player player1 = new SimplePlayer(x, rules, board);
        rules.registerPlayer(player1);
        Player player2 = new SimplePlayer(o, rules, board);
        rules.registerPlayer(player2);
        Assert.assertThat(rules.gameOver(), is(true));
        Assert.assertThat(rules.drawGame(), is(false));
    }

    /**
     * Здесь проверяется завершение игры выигрышем, если нет заполненных линий.
     */
    @Test
    public void whenNotHaveLineThenGameNotOver() {
        Mark x = new XMark();
        Mark o = new OMark();
        Board board = new GameBoard(3, 3);
        board.setField(new GamePoint(1, 0), o);
        board.setField(new GamePoint(0, 1), x);
        board.setField(new GamePoint(2, 1), x);
        board.setField(new GamePoint(1, 2), o);
        Rules rules = new GameRules(board);
        Player player1 = new SimplePlayer(x, rules, board);
        rules.registerPlayer(player1);
        Player player2 = new SimplePlayer(o, rules, board);
        rules.registerPlayer(player2);
        Assert.assertThat(rules.gameOver(), is(false));
    }

    /**
     * Здесь проверяется очередность ходов игроками.
     */
    @Test
    public void whenDoPlayThenNextPlayerChangesCorrect() {
        Board board = new GameBoard(2, 2);
        Rules rules = new GameRules(board);
        Mark x = new XMark();
        Mark o = new OMark();
        Player player1 = new SimplePlayer(x, rules, board);
        rules.registerPlayer(player1);
        Player player2 = new SimplePlayer(o, rules, board);
        rules.registerPlayer(player2);
        Assert.assertThat(rules.currentPlayer().getMark(), is(x));
        rules.moveDone();
        Assert.assertThat(rules.currentPlayer().getMark(), is(o));
        rules.moveDone();
        Assert.assertThat(rules.currentPlayer().getMark(), is(x));
        rules.moveDone();
        Assert.assertThat(rules.currentPlayer().getMark(), is(o));
        rules.moveDone();
        Assert.assertThat(rules.currentPlayer().getMark(), is(x));
    }

    /**
     * Здесь проверяется регистрация одного игрока дважды.
     */
    @Test
    public void whenRegisterOnePlayerTwoTimesThenGetError() {
        OutputStream byteout = new ByteArrayOutputStream();
        PrintStream stdout = System.out;
        System.setOut(new PrintStream(byteout));
        Board board = new GameBoard(2, 2);
        Rules rules = new GameRules(board);
        Mark x = new XMark();
        Player player1 = new SimplePlayer(x, rules, board);
        rules.registerPlayer(player1);
        rules.registerPlayer(player1);
        String actual = byteout.toString();
        String expected = "This player already registered!" + System.lineSeparator();
        Assert.assertThat(actual, is(expected));
        System.setOut(stdout);
    }

    /**
     * Здесь проверяется завершение игры вничью, если нет заполненных линий и ходить больше некуда.
     */
    @Test
    public void whenNotPlaceAndNoLineThenGameIsDraw() {
        Mark x = new XMark();
        Mark o = new OMark();
        Board board = new GameBoard(3, 3);
        board.setField(new GamePoint(0, 0), o);
        board.setField(new GamePoint(1, 0), o);
        board.setField(new GamePoint(2, 0), x);
        board.setField(new GamePoint(0, 1), x);
        board.setField(new GamePoint(1, 1), x);
        board.setField(new GamePoint(2, 1), o);
        board.setField(new GamePoint(0, 2), o);
        board.setField(new GamePoint(1, 2), x);
        board.setField(new GamePoint(2, 2), x);
        Rules rules = new GameRules(board);
        Player player1 = new SimplePlayer(x, rules, board);
        rules.registerPlayer(player1);
        Player player2 = new SimplePlayer(o, rules, board);
        rules.registerPlayer(player2);
        Assert.assertThat(rules.gameOver(), is(false));
        Assert.assertThat(rules.drawGame(), is(true));
    }

    /**
     * Здесь проверяется запуск и ход игры.
     */
    @Test
    public void whenStartGameThenCorrectPlays() {
        String line = System.lineSeparator();
        String answers = "0, 0" + line + "0, 1" + line + "1, 1";
        InputStream byteInput = new ByteArrayInputStream((answers).getBytes());
        InputStream stdin = System.in;
        System.setIn(byteInput);
        Input input = new ConsoleInput();
        Printer printer = new ConsolePrinter();
        Board board = new GameBoard(2, 2);
        Rules rules = new GameRules(board);
        Player player1 = new SimplePlayer(new XMark(), rules, board);
        Player player2 = new SimplePlayer(new OMark(), rules, board);
        rules.registerPlayer(player1);
        rules.registerPlayer(player2);
        rules.startGame(input, printer);
        Assert.assertThat(rules.gameOver(), is(true));
        Assert.assertThat(rules.drawGame(), is(false));
        System.setIn(stdin);
    }

    /**
     * Здесь проверяется запуск игры когда игроков недостаточно.
     */
    @Test
    public void whenStartGameWithOnePlayerThenError() {
        String line = System.lineSeparator();
        OutputStream byteOut = new ByteArrayOutputStream();
        PrintStream stdout = System.out;
        System.setOut(new PrintStream(byteOut));
        Input input = new ConsoleInput();
        Printer printer = new ConsolePrinter();
        Board board = new GameBoard(2, 2);
        Rules rules = new GameRules(board);
        Player player1 = new SimplePlayer(new XMark(), rules, board);
        rules.registerPlayer(player1);
        rules.startGame(input, printer);
        String output = byteOut.toString();
        String expectedOutput = new StringBuilder()
                .append("----------------------------").append(line)
                .append("SORRY, YOU MUST HAVE AT LEAST TWO PLAYERS!").append(line)
                .append("----------------------------").append(line)
                .append("Thank you for game!").append(line)
                .toString();
        Assert.assertThat(output, is(expectedOutput));
        System.setOut(stdout);
    }
}