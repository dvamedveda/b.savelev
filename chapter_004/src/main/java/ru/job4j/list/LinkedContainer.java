package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс, реализующий контейнер с параметризуемым типом, на базе связанного списка.
 * Также реализовывает Iterable, также реализовывает fail-fast поведение при итерации.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class LinkedContainer<T> implements Iterable {

    /**
     * Счетчик модификаций.
     */
    private int modCount = 0;

    /**
     * Размер списка текущий.
     */
    private int size = 0;

    /**
     * Ссылка на первый элемента списка
     */
    private Node<T> first;

    /**
     * Ссылка на последний элемент списка.
     */
    private Node<T> last;

    /**
     * Связывание элементов при добавлении.
     * Если в списке только один элемент, то он одновременно и первый и последний.
     * Также в этом случае у него нет ни предыдущего, ни следующего элемента.
     * Инкапсулированный метод.
     *
     * @param data данные, которые должен хранить в себе элемент.
     */
    private void link(T data) {
        Node<T> newNode = new Node<>(null, data, null);
        Node<T> l = last;
        if (first == null && last == null) {
            first = newNode;
        } else {
            newNode.prev = l;
            l.next = newNode;
        }
        last = newNode;
        this.size++;
        this.modCount++;
    }

    /**
     * Связывание элементов при удалении.
     * @param node удаляемая нода
     */
    private void unlink(Node<T> node) {
        Node<T> p = node.prev;
        Node<T> n = node.next;
        if (p != null && n != null) {
            p.next = n;
            n.prev = p;
        } else if (p == null && n != null) {
            n.prev = null;
            this.first = n;
        } else if (p != null) {
            p.next = null;
            this.last = p;
        } else {
            this.first = null;
            this.last = null;
        }
        this.size--;
        this.modCount++;
    }

    /**
     * Удаление ноды из списка по индексу
     * @param index индекс, по которому происходит удаление из списка.
     */
    public void delete(int index) {
        if (index > 0 || index < this.size) {
            Node<T> current = first;
            for (int i = 0; i < this.size; i++) {
                if (index == i) {
                    unlink(current);
                    break;
                }
                current = current.next;
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Добавление элемента в список.
     * Публичный метод для общего пользования.
     *
     * @param data данные которые должны храниться
     *             в следующем добавленном в список элементе.
     */
    public void add(T data) {
        link(data);
    }

    /**
     * Получение данных из списка по позиции элемента.
     */
    public T get(int index) {
        T result = null;
        if (0 <= index && index < size) {
            Node<T> current = this.first;
            for (int count = 0; count < size; count++) {
                if (index == count) {
                    result = current.item;
                    break;
                } else {
                    current = current.next;
                }
            }
        }
        return result;
    }

    /**
     * Текущая длина списка.
     *
     * @return текущая длина списка.
     */
    public int length() {
        return this.size;
    }

    /**
     * Внутренний класс, реализующий ноду для хранения данных и внесения в список.
     *
     * @param <T> данные, которые нужно хранить в ноде.
     */
    private class Node<T> {
        private T item;
        private Node<T> prev;
        private Node<T> next;

        /**
         * Конструктор ноды.
         *
         * @param prev предыдущий элемент.
         * @param data данные для хранения.
         * @param next следующий элемент.
         */
        private Node(Node<T> prev, T data, Node<T> next) {
            this.item = data;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * Реализация итератора для LinkedContainer.
     */
    private class LinkedContainerIterator<T> implements Iterator<T> {

        /**
         * Снимок счетчика модификаций.
         */
        private int expectedModCount = modCount;

        /**
         * Текущий контейнер для итерации по нему.
         */
        private LinkedContainer container;

        /**
         * Стартовый индекс, с которого начинается итерация.
         */
        private int currentIndex = 0;

        /**
         * Конструктор итератора, в который прокидывается текущий контейнер, по которому итерируемся.
         *
         * @param container текущий контейнер.
         */
        private LinkedContainerIterator(LinkedContainer container) {
            this.container = container;
        }

        /**
         * Есть следующий элемент в списке.
         *
         * @return да или нет
         */
        @Override
        public boolean hasNext() {
            return container.get(currentIndex) != null;
        }

        /**
         * Получить следующий элемент.
         * Исключение NoSuchElementException если элементов больше нет
         * Исключение ConcurrentModificationException если список был изменен с создания итератора.
         *
         * @return следующий элемент.
         */
        @Override
        public T next() {
            T iterResult;
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            } else {
                if (hasNext()) {
                    iterResult = (T) container.get(currentIndex);
                    currentIndex++;
                } else {
                    throw new NoSuchElementException();
                }
            }
            return iterResult;
        }
    }

    /**
     * Создаем объект итератора.
     *
     * @return объект итератора.
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedContainerIterator<>(this);
    }
}