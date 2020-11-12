package ru.job4j.tictactoe.io;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.tictactoe.game.*;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;

/**
 * Тесты класса ru.job4j.tictactoe.io.ConsoleInput.
 */
public class ConsoleInputTest {

    /**
     * Здесь проверяется ввод корректных координат для хода от игрока.
     */
    @Test
    public void whenAskCoordinatesThenCorrect() {
        InputStream byteInput = new ByteArrayInputStream(("0, 0" + System.lineSeparator()).getBytes());
        OutputStream byteOut = new ByteArrayOutputStream();
        InputStream stdin = System.in;
        PrintStream stdout = System.out;
        System.setIn(byteInput);
        System.setOut(new PrintStream(byteOut));
        Mark x = new XMark();
        Board board = new GameBoard(2, 2);
        Rules rules = new GameRules(board);
        Player player1 = new SimplePlayer(x, rules, board);
        Input input = new ConsoleInput();
        Point result = GamePoint.toPoint(input.askMove(player1));
        String output = byteOut.toString();
        String expected = new StringBuilder()
                .append("Waiting move from player X").append(System.lineSeparator())
                .toString();
        Assert.assertThat(result.getX(), is(0));
        Assert.assertThat(result.getY(), is(0));
        Assert.assertThat(output, is(expected));
        System.setIn(stdin);
        System.setOut(stdout);
    }

    /**
     * Здесь проверяется ввод некорректных координат для хода от игрока.
     */
    @Test
    public void whenIncorrectCoordinatesThenCorrect() {
        InputStream byteInput = new ByteArrayInputStream(("5, 5" + System.lineSeparator()).getBytes());
        OutputStream byteOut = new ByteArrayOutputStream();
        InputStream stdin = System.in;
        PrintStream stdout = System.out;
        System.setIn(byteInput);
        System.setOut(new PrintStream(byteOut));
        Mark x = new XMark();
        Board board = new GameBoard(2, 2);
        Rules rules = new GameRules(board);
        Player player1 = new SimplePlayer(x, rules, board);
        Input input = new ConsoleInput();
        Point result = GamePoint.toPoint(input.askMove(player1));
        String output = byteOut.toString();
        String expected = new StringBuilder()
                .append("Waiting move from player X").append(System.lineSeparator())
                .toString();
        Assert.assertThat(result.getX(), is(5));
        Assert.assertThat(result.getY(), is(5));
        Assert.assertThat(output, is(expected));
        System.setIn(stdin);
        System.setOut(stdout);
    }

    /**
     * Здесь проверяется ввод отличных от координат данных для хода от игрока.
     */
    @Test
    public void whenIncorrectInputThenOutputError() {
        String line = System.lineSeparator();
        InputStream byteInput = new ByteArrayInputStream(("a, b" + line + "exit" + line).getBytes());
        OutputStream byteOut = new ByteArrayOutputStream();
        InputStream stdin = System.in;
        PrintStream stdout = System.out;
        System.setIn(byteInput);
        System.setOut(new PrintStream(byteOut));
        Mark x = new XMark();
        Board board = new GameBoard(2, 2);
        Rules rules = new GameRules(board);
        Player player1 = new SimplePlayer(x, rules, board);
        Input input = new ConsoleInput();
        String result = input.askMove(player1);
        String output = byteOut.toString();
        String expected = new StringBuilder()
                .append("Waiting move from player X").append(System.lineSeparator())
                .append("Enter two integer coordinates!").append(System.lineSeparator())
                .toString();
        Assert.assertThat(result, is("exit"));
        Assert.assertThat(output, is(expected));
        System.setIn(stdin);
        System.setOut(stdout);
    }

    /**
     * Здесь проверяется ввод размера поля от игрока.
     */
    @Test
    public void whenAskBoardSizeThenCorrect() {
        InputStream byteInput = new ByteArrayInputStream(("1" + System.lineSeparator()).getBytes());
        OutputStream byteOut = new ByteArrayOutputStream();
        InputStream stdin = System.in;
        PrintStream stdout = System.out;
        System.setIn(byteInput);
        System.setOut(new PrintStream(byteOut));
        Input input = new ConsoleInput();
        String result = input.askBoardSize();
        String output = byteOut.toString();
        String expectedSize = "1";
        String expectedOutput = new StringBuilder()
                .append("Welcome to TicTacToe!").append(System.lineSeparator())
                .append("----------------------------").append(System.lineSeparator())
                .append("Enter board size (one integer number) or type 'exit' to exit:").append(System.lineSeparator())
                .toString();
        Assert.assertThat(result, is(expectedSize));
        Assert.assertThat(output, is(expectedOutput));
        System.setIn(stdin);
        System.setOut(stdout);
    }

    /**
     * Здесь проверяется ввод недопустимого размера поля.
     */
    @Test
    public void whenIncorrectBoardSizeThenReaskSize() {
        String answers = "0" + System.lineSeparator() + "1" + System.lineSeparator();
        InputStream byteInput = new ByteArrayInputStream((answers).getBytes());
        OutputStream byteOut = new ByteArrayOutputStream();
        InputStream stdin = System.in;
        PrintStream stdout = System.out;
        System.setIn(byteInput);
        System.setOut(new PrintStream(byteOut));
        Input input = new ConsoleInput();
        String result = input.askBoardSize();
        String output = byteOut.toString();
        String expectedSize = "1";
        String expectedOutput = new StringBuilder()
                .append("Welcome to TicTacToe!").append(System.lineSeparator())
                .append("----------------------------").append(System.lineSeparator())
                .append("Enter board size (one integer number) or type 'exit' to exit:").append(System.lineSeparator())
                .append("Board size must be positive number, at least 1!").append(System.lineSeparator())
                .toString();
        Assert.assertThat(result, is(expectedSize));
        Assert.assertThat(output, is(expectedOutput));
        System.setIn(stdin);
        System.setOut(stdout);
    }
}