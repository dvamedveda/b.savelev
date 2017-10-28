package ru.job4j.array;

/**
 * Класс Turn, который содержит метод для разворачивания массива в обратную сторону.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Turn {

    /**
     * Метод, разворачивающий переданный ему массив в обратном направлении.
     *
     * @param array массив, который требуется развернуть, int[].
     * @return развернутый в обратную сторону массив, int[].
     */
    public int[] back(int[] array) {
        int temp;
        int[] result;
        if (array.length % 2 != 0) {
            for (int index = 0; index < (array.length - 1) / 2; index++) {
                temp = array[index];
                array[index] = array[array.length - 1 - index];
                array[array.length - 1 - index] = temp;
            }
            result = array;
        } else {
            for (int index = 0; index < array.length / 2; index++) {
                temp = array[index];
                array[index] = array[array.length - 1 - index];
                array[array.length - 1 - index] = temp;
            }
            result = array;
        }
        return result;
    }
}