package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Интерфейс, описывающий ноду элементарного дерева.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Node<E extends Comparable<E>> {

    /**
     * Список потомков данной ноды.
     */
    private final List<Node<E>> children = new ArrayList<>();

    /**
     * Значение данной ноды.
     */
    private final E value;

    /**
     * Конструктор ноды.
     * @param value значение для хранения.
     */
    public Node(final E value) {
        this.value = value;
    }

    /**
     * Добавление потомка данной ноды в дерево.
     * @param child
     */
    public void add(Node<E> child) {
        this.children.add(child);
    }

    /**
     * Получить список потомков данной ноды.
     * @return список потомков.
     */
    public List<Node<E>> leaves() {
        return this.children;
    }

    /**
     * Сравнить значение данной ноды с переданным значением.
     * @param that
     * @return
     */
    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }

    /**
     * Получить значение данной ноды.
     * @return значение ноды.
     */
    public E getValue() {
        return this.value;
    }
}