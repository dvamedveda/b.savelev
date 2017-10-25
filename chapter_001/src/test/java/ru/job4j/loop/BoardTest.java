package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса Board.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class BoardTest {

    /**
     * Тест метода Board.paint().
     * Проверятся рисование шахматной доски размером 3х3.
     */
    @Test
    public void whenPaintingBoardThreeWidthAndThreeHeightThenResultIsThreeRowAndThreeColBoard() {
        Board board = new Board();
        String result = board.paint(3, 3);
        final String separator = System.getProperty("line.separator");
        String expected = String.format("X X%s X %sX X%s", separator, separator, separator);
        assertThat(result, is(expected));
    }

    /**
     * Тест метода Board.paint().
     * Проверятся рисование шахматной доски размером 5х4.
     */
    @Test
    public void whenPaintingBoardFiveWidthAndFourHeightThenResultIsFourRowAndFiveColBoard() {
        Board board = new Board();
        String result = board.paint(5, 4);
        final String separator = System.getProperty("line.separator");
        String expected = String.format("X X X%s X X %sX X X%s X X %s", separator, separator, separator, separator);
        assertThat(result, is(expected));
    }
}