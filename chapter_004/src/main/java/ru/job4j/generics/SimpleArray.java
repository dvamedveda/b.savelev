package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс, реализующий контейнер SimpleArray с параметризуемым типом, использующий обычный массив.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class SimpleArray<T> implements Iterable<T> {

    /**
     * Здесь хранится массив с данными
     */
    private Object[] storeArray = new Object[10];

    /**
     * Добавление элемента в массив.
     * Объект добавляется в первую свободную ячейку массива.
     * Если свободных ячеек нет - массив увеличивается вдвое.
     *
     * @param model объект для добавления.
     */
    public void add(T model) {
        int index = this.nextIndex();
        this.storeArray[index] = (Object) model;
    }

    /**
     * Установить значение ячейки массива по индексу на заданное.
     *
     * @param index индекс, по которому нужно заменить объект.
     * @param model объект, который нужно разместить в массиве.
     */
    public void set(int index, T model) {
        this.storeArray[index] = model;
    }

    /**
     * Удалить элемент массива по заданному индексу.
     *
     * @param index индекс, по которому удаляется элемент.
     */
    public void delete(int index) {
        this.storeArray[index] = null;
    }

    /**
     * Получить элемент массива по заданному индексу.
     *
     * @param index индекс ячейки.
     * @return значение заданной ячейки
     */
    public T get(int index) {
        T result = null;
        if (this.storeArray[index] != null) {
            result = (T) this.storeArray[index];
        }
        return result;
    }

    /**
     * Получить длину массива с данными.
     *
     * @return длина массива.
     */
    public int length() {
        return this.storeArray.length;
    }

    /**
     * Выдает первый свободный индекс в массиве для добавления элемента.
     * Если свободных индексов нет, размер массива удваивается и возвращается первый свободный индекс.
     *
     * @return свободный индекс.
     */
    private int nextIndex() {
        int result = -1;
        for (int index = 0; index < this.storeArray.length; index++) {
            if (this.storeArray[index] == null) {
                result = index;
                break;
            }
            if (index == this.storeArray.length - 1 && this.storeArray[index] != null) {
                Object[] temp = this.storeArray;
                this.storeArray = new Object[this.storeArray.length * 2];
                System.arraycopy(temp, 0, this.storeArray, 0, temp.length);
                result = index + 1;
            }
        }
        return result;
    }

    /**
     * Отдаем итератор для SimpleArray
     *
     * @return объект итератора.
     */
    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator();
    }

    /**
     * Здесь реализуется итератор по SimpleArray.
     */
    private class SimpleArrayIterator implements Iterator<T> {

        private int index = 0;
        private Object[] simpleArray = storeArray;

        /**
         * Есть следующий элемент или нет.
         *
         * @return да/нет.
         */
        @Override
        public boolean hasNext() {
            boolean result = false;
            for (int current = index; current < this.simpleArray.length; current++) {
                if (this.simpleArray[current] != null) {
                    result = true;
                    break;
                }
            }
            return result;
        }

        /**
         * Получить следующий элемент массива, если он есть, иначе исключение.
         *
         * @return следующий элемент.
         */
        @Override
        public T next() {
            T result = null;
            if (this.hasNext()) {
                for (int currentIndex = index; currentIndex < this.simpleArray.length; currentIndex++) {
                    if (this.simpleArray[currentIndex] != null) {
                        result = (T) this.simpleArray[currentIndex];
                        this.index++;
                        break;
                    }
                }
            } else {
                throw new NoSuchElementException();
            }
            return result;
        }
    }
}