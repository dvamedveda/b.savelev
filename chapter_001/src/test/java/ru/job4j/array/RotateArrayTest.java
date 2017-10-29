package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса RotateArray.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class RotateArrayTest {

    /**
     * Тест метода RotateArray.rotate().
     * Проверяет, что метод правильно поворачивает матрицу 2х2.
     */
    @Test
    public void whenRotateArrayTwoRowsTwoColsThenItRotate() {
        int[][] array = {{7, 8}, {1, 3}};
        RotateArray rotateArray = new RotateArray();
        int[][] result = rotateArray.rotate(array);
        int[][] expected = {{1, 7}, {3, 8}};
        assertThat(result, is(expected));
    }

    /**
     * Тест метода RotateArray.rotate().
     * Проверяет, что метод правильно поворачивает матрицу 3х3.
     */
    @Test
    public void whenRotateArrayThreeRowsThreeColsThenItRotate() {
        int[][] array = {{7, 8, 4}, {1, 3, 2}, {9, 2, 6}};
        RotateArray rotateArray = new RotateArray();
        int[][] result = rotateArray.rotate(array);
        int[][] expected = {{9, 1, 7}, {2, 3, 8}, {6, 2, 4}};
        assertThat(result, is(expected));
    }
}