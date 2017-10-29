package ru.job4j.array;

/**
 * Класс RotateArray, который содержит методы для работы с массивами.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class RotateArray {

    /**
     * Метод, который поворачивает переданную ему матрицу на 90 градусов по часовой стрелке.
     *
     * @param array массив, который нужно повернуть, int[][].
     * @return повернутый массив, int[][].
     */
    public int[][] rotate(int[][] array) {
        int[][] result = new int[array.length][array[0].length];
        for (int i = 0; i <= array.length - 1; i++) {
            for (int j = 0; j <= array.length - 1; j++) {
                result[i][array.length - 1 - j] = array[j][i];
            }
        }
        return result;
    }
}