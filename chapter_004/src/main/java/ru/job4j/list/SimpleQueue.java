package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * Класс, реализующий очередь с параметризуемым типом, на базе связанного списка.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class SimpleQueue<T> {

    /**
     * Непосредественно хранилище.
     */
    private LinkedContainer<T> store = new LinkedContainer<>();

    /**
     * Добавить элемент в очередь.
     *
     * @param element новый элемент.
     */
    public void push(T element) {
        this.store.add(element);
    }

    /**
     * Получить первый элемент из очереди, исключение, если элементов нет.
     *
     * @return первый элемент.
     */
    public T poll() {
        T result = null;
        if (this.store.length() > 0) {
            result = this.store.get(0);
            this.store.delete(0);
        } else {
            throw new NoSuchElementException();
        }
        return result;
    }
}