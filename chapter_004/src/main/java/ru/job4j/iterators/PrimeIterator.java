package ru.job4j.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * В этом классе реализуется итератор простых чисел для массива.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class PrimeIterator implements Iterator<Integer> {

    /**
     * Итерируемый массив.
     */
    private int[] array;

    /**
     * Текущий индекс.
     */
    private int index = 0;

    /**
     * Конструктор.
     *
     * @param numbers массив, который нужно перебрать.
     */
    public PrimeIterator(final int[] numbers) {
        this.array = numbers;
    }

    /**
     * Проверяем, есть ли еще простые числа в массиве.
     *
     * @return есть или нет.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        if (this.index < this.array.length - 1) {
            for (int current = index; current < array.length; current++) {
                if (isPrime(this.array[current])) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Возвращает простое число при вызове.
     *
     * @return следующее простое число.
     * @throws NoSuchElementException если простых чисел в массиве больше нет.
     */
    @Override
    public Integer next() {
        int result = 0;
        for (int current = index; current < this.array.length; current++) {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            if (isPrime(this.array[current])) {
                result = this.array[current];
                index++;
                break;
            }
            index++;
        }
        return result;
    }

    /**
     * Проверка числа, является ли оно простым.
     *
     * @param number число для проверки.
     * @return true, если простое, иначе - false.
     */
    private boolean isPrime(int number) {
        boolean result = true;
        if (number < 2) {
            result = false;
        } else {
            for (int divider = 2; divider * divider <= number; divider++) {
                if (number % divider == 0) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}