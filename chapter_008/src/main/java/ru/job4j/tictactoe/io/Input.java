package ru.job4j.tictactoe.io;

import ru.job4j.tictactoe.game.Player;

/**
 * Интерфейс для ввода данных от игроков.
 */
public interface Input {

    /**
     * Запросить данные для совершения хода игроком или сигнал для выхода из игры.
     *
     * @param player игрок, от которого ожидается ход.
     * @return координаты хода.
     */
    String askMove(Player player);

    /**
     * Запросить размер игрового поля или сигнал для выхода из игры.
     *
     * @return размер игрового поля.
     */
    String askBoardSize();
}