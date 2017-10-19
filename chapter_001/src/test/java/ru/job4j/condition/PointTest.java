package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс с тестами методов класса Point.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class PointTest {

    /**
     * Тест метода Point.is(a, b).
     * Выполняется при условии, что
     * Point.getY() = первый параметр is() * Point.getX() + второй параметр is()
     */
    @Test
    public void whenPointOnLineThenTrue() {
        Point point = new Point(2, 5);
        boolean result = point.is(2, 1);
        assertThat(result, is(true));
    }
}