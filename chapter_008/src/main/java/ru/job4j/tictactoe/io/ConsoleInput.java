package ru.job4j.tictactoe.io;

import ru.job4j.tictactoe.game.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс, реализующий ввод данных от игроков.
 */
public class ConsoleInput implements Input {
    /**
     * Поток ввода - стандартный поток ввода с консоли.
     */
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    /**
     * Запросить данные для совершения хода игроком или сигнал для выхода из игры.
     *
     * @param player игрок, от которого ожидается ход.
     * @return координаты хода.
     */
    @Override
    public String askMove(Player player) {
        String result = "";
        System.out.println("Waiting move from player " + player.getMark().getType());
        try {
            String answer = "";
            boolean valid = false;
            while (!valid) {
                answer = reader.readLine();
                if (answer.equals("exit")) {
                    break;
                }
                valid = isCoordinates(answer);
                if (!valid) {
                    System.out.println("Enter two integer coordinates!");
                }
            }
            result = answer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Запросить размер игрового поля или сигнал о выходе из игры.
     *
     * @return размер игрового поля.
     */
    @Override
    public String askBoardSize() {
        String line = System.lineSeparator();
        String result = null;
        System.out.println("Welcome to TicTacToe!" + line
                + "----------------------------" + line
                + "Enter board size (one integer number) or type 'exit' to exit:");
        try {
            String answer = "";
            boolean validSize = false;
            while (!validSize) {
                answer = reader.readLine();
                if (answer.equals("exit")) {
                    result = answer;
                    break;
                }
                try {
                    int size = Integer.parseInt(answer);
                    if (size > 0) {
                        validSize = true;
                        result = Integer.toString(size);
                    } else {
                        System.out.println("Board size must be positive number, at least 1!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Enter one integer digit!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Вспомогательный метод для проверки, является ли строка координатами.
     *
     * @param string строка для проверки.
     * @return строка содержит координаты или нет.
     */
    private boolean isCoordinates(String string) {
        boolean result = false;
        String[] parts = string.split(",");
        if (parts.length == 2) {
            try {
                Integer.parseInt(parts[0].trim());
                Integer.parseInt(parts[1].trim());
                result = true;
            } catch (NumberFormatException e) {
                result = false;
            }
        }
        return result;
    }
}