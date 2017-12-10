package ru.job4j.strategy;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс, содержащий тесты для класса Square.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class SquareTest {

    /**
     * Тест, проверяющий, что Square.draw() возвращает квадрат в псевдографике.
     */
    @Test
    public void whenCallSquareDrawThenReturnsSquare() {
        String delimiter = System.lineSeparator();
        Square square = new Square();
        String result = square.draw();
        String expected = new StringBuilder()
                .append("#########").append(delimiter)
                .append("#########").append(delimiter)
                .append("#########").append(delimiter)
                .append("#########").append(delimiter)
                .append("#########").append(delimiter)
                .toString();
        assertThat(result, is(expected));
    }
}