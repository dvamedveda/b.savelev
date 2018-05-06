package ru.job4j.list;

/**
 * Класс, реализующий ноду для примитивного связного списка с параметризуемым типом.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Node<T> {
    private T value;
    private Node<T> next;

    /**
     * Конструктор ноды.
     *
     * @param value данные для хранения в ноде.
     */
    public Node(T value) {
        this.value = value;
    }

    /**
     * Установка для ноды следующей ноды.
     *
     * @param node
     */
    public void setNext(Node<T> node) {
        this.next = node;
    }

    /**
     * Геттер следующей ноды.
     * @return поле next
     */
    public Node<T> getNext() {
        return this.next;
    }

    /**
     * Проверка списка на наличие зацикленностей.
     *
     * @param first нода, с которой нужно начать.
     * @return нода зациклена или нет.
     */
    public boolean hasCycle(Node<T> first) {
        boolean result = false;
        Node slow = first, fast = first;
        while (true) {
            slow = slow.getNext();
            if (fast.getNext() != null) {
                fast = fast.getNext().getNext();
            } else {
                break;
            }
            if (fast == null) {
                break;
            }
            if (slow == fast) {
                result = true;
                break;
            }

        }
        return result;
    }
}