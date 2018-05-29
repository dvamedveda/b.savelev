package ru.job4j.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс, реализующий множество с использованием хэш таблицы на основе массива.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class SimpleHashSet<T> implements Iterable<T> {

    /**
     * Хранилище объектов.
     */
    private Object[] store = new Object[16];

    /**
     * Коэффициент заполнения множества
     */
    private final double loadFactor = 0.75;

    /**
     * Текущая вместительность множества.
     */
    private int capacity = this.store.length;

    /**
     * Порог, при котором множество нужно увеличивать.
     */
    private int threshold = (int) (capacity * loadFactor);

    /**
     * Добавление элемента во множество.
     *
     * @param data данные для добавления.
     * @return результат добавления.
     */
    public boolean add(T data) {
        boolean added = false;
        if (this.size() >= threshold) {
            this.resize();
        }
        int position = data.hashCode() % capacity;
        if (this.store[position] == null) {
            this.store[position] = data;
            added = true;
        } else {
            while (!added) {
                if (this.store[position].equals(data)) {
                    this.store[position] = data;
                    break;
                }
                position++;
                if (this.store[position] == null) {
                    this.store[position] = data;
                    added = true;
                }
            }
        }
        return added;
    }

    /**
     * Проверка, содержится ли элемент во множестве.
     *
     * @param data данные, которые нужно найти.
     * @return результат проверки.
     */
    public boolean contains(T data) {
        boolean isContains = false;
        int position = data.hashCode() % capacity;
        for (int counter = position; counter < this.store.length; counter++) {
            if (this.store[position] == null) {
                break;
            } else if (this.store[position].equals(data)) {
                isContains = true;
                break;
            }
        }
        return isContains;
    }

    /**
     * Удаление элемента из множества.
     *
     * @param data данные, которые нужно удалить.
     * @return результат удаления.
     */
    public boolean remove(T data) {
        boolean result = false;
        if (this.contains(data)) {
            int sPosition = data.hashCode() % capacity;
            for (int index = sPosition; !result; index++) {
                if (this.store[index].equals(data)) {
                    result = true;
                    this.store[index] = null;
                }
            }
        }
        return result;
    }

    /**
     * Получение размера множества.
     *
     * @return количество элементов во множестве.
     */
    public int size() {
        int count = 0;
        for (Object object : this.store) {
            if (object != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * Вспомогательный метод для увеличения емкости множества.
     */
    private void resize() {
        Object[] temp = this.store;
        this.store = new Object[temp.length * 2];
        System.arraycopy(temp, 0, this.store, 0, temp.length);
        this.capacity = this.store.length;
        this.threshold = (int) (capacity * loadFactor);
    }

    /**
     * Публичный метод для получения итератора по множеству.
     *
     * @return новый объект итератора.
     */
    @Override
    public Iterator<T> iterator() {
        return new SimpleHashSetIterator();
    }

    /**
     * Реализуем итератор.
     */
    private class SimpleHashSetIterator implements Iterator<T> {

        private Object[] storage = store;
        private int index = 0;

        /**
         * Флажок наличия следующего элемента.
         *
         * @return есть следующий элемент или нет.
         */
        @Override
        public boolean hasNext() {
            boolean result = false;
            for (int current = index; current < this.storage.length; current++) {
                if (this.storage[current] != null) {
                    result = true;
                    break;
                }
            }
            return result;
        }

        /**
         * Получение следующего элемента из итератора.
         *
         * @return следующий элемент.
         */
        @Override
        public T next() {
            T result = null;
            if (this.hasNext()) {
                for (int pointer = index; pointer < this.storage.length; pointer++) {
                    this.index++;
                    if (this.storage[pointer] != null) {
                        result = (T) this.storage[pointer];
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