package ru.job4j.array;

/**
 * Класс BubbleSort, который содержит метод для сортировки массива методом пузырька.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class BubbleSort {

    /**
     * Метод, сортирующий переданный ему массив способом пузырька.
     *
     * @param array массив для сортировки, int[].
     * @return отсортированный массив, int[].
     */
    public int[] sort(int[] array) {
        int temp;
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }
}