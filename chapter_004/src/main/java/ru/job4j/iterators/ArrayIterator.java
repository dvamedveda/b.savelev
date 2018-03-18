package ru.job4j.iterators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * В этом классе реализуется итератор для двумерного массива.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class ArrayIterator implements Iterator<Integer> {

    /**
     * Итерируемый массив.
     */
    private final int[][] array;

    /**
     * Индексы текущего элемента для итерации.
     */
    private int row = 0;
    private int col = 0;

    /**
     * Конструктор, для инициализации массива.
     *
     * @param array
     */
    public ArrayIterator(int[][] array) {
        this.array = array;
    }

    /**
     * Проверка на наличие следующего элемента двумерного массива.
     *
     * @return есть следующий элемент или нет.
     */
    @Override
    public boolean hasNext() {
        return (row < this.array.length && col < this.array[row].length);
    }

    /**
     * Вернуть следующий элемент двумерного массива.
     *
     * @return следующий элемент массива.
     */
    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        } else {
            Integer result = this.array[row][col];
            if (col == this.array[row].length - 1) {
                col = 0;
                row++;
            } else {
                col++;
            }
            return result;
        }
    }
}