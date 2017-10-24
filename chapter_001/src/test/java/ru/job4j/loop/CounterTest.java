package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса Counter.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class CounterTest {

    /**
     * Тест, проверяющий работу метода Counter.add().
     * Проверяется известная сумма четных чисел в диапазоне 1-10.
     */
    @Test
    public void whenGiveIntervalFromOneToTenThenGetThirty() {
        Counter counter = new Counter();
        int result = counter.add(1, 10);
        assertThat(result, is(30));
    }
}