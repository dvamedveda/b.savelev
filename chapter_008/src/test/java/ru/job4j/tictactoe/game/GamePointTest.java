package ru.job4j.tictactoe.game;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * Тесты класса ru.job4j.tictactoe.game.GamePoint.
 */
public class GamePointTest {

    /**
     * Здесь проверяется создание координат.
     */
    @Test
    public void whenCreatePointThenSuccess() {
        Point point = new GamePoint(1, 1);
        Assert.assertThat(point.getX(), is(1));
        Assert.assertThat(point.getY(), is(1));
    }

    /**
     * Здесь проверяется конвертация строки в координаты.
     */
    @Test
    public void whenConvertStringToPointThenSuccess() {
        String coords = "1, 2";
        Point point = GamePoint.toPoint(coords);
        Assert.assertThat(point.getX(), is(1));
        Assert.assertThat(point.getY(), is(2));
    }
}