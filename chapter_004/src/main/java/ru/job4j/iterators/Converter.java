package ru.job4j.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс, реализующий конвертер итератора итераторов чисел в итератор чисел.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Converter {

    /**
     * Метод, выполняющий конвертацию.
     * В качестве результата возвращается анонимный класс-итератор.
     *
     * @param iterators итератор итераторов чисел.
     * @return итератор чисел.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> iterators) {
        return new Iterator<Integer>() {

            private Iterator<Integer> currentIterator = iterators.next();

            /**
             * Есть ли следующие числа в итераторе итераторов - проверяем это.
             */
            @Override
            public boolean hasNext() {
                boolean result = false;
                if (this.currentIterator.hasNext()) {
                    result = true;
                } else {
                    while (iterators.hasNext()) {
                        this.currentIterator = iterators.next();
                        if (this.currentIterator.hasNext()) {
                            result = true;
                            break;
                        }
                    }
                }
                return result;
            }

            /**
             * Возвращаем следующие число из итератора итераторов.
             * @return следующее число.
             */
            @Override
            public Integer next() {
                int result = 0;
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                if (this.currentIterator.hasNext()) {
                    result = this.currentIterator.next();
                } else if (this.currentIterator == null || !this.currentIterator.hasNext()) {
                    this.currentIterator = iterators.next();
                    result = this.currentIterator.next();
                }
                return result;
            }
        };
    }

}