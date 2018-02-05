package ru.job4j.convert;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс содержащий методы для конвертации массива в список и наоборот.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class ArrayConvert {

    /**
     * Метод конвертирует двумерный массив в список.
     *
     * @param array двумерный список, который требуется сконвертировать.
     * @return сконвертированный список.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> result = new ArrayList<>();
        for (int[] outer : array) {
            for (int inner : outer) {
                result.add(inner);
            }
        }
        return result;
    }

    /**
     * Метод конвертирует список в двумерный массив.
     * Если значения не полностью заполняют матрицу,
     * то остальные значения матрицы заполняются нулями.
     *
     * @param list список, который требуется сконвертировать.
     * @param rows число строк, которое должен содержать двумерный массив.
     * @return сконвертированный двумерный массив
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int p = (list.size() / rows);
        int cols = list.size() % rows == 0 ? list.size() / rows : p + 1;
        int[][] result = new int[rows][cols];
        int inner = 0;
        int outer = 0;
        for (int value : list) {
            if (inner == cols) {
                inner = 0;
                outer++;
            }
            result[outer][inner] = value;
            inner++;
        }
        return result;
    }
}