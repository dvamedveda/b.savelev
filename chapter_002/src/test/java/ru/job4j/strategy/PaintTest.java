package ru.job4j.strategy;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс, содержащий тесты для класса Paint.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class PaintTest {

    /**
     * Тест, проверяющий вывод на консоль квадрата при использовании класса Paint и передачи ему квадрата.
     */
    @Test
    public void whenUseSquareThenStrategyDrawsSquare() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream byteout = new ByteArrayOutputStream();
        String delimiter = System.lineSeparator();

        System.setOut(new PrintStream(byteout));
        new Paint(new Square()).drawShape();
        String result = new String(byteout.toByteArray());
        String expected = new StringBuilder()
                .append("#########").append(delimiter)
                .append("#########").append(delimiter)
                .append("#########").append(delimiter)
                .append("#########").append(delimiter)
                .append("#########").append(delimiter)
                .append(delimiter)
                .toString();
        assertThat(result, is(expected));
        System.setOut(stdout);
    }

    /**
     * Тест, проверяющий вывод на консоль треугольника при использовании класса Paint и передачи ему треугольника.
     */
    @Test
    public void whenUseTrinagleThenStrategyDrawsTriangle() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream byteout = new ByteArrayOutputStream();
        String delimiter = System.lineSeparator();

        System.setOut(new PrintStream(byteout));
        new Paint(new Triangle()).drawShape();
        String result = new String(byteout.toByteArray());
        String expected = new StringBuilder()
                .append("    #    ").append(delimiter)
                .append("   ###   ").append(delimiter)
                .append("  #####  ").append(delimiter)
                .append(" ####### ").append(delimiter)
                .append("#########").append(delimiter)
                .append(delimiter)
                .toString();
        assertThat(result, is(expected));
        System.setOut(stdout);
    }
}