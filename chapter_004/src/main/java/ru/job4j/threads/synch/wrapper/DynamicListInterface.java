package ru.job4j.threads.synch.wrapper;

/**
 * Интерфейс, описывающий DynamicList с параметризуемым типом.
 * Нужен для того, чтобы просто переадресовать вызовы методов DynamicList в сам DynamicList,
 * где они уже реализованы.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public interface DynamicListInterface<T> {

    /**
     * Добавление элементов в список.
     *
     * @param model добавляемый элемент.
     */
    void add(T model);

    /**
     * Получение элемента списка по индексу в списке.
     *
     * @param index индекс элемента
     * @return элемент
     */
    T get(int index);

    /**
     * Изменить элемент по индексу.
     *
     * @param index индекс элемента в списке.
     * @param model новый объект
     */
    void set(int index, T model);

    /**
     * Удаление элемента из списка по индексу.
     *
     * @param index индекс удаляемого элемента.
     */
    void delete(int index);

    /**
     * Получить длину списка.
     *
     * @return длина списка.
     */
    int length();
}