package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса ArrayUnion.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class ArrayUnionTest {

    /**
     * Тест метода ArrayUnion.union().
     * Проверяется правильное слияние двух массивов с четным числом элементов.
     */
    @Test
    public void whenUnionArraysLengthThreeAndLengthThreeThenResultIsArrayLengthSix() {
        ArrayUnion arrayUnion = new ArrayUnion();
        int[] firstArray = {1, 2, 3};
        int[] secondArray = {4, 5, 6};
        int[] result = arrayUnion.union(firstArray, secondArray);
        int[] expected = {1, 2, 3, 4, 5, 6};
        assertThat(result, is(expected));
    }

    /**
     * Тест метода ArrayUnion.union().
     * Проверяется правильное слияние двух массивов с четным и нечетным числом элементов.
     */
    @Test
    public void whenUnionArraysLengthThreeAndLengthFourThenResultIsArrayLengthSeven() {
        ArrayUnion arrayUnion = new ArrayUnion();
        int[] firstArray = {1, 2, 3};
        int[] secondArray = {4, 5, 6, 7};
        int[] result = arrayUnion.union(firstArray, secondArray);
        int[] expected = {1, 2, 3, 4, 5, 6, 7};
        assertThat(result, is(expected));
    }

    /**
     * Тест метода ArrayUnion.union().
     * Проверяется правильное слияние двух массивов, в которых есть одинаковые числа.
     */
    @Test
    public void whenUnionArraysWithEqualNumbersThenArrayUnionsRight() {
        ArrayUnion arrayUnion = new ArrayUnion();
        int[] firstArray = {1, 2, 3};
        int[] secondArray = {3, 4, 5, 6};
        int[] result = arrayUnion.union(firstArray, secondArray);
        int[] expected = {1, 2, 3, 3, 4, 5, 6};
        assertThat(result, is(expected));
    }
}