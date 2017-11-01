package ru.job4j.array;

import java.util.Arrays;

/**
 * Класс ArrayDuplicate, который содержит методы для работы с массивами.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class ArrayDuplicate {

    /**
     * Метод, удаляющий дубликаты из массива строк.
     *
     * @param array массив строк, из которого удаляются дубликаты, String[].
     * @return массив без дубликатов, String[].
     */
    public String[] remove(String[] array) {
        int unique = array.length;
        for (int outer = 0; outer < unique; outer++) {
            for (int inner = outer + 1; outer < unique; outer++) {
                if (array[outer].equals(array[inner])) {
                    array[inner] = array[unique - 1];
                    unique--;
                    inner--;
                }
            }
        }
        return Arrays.copyOf(array, unique);
    }
}