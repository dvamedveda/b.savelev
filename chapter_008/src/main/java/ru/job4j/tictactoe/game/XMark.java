package ru.job4j.tictactoe.game;

/**
 * Реализация фишки "Крестик".
 */
public class XMark implements Mark {

    /**
     * Значение фишки "Крестик".
     */
    private String mark = "X";

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