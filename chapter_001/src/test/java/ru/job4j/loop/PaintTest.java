package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса Paint.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class PaintTest {

    /**
     * Тест метода Paint.pyramid().
     * Проверяет правильность построенной пирамиды при заданной высоте 3.
     */
    @Test
    public void whenPyramidHeightIsThreeThenWidthIsFiveAndRowsIsThree() {
        Paint paint = new Paint();
        String line = System.lineSeparator();
        String result = paint.pyramid(3);
        String expected = String.format("  ^  %s ^^^ %s^^^^^%s", line, line, line);
        assertThat(result, is(expected));
    }

    /**
     * Тест метода Paint.pyramid().
     * Проверяет правильность построенной пирамиды при заданной высоте 5.
     */
    @Test
    public void whenPyramidHeightIsFiveThenWidthIsSevenAndRowsIsFive() {
        Paint paint = new Paint();
        String line = System.lineSeparator();
        String result = paint.pyramid(5);
        String expected = String.format("    ^    %s   ^^^   %s  ^^^^^  %s ^^^^^^^ %s^^^^^^^^^%s", line, line, line, line, line);
        assertThat(result, is(expected));
    }
}