package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * Класс, реализующий стек с параметризуемым типом, на базе связанного списка.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class SimpleStack<T> {

    /**
     * Непосредественно хранилище.
     */
    private LinkedContainer<T> store = new LinkedContainer<>();

    /**
     * Добавить новый элемент в стек.
     *
     * @param element новый элемент.
     */
    public void push(T element) {
        this.store.add(element);
    }

    /**
     * Получить последний элемент из стека, исключение, если элементов нет.
     *
     * @return последний элемент.
     */
    public T poll() {
        T result = null;
        if (this.store.length() > 0) {
            int last = this.store.length() - 1;
            result = this.store.get(last);
            this.store.delete(last);
        } else {
            throw new NoSuchElementException();
        }
        return result;
    }
}