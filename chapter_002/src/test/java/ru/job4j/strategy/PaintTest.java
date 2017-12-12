package ru.job4j.strategy;

import org.junit.After;
import org.junit.Before;
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
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream byteout = new ByteArrayOutputStream();
    private final String delimiter = System.lineSeparator();

    /**
     * Устанавливаем новый поток вывода.
     */
    @Before
    public void loadByteOut() {
        System.out.println("Executing before test...");
        System.setOut(new PrintStream(this.byteout));
    }

    /**
     * Возвращаем стандартный поток вывода.
     */
    @After
    public void loadStandartOut() {
        System.setOut(stdout);
        System.out.println("Executing after test...");
    }

    /**
     * Тест, проверяющий вывод на консоль квадрата при использовании класса Paint и передачи ему квадрата.
     */
    @Test
    public void whenUseSquareThenStrategyDrawsSquare() {
        new Paint(new Square()).drawShape();
        String result = new String(this.byteout.toByteArray());
        String expected = new StringBuilder()
                .append("#########").append(this.delimiter)
                .append("#########").append(this.delimiter)
                .append("#########").append(this.delimiter)
                .append("#########").append(this.delimiter)
                .append("#########").append(this.delimiter)
                .append(this.delimiter)
                .toString();
        assertThat(result, is(expected));
    }

    /**
     * Тест, проверяющий вывод на консоль треугольника при использовании класса Paint и передачи ему треугольника.
     */
    @Test
    public void whenUseTrinagleThenStrategyDrawsTriangle() {
        new Paint(new Triangle()).drawShape();
        String result = new String(this.byteout.toByteArray());
        String expected = new StringBuilder()
                .append("    #    ").append(this.delimiter)
                .append("   ###   ").append(this.delimiter)
                .append("  #####  ").append(this.delimiter)
                .append(" ####### ").append(this.delimiter)
                .append("#########").append(this.delimiter)
                .append(this.delimiter)
                .toString();
        assertThat(result, is(expected));
    }
}