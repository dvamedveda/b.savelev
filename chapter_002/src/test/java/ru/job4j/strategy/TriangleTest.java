package ru.job4j.strategy;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс, содержащий тесты для класса Triangle.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class TriangleTest {

    /**
     * Тест, проверяющий, что Triangle.draw() возвращает треугольник в псевдографике.
     */
    @Test
    public void whenCallTriangleDrawThenReturnsTriangle() {
        String delimiter = System.lineSeparator();
        Triangle triangle = new Triangle();
        String result = triangle.draw();
        String expected = new StringBuilder()
                .append("    #    ").append(delimiter)
                .append("   ###   ").append(delimiter)
                .append("  #####  ").append(delimiter)
                .append(" ####### ").append(delimiter)
                .append("#########").append(delimiter)
                .toString();
        assertThat(result, is(expected));
    }
}