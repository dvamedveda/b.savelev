package ru.job4j.tictactoe;

import ru.job4j.tictactoe.game.*;
import ru.job4j.tictactoe.io.ConsoleInput;
import ru.job4j.tictactoe.io.ConsolePrinter;
import ru.job4j.tictactoe.io.Input;
import ru.job4j.tictactoe.io.Printer;

/**
 * Класс, реализующий игру в крестики-нолики для демонстрации.
 * Здесь используется консольный ввод и вывод данных.
 */

public class Game {
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Printer printer = new ConsolePrinter();
        int boardSize;
        String answer = input.askBoardSize();
        if (answer.equals("exit")) {
            return;
        } else {
            boardSize = Integer.parseInt(answer);
        }
        Board board = new GameBoard(boardSize, boardSize);
        Rules rules = new GameRules(board);
        Player player1 = new SimplePlayer(new XMark(), rules, board);
        Player player2 = new SimplePlayer(new OMark(), rules, board);
        rules.registerPlayer(player1);
        rules.registerPlayer(player2);
        rules.startGame(input, printer);
    }
}