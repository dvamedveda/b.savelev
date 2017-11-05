package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса BubbleSort.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class BubbleSortTest {

    /**
     * Тест метода BubbleSort.sort().
     * Проверяет сортировку массива из 10 чисел.
     */
    @Test
    public void whenSortTenNumbersArrayThenSortIt() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] result = bubbleSort.sort(new int[]{2, 5, 0, 7, 4, 8, 1, 3, 6, 9});
        int[] expected = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertThat(result, is(expected));
    }

    /**
     * Тест метода Bubble.sort().
     * Проверяет сортировку уже отсортированного массива из 10 чисел.
     */
    @Test
    public void whenSortSortedArrayThenNotSortIt() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] result = bubbleSort.sort(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        int[] expected = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertThat(result, is(expected));
    }
}