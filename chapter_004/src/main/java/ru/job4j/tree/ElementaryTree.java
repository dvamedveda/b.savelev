package ru.job4j.tree;

import java.util.Optional;

/**
 * Интерфейс, описывающий элементарное дерево.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public interface ElementaryTree<E extends Comparable<E>> extends Iterable<E> {

    /**
     * Добавление элемента в дерево.
     * @param parent родитель
     * @param child потомок
     * @return результат добавления элемента.
     */
    boolean add(E parent, E child);

    /**
     * Поиск в дереве по значению.
     * @param value значение.
     * @return Optional-объект, содержащий значение или null.
     */
    Optional<Node<E>> findBy(E value);
}