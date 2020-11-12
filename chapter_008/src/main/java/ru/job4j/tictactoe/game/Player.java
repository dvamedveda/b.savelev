package ru.job4j.tictactoe.game;

/**
 * Интерфейс, описывающий игрока.
 */
public interface Player {

    /**
     * Сделать ход по координатам.
     *
     * @param point координаты.
     */
    void doMove(Point point);

    /**
     * Получить фишку игрока.
     *
     * @return фишка игрока.
     */
    Mark getMark();
}