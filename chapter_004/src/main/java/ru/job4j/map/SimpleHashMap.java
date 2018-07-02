package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс, реализующий HashMap с использованием хэш таблицы на основе массива.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class SimpleHashMap<K, V> implements Iterable {

    /**
     * Хранилище объектов.
     */
    private Object[] store = new Object[16];

    /**
     * Коэффициент загрузки хранилища.
     */
    private final double loadFactor = 0.75;

    /**
     * Текущая вместимость хранилища.
     */
    private int capacity = this.store.length;

    /**
     * Текущий порог загрузки хранилища, после которого размер хранилища будет увеличен.
     */
    private int threshold = (int) (capacity * loadFactor);

    /**
     * Вставка пар "ключ-значение" в Map.
     *
     * @param key   ключ пары.
     * @param value значение пары.
     * @return результат вставки. false, если при добавлении ключ уже есть(коллизии не разрешаются).
     */
    public boolean insert(K key, V value) {
        boolean added = false;
        if (this.size() >= threshold) {
            this.resize();
        }
        int position = getPosition(key);
        if (store[position] == null) {
            store[position] = new Node<>(key, value);
            added = true;
        }
        return added;
    }

    /**
     * Получение значения по ключу из Map.
     *
     * @param key ключ
     * @return значение, соответствующее ключу.
     */
    public V get(K key) {
        V result = null;
        int position = getPosition(key);
        if (this.store[position] != null) {
            Node<K, V> node = (Node<K, V>) this.store[position];
            result = node.getValue();
        }
        return result;
    }

    /**
     * Удаление пары из Map по ключу.
     *
     * @param key ключ
     * @return результат удаления пары.
     */
    public boolean delete(K key) {
        boolean result = false;
        int position = getPosition(key);
        if (this.store[position] != null) {
            this.store[position] = null;
            result = true;
        }
        return result;
    }

    /**
     * Метод для вычисления позиции(хеша) для ключа.
     *
     * @param key ключ.
     * @return позиция в хранилище.
     */
    private int getPosition(K key) {
        return key.hashCode() % capacity;
    }

    /**
     * Текущий размер Map.
     *
     * @return размер карты.
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
     * Метод для увеличения размера хранилища.
     * Вызывает перехеширование элементов в карте.
     */
    private void resize() {
        Object[] temp = this.store;
        this.store = new Object[temp.length * 2];
        this.capacity = this.store.length;
        this.threshold = (int) (capacity * loadFactor);
        rehash(temp, this.store);
    }

    /**
     * Метод для перехеширования карты.
     * Вызывается при изменении размеров карты.
     *
     * @param source исходное хранилище.
     * @param target новое, увеличенное, хранилище.
     */
    private void rehash(Object[] source, Object[] target) {
        for (Object item : source) {
            if (item != null) {
                Node<K, V> node = (Node<K, V>) item;
                int position = this.getPosition(node.getKey());
                target[position] = new Node<>(node.getKey(), node.getValue());
            }
        }
    }

    /**
     * Класс, представляющий объекты пар "ключ-значение".
     *
     * @param <K> ключ пары.
     * @param <V> значение пары.
     */
    class Node<K, V> {

        K originalKey;
        V data;

        /**
         * Конструктор пары.
         *
         * @param key   ключ пары.
         * @param value значение пары.
         */
        Node(K key, V value) {
            originalKey = key;
            data = value;
        }

        /**
         * Геттер.
         *
         * @return значение пары.
         */
        V getValue() {
            return data;
        }

        /**
         * Геттер.
         *
         * @return значение ключа.
         */
        K getKey() {
            return originalKey;
        }
    }

    /**
     * Получение нового итератора по карте.
     *
     * @return итератор по карте.
     */
    @Override
    public Iterator<Node> iterator() {
        return new SimpleHashMapIterator();
    }

    /**
     * Реализация итератора по карте.
     */
    private class SimpleHashMapIterator implements Iterator<Node> {

        /**
         * Копия хранилища.
         */
        private Object[] storage = store;

        /**
         * Индекс для курсора итератора.
         */
        private int index = 0;

        /**
         * Есть следующий элемент?
         *
         * @return есть или нет.
         */
        @Override
        public boolean hasNext() {
            boolean result = false;
            for (int pointer = index; pointer < this.storage.length; pointer++) {
                index = pointer;
                if (this.storage[pointer] != null) {
                    result = true;
                    break;
                }
            }
            return result;
        }

        /**
         * Получить следующий элемент.
         *
         * @return пара "ключ-значение".
         */
        @Override
        public Node<K, V> next() {
            Node<K, V> result;
            if (this.hasNext()) {
                result = (Node) this.storage[index];
                index++;
            } else {
                throw new NoSuchElementException();
            }
            return result;
        }
    }
}