package ru.job4j.tictactoe.game;

import ru.job4j.tictactoe.io.Input;
import ru.job4j.tictactoe.io.Printer;

/**
 * Интерфейс, описывающий правила игры.
 */
public interface Rules {
    /**
     * Проверить, закончена ли игра с выигрышем.
     *
     * @return победил кто-то из игроков или нет.
     */
    boolean gameOver();

    /**
     * Проверить, закончена ли игра вничью:
     * никто не выиграл, поле заполнено.
     *
     * @return ничья или нет.
     */
    boolean drawGame();

    /**
     * Доступен ли ход по координатам.
     *
     * @param point координаты.
     * @return ход доступен или нет.
     */
    boolean moveAvailable(Point point);

    /**
     * Получить текущего игрока, от которого ожидается ход.
     *
     * @return текущий игрок.
     */
    Player currentPlayer();

    /**
     * Зарегистрировать игрока в правилах.
     *
     * @param player новый игрок.
     */
    void registerPlayer(Player player);

    /**
     * Зафиксировать ход от игрока.
     */
    void moveDone();

    /**
     * Запустить игру.
     * @param input объект для ввода данных от игрока.
     * @param printer объект для вывода данных игроку.
     */
    void startGame(Input input, Printer printer);
}