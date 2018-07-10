package ru.job4j.tree;

import java.util.*;

/**
 * Интерфейс, реализующий элементарное дерево.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class SimpleTree<E extends Comparable<E>> implements ElementaryTree {

    /**
     * Корень дерева.
     */
    private Node<E> root;

    /**
     * Конструктор дерева с инициализацией корня дерева.
     * @param root значение, являющееся корнем.
     */
    public SimpleTree(E root) {
        this.root = new Node<>(root);
    }

    /**
     * Добавление значений в дерево.
     * Дубликаты не добавляются.
     * @param parent родитель
     * @param child потомок
     * @return результат добавления - добавлено или нет.
     */
    @Override
    public boolean add(Comparable parent, Comparable child) {
        boolean result = false;
        boolean duplicate = false;
        Optional<Node<E>> parentNode = this.findBy(parent);
        if (parentNode.isPresent()) {
            for (Node<E> everyChild : parentNode.get().leaves()) {
                if (everyChild.eqValue((E) child)) {
                    duplicate = true;
                }
            }
            if (!duplicate) {
                Node<E> childNode = new Node<>((E) child);
                parentNode.get().add(childNode);
                result = true;
            }
        }
        return result;
    }

    /**
     * Поиск значения по дереву.
     * @param value значение.
     * @return опциональный объект, содержащий ноду или Null.
     */
    @Override
    public Optional<Node<E>> findBy(Comparable value) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue((E) value)) {
                result = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    /**
     * Определяем, является ли дерево бинарным (у каждого узла не более двух потомков).
     * @return дерево бинарное или нет
     */
    public boolean isBinary() {
        boolean result = true;
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Optional<Node<E>> node = this.findBy((E) iterator.next());
            if (node.isPresent() && node.get().leaves().size() > 2) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Получение итератора.
     * @return объект итератора.
     */
    @Override
    public Iterator iterator() {
        return new SimpleTreeIterator();
    }

    /**
     * Класс, реализующий итератор для дерева.
     * Обходит элементы дерева по очереди.
     */
    private class SimpleTreeIterator implements Iterator<E> {

        /**
         * Очередь элементов дерева.
         */
        private Queue<Node<E>> queue = new LinkedList<>();

        /**
         * Конструктор итератора с инициализацией очереди корнем дерева.
         */
        SimpleTreeIterator() {
            queue.offer(root);
        }

        /**
         * Следующий элемент есть или нет?
         * Проверяется, пуста ли очередь.
         * @return есть или нет элементы в очереди.
         */
        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        /**
         * Получение следующего элемента из очереди.
         * Если у полученного элемента есть потомки - они все добавляются в конец очереди.
         * @return следующий элемент.
         */
        @Override
        public E next() {
            E result;
            if (hasNext()) {
                Node<E> nextNode = queue.poll();
                if (!nextNode.leaves().isEmpty()) {
                    for (Node<E> child : nextNode.leaves()) {
                        queue.add(child);
                    }
                }
                result = nextNode.getValue();
            } else {
                throw new NoSuchElementException();
            }
            return result;
        }
    }
}