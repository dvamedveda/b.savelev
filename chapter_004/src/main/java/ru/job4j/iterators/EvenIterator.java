package ru.job4j.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * В этом классе реализуется итератор четных чисел для массива.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class EvenIterator implements Iterator<Integer> {

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
     * @param numbers массив, который нужно проитерировать.
     */
    public EvenIterator(final int[] numbers) {
        this.array = numbers;
    }

    /**
     * Проверяем, есть ли еще четные числа в массиве.
     *
     * @return есть или нет.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        if (this.index < this.array.length - 1) {
            for (int current = index; current < array.length; current++) {
                if (isEven(this.array[current])) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Возвращает четное число при вызову.
     *
     * @return следующее четное число.
     * @throws NoSuchElementException если четных чисел в массиве больше нет.
     */
    @Override
    public Integer next() {
        int result = 0;
        for (int current = index; current < this.array.length; current++) {
            if (isEven(this.array[current])) {
                result = this.array[current];
                index++;
                break;
            }
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            index++;
        }
        return result;

    }

    /**
     * Проверка числа, является ли оно четным
     *
     * @param number число для проверки.
     * @return четное или нечетное.
     */
    private boolean isEven(int number) {
        return number % 2 == 0;
    }
}