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
    private Object[] store = new Object[10];

    /**
     * Добавление элемента в массив.
     * Объект добавляется в первую свободную ячейку массива.
     * Если свободных ячеек нет - массив увеличивается вдвое.
     *
     * @param model объект для добавления.
     */
    public void add(T model) {
        int index = this.nextIndex();
        this.store[index] = (Object) model;
    }

    /**
     * Установить значение ячейки массива по индексу на заданное.
     *
     * @param index индекс, по которому нужно заменить объект.
     * @param model объект, который нужно разместить в массиве.
     */
    public void set(int index, T model) {
        this.store[index] = model;
    }

    /**
     * Удалить элемент массива по заданному индексу.
     * При удалении элемента часть массива справа от индекса перемещается на одну позицию влево.
     *
     * @param index индекс, по которому удаляется элемент.
     */
    public void delete(int index) {
        Object[] result = new Object[this.store.length];
        if (index == 0) {
            System.arraycopy(this.store, 1, result, 0, this.store.length - 1);
        } else if (index == this.store.length - 1) {
            System.arraycopy(this.store, 0, result, 0, this.store.length - 1);
        } else {
            System.arraycopy(this.store, 0, result, 0, index);
            System.arraycopy(this.store, index + 1, result, index, this.store.length - index - 1);
        }
        this.store = result;
    }

    /**
     * Получить элемент массива по заданному индексу.
     *
     * @param index индекс ячейки.
     * @return значение заданной ячейки
     */
    public T get(int index) {
        T result = null;
        if (this.store[index] != null) {
            result = (T) this.store[index];
        }
        return result;
    }

    /**
     * Получить длину массива с данными.
     *
     * @return длина массива.
     */
    public int length() {
        return this.store.length;
    }

    /**
     * Выдает первый свободный индекс в массиве для добавления элемента.
     * Если свободных индексов нет, размер массива удваивается и возвращается первый свободный индекс.
     *
     * @return свободный индекс.
     */
    private int nextIndex() {
        int result = -1;
        for (int index = 0; index < this.store.length; index++) {
            if (this.store[index] == null) {
                result = index;
                break;
            }
            if (index == this.store.length - 1 && this.store[index] != null) {
                Object[] temp = this.store;
                this.store = new Object[this.store.length * 2];
                System.arraycopy(temp, 0, this.store, 0, temp.length);
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
        private Object[] array = store;

        /**
         * Есть следующий элемент или нет.
         *
         * @return да/нет.
         */
        @Override
        public boolean hasNext() {
            boolean result = false;
            for (int current = index; current < this.array.length; current++) {
                if (this.array[current] != null) {
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
                for (int currentIndex = index; currentIndex < this.array.length; currentIndex++) {
                    if (this.array[currentIndex] != null) {
                        result = (T) this.array[currentIndex];
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