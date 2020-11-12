package ru.job4j.tictactoe.io;

import ru.job4j.tictactoe.game.Board;
import ru.job4j.tictactoe.game.GamePoint;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Класс, реализующий вывод данных для игроков.
 */
public class ConsolePrinter implements Printer {

    /**
     * Системный разделитель строк.
     */
    private String line = System.lineSeparator();

    /**
     * Поток вывода.
     */
    private OutputStream outputStream;

    /**
     * Конструктор класса вывода.
     * Потоком вывода становится стандартный вывод на консоль.
     */
    public ConsolePrinter() {
        this.outputStream = System.out;
    }

    /**
     * Напечатать текущее состояние игрового поля.
     *
     * @param board игровое поле.
     */
    @Override
    public void printBoard(Board board) {
        try {
            outputStream.write("Current board state:".getBytes());
            outputStream.write(this.line.getBytes());
            for (int y = 0; y < board.getHeight(); y++) {
                outputStream.write("|".getBytes());
                String[] line = new String[board.getLength()];
                for (int x = 0; x < board.getLength(); x++) {
                    line[x] = board.getField(new GamePoint(x, y));
                }
                for (String mark : line) {
                    outputStream.write((mark.isEmpty() ? " " : mark).getBytes());
                    outputStream.write("|".getBytes());
                }
                outputStream.write(this.line.getBytes());
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Напечатать информацию о выигрыше.
     *
     * @param playerMark фишка, ходившая последней.
     */
    @Override
    public void winGame(String playerMark) {
        try {
            outputStream.write("----------------------------".getBytes());
            outputStream.write(this.line.getBytes());
            outputStream.write(("PLAYER " + playerMark + " WINS!!!").getBytes());
            outputStream.write(this.line.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Напечатать информацию о ничьей.
     *
     * @param playerMark фишка ходившая последней.
     */
    @Override
    public void drawGame(String playerMark) {
        try {
            outputStream.write("----------------------------".getBytes());
            outputStream.write(this.line.getBytes());
            outputStream.write("SORRY, THIS IS A DRAW GAME".getBytes());
            outputStream.write(this.line.getBytes());
            outputStream.write(("PLAYER " + playerMark + " DID LAST MOVE IN THIS GAME!").getBytes());
            outputStream.write(this.line.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Напечатать прощание.
     */
    @Override
    public void printBye() {
        try {
            outputStream.write("----------------------------".getBytes());
            outputStream.write(this.line.getBytes());
            outputStream.write("Thank you for game!".getBytes());
            outputStream.write(this.line.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Напечатать информацию о том, что недостаточно игроков.
     */
    @Override
    public void notEnoughPlayers() {
        try {
            outputStream.write("----------------------------".getBytes());
            outputStream.write(this.line.getBytes());
            outputStream.write("SORRY, YOU MUST HAVE AT LEAST TWO PLAYERS!".getBytes());
            outputStream.write(this.line.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}