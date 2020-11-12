package ru.job4j.tictactoe.game;

/**
 * Интерфейс, описывающий фишку игрока.
 */
public interface Mark {

    /**
     * Получить значение фишки.
     *
     * @return значение фишки.
     */
    String getType();
}