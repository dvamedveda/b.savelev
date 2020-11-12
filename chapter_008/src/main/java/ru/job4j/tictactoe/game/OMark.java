package ru.job4j.tictactoe.game;

/**
 * Реализация фишки "Нолик".
 */
public class OMark implements Mark {

    /**
     * Значение фишки "Нолик".
     */
    private String mark = "O";

    /**
     * Получить значение фишки.
     *
     * @return значение фишки.
     */
    @Override
    public String getType() {
        return this.mark;
    }
}