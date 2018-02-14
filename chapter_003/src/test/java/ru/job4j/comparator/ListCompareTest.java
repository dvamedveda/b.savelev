package ru.job4j.comparator;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Пакет, содержащий тесты для проверки класса ListCompare.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class ListCompareTest {

    /**
     * Тест проверяет сравнение одинаковых по длине списков.
     */
    @Test
    public void whenLeftAndRightEqualsThenZero() {
        ListCompare compare = new ListCompare();
        int rst = compare.compare(
                Arrays.asList(1, 2, 3),
                Arrays.asList(1, 2, 3)
        );
        assertThat(rst, is(0));
    }

    /**
     * Тест проверяет сравнение не одинаковых по длине списков, где меньше - левый.
     */
    @Test
    public void whenSmallLeftLessRightThenMinus() {
        ListCompare compare = new ListCompare();
        int rst = compare.compare(
                Arrays.asList(1),
                Arrays.asList(1, 2, 3)
        );
        assertThat(rst, is(-1));
    }

    /**
     * Тест проверяет сравнение одинаковых по длине списков, где меньше - левый.
     */
    @Test
    public void whenLeftLessRightThenMinus() {
        ListCompare compare = new ListCompare();
        int rst = compare.compare(
                Arrays.asList(1, 1),
                Arrays.asList(1, 2)
        );
        assertThat(rst, is(-1));
    }

    /**
     * Тест проверяет сравнение одинаковых по длине списков, где меньше - правый.
     */
    @Test
    public void whenLeftGreatRightThenPlus() {
        ListCompare compare = new ListCompare();
        int rst = compare.compare(
                Arrays.asList(1, 2),
                Arrays.asList(1, 1)
        );
        assertThat(rst, is(1));
    }

    /**
     * Тест проверяет сравнение не одинаковых по длине списков, где меньше - правый.
     */
    @Test
    public void whenSmallLeftGreatRightThenPlus() {
        ListCompare compare = new ListCompare();
        int rst = compare.compare(
                Arrays.asList(10),
                Arrays.asList(2, 3, 4)
        );
        assertThat(rst, is(1));
    }
}
