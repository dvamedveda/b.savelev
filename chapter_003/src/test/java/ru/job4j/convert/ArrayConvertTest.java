package ru.job4j.convert;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;

/**
 * Класс содержащий тесты класса ArraysConvert.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class ArrayConvertTest {

    /**
     * Тест проверяющий конвертацию двумерного массива в список.
     */
    @Test
    public void whenArrayToListThenSuccess() {
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        ArrayConvert ac = new ArrayConvert();
        List<Integer> result = ac.toList(array);
        List<Integer> expected = new ArrayList<>();
        for (int count = 1; count <= 9; count++) {
            expected.add(count);
        }
        Assert.assertThat(expected, IsIterableContainingInAnyOrder.containsInAnyOrder(result.toArray()));
    }

    /**
     * Тест проверяющий конвертацию списка в двумерный массив, когда значения полностью заполняют матрицу.
     */
    @Test
    public void whenListWithModZeroToArrayThenSuccess() {
        List<Integer> ourList = new ArrayList<>();
        for (int count = 1; count <= 9; count++) {
            ourList.add(count);
        }
        int[][] expected = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        ArrayConvert ac = new ArrayConvert();
        int[][] result = ac.toArray(ourList, 3);
        Assert.assertThat(result, is(expected));
    }

    /**
     * Тест проверяющий конвертацию списка в двумерный массив, когда значения не полностью заполняют матрицу.
     */
    @Test
    public void whenListWithModNotZeroToArrayThreeRowsThenFourCols() {
        List<Integer> ourList = new ArrayList<>();
        for (int count = 1; count <= 11; count++) {
            ourList.add(count);
        }
        int[][] expected = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 0}};
        ArrayConvert ac = new ArrayConvert();
        int[][] result = ac.toArray(ourList, 3);
        Assert.assertThat(result, is(expected));
    }

    /**
     * Тест проверяющий конвертацию списка в двумерный массив, когда значения не полностью заполняют матрицу.
     */
    @Test
    public void whenListWithModNotZeroToArrayFourRowsThenFiveCols() {
        List<Integer> ourList = new ArrayList<>();
        for (int count = 1; count <= 17; count++) {
            ourList.add(count);
        }
        int[][] expected = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 0, 0, 0}};
        ArrayConvert ac = new ArrayConvert();
        int[][] result = ac.toArray(ourList, 4);
        Assert.assertThat(result, is(expected));
    }
}