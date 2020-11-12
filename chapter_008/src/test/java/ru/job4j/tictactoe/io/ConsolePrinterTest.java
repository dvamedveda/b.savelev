package ru.job4j.tictactoe.io;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.tictactoe.game.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;


/**
 * Тесты класса ru.job4j.tictactoe.game.ConsolePrinter.
 */
public class ConsolePrinterTest {

    /**
     * Здесь проверяется печать пустого поля.
     */
    @Test
    public void whenPrintEmptyBoardThenCorrect() {
        OutputStream byteout = new ByteArrayOutputStream();
        PrintStream stdout = System.out;
        System.setOut(new PrintStream(byteout));
        Printer printer = new ConsolePrinter();
        Board board = new GameBoard(3, 3);
        printer.printBoard(board);
        String actual = byteout.toString();
        String expected = new StringBuilder()
                .append("Current board state:").append(System.lineSeparator())
                .append("| | | |").append(System.lineSeparator())
                .append("| | | |").append(System.lineSeparator())
                .append("| | | |").append(System.lineSeparator())
                .toString();
        Assert.assertThat(actual, is(expected));
        System.setOut(stdout);
    }

    /**
     * Здесь проверяется печать непустого поля.
     */
    @Test
    public void whenPrintOneLineBoardThenCorrect() {
        OutputStream byteout = new ByteArrayOutputStream();
        PrintStream stdout = System.out;
        System.setOut(new PrintStream(byteout));
        Printer printer = new ConsolePrinter();
        Board board = new GameBoard(3, 3);
        Mark x = new XMark();
        Mark o = new OMark();
        board.setField(new GamePoint(0, 0), o);
        board.setField(new GamePoint(0, 1), x);
        board.setField(new GamePoint(1, 1), x);
        board.setField(new GamePoint(2, 1), x);
        board.setField(new GamePoint(2, 2), o);
        printer.printBoard(board);
        String actual = byteout.toString();
        String expected = new StringBuilder()
                .append("Current board state:").append(System.lineSeparator())
                .append("|O| | |").append(System.lineSeparator())
                .append("|X|X|X|").append(System.lineSeparator())
                .append("| | |O|").append(System.lineSeparator())
                .toString();
        Assert.assertThat(actual, is(expected));
        System.setOut(stdout);
    }

    /**
     * Здесь проверяется печать прощания.
     */
    @Test
    public void whenPrintByeThenCorrect() {
        OutputStream byteout = new ByteArrayOutputStream();
        PrintStream stdout = System.out;
        System.setOut(new PrintStream(byteout));
        Printer printer = new ConsolePrinter();
        printer.printBye();
        String actual = byteout.toString();
        String expected = new StringBuilder()
                .append("----------------------------").append(System.lineSeparator())
                .append("Thank you for game!").append(System.lineSeparator())
                .toString();
        Assert.assertThat(actual, is(expected));
        System.setOut(stdout);
    }

    /**
     * Здесь проверяется печать информации об игре, завершившейся выигрышем.
     */
    @Test
    public void whenPrintWinGameThenCorrect() {
        OutputStream byteout = new ByteArrayOutputStream();
        PrintStream stdout = System.out;
        System.setOut(new PrintStream(byteout));
        Printer printer = new ConsolePrinter();
        printer.winGame("X");
        String actual = byteout.toString();
        String expected = new StringBuilder()
                .append("----------------------------").append(System.lineSeparator())
                .append("PLAYER X WINS!!!").append(System.lineSeparator())
                .toString();
        Assert.assertThat(actual, is(expected));
        System.setOut(stdout);
    }

    /**
     * Здесь проверяется печать информации об игре, завершившейся вничью.
     */
    @Test
    public void whenPrintDrawGameThenCorrect() {
        OutputStream byteout = new ByteArrayOutputStream();
        PrintStream stdout = System.out;
        System.setOut(new PrintStream(byteout));
        Printer printer = new ConsolePrinter();
        printer.drawGame("X");
        String actual = byteout.toString();
        String expected = new StringBuilder()
                .append("----------------------------").append(System.lineSeparator())
                .append("SORRY, THIS IS A DRAW GAME").append(System.lineSeparator())
                .append("PLAYER X DID LAST MOVE IN THIS GAME!").append(System.lineSeparator())
                .toString();
        Assert.assertThat(actual, is(expected));
        System.setOut(stdout);
    }

    /**
     * Здесь проверяется печать информации о том, что недостаточно игроков.
     */
    @Test
    public void whenNotEnoughPlayersThenCorrect() {
        OutputStream byteout = new ByteArrayOutputStream();
        PrintStream stdout = System.out;
        System.setOut(new PrintStream(byteout));
        Printer printer = new ConsolePrinter();
        printer.notEnoughPlayers();
        String actual = byteout.toString();
        String expected = new StringBuilder()
                .append("----------------------------").append(System.lineSeparator())
                .append("SORRY, YOU MUST HAVE AT LEAST TWO PLAYERS!").append(System.lineSeparator())
                .toString();
        Assert.assertThat(actual, is(expected));
        System.setOut(stdout);
    }
}