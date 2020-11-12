package ru.job4j.tictactoe.io;

import ru.job4j.tictactoe.game.Board;

/**
 * Интерфейс, описывающий вывод данных для игроков.
 */
public interface Printer {

    /**
     * Напечатать текущее состояние игрового поля.
     *
     * @param board игровое поле.
     */
    void printBoard(Board board);

    /**
     * Напечатать информацию о выигрыше.
     *
     * @param string фишка, ходившая последней.
     */
    void winGame(String string);

    /**
     * Напечатать информацию о ничьей.
     *
     * @param string фишка ходившая последней.
     */
    void drawGame(String string);

    /**
     * Напечатать прощание.
     */
    void printBye();

    /**
     * Напечатать информацию о том, что недостаточно игроков.
     */
    void notEnoughPlayers();
}