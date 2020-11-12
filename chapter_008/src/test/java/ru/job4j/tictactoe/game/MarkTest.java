package ru.job4j.tictactoe.game;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * Тесты классов ru.job4j.tictactoe.game.*Mark.
 */
public class MarkTest {

    /**
     * Здесь проверяется создание фишек для игры.
     */
    @Test
    public void whenCreateMarksThenSuccess() {
        Mark o = new OMark();
        Mark x = new XMark();
        Assert.assertThat(o.getType(), is("O"));
        Assert.assertThat(x.getType(), is("X"));
    }
}