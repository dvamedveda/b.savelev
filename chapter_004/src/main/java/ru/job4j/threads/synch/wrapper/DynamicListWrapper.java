package ru.job4j.threads.synch.wrapper;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.DynamicList;

import java.util.Iterator;

/**
 * Базовый декоратор(потокобезопасный) для списка DynamicList, реализующий интерфейсы Iterable, DynamicListInterface.
 * Также реализует fail-safe итерацию по списку.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
@ThreadSafe
public class DynamicListWrapper<T> implements Iterable<T>, DynamicListInterface<T> {

    /**
     * Хранилище объектов.
     */
    @GuardedBy("this")
    private DynamicList<T> dynamicList;

    /**
     * Конструктор.
     */
    DynamicListWrapper() {
        this.dynamicList = new DynamicList<>();
    }

    /**
     * Получение объекта fail-safe итератора.
     *
     * @return объект итератора.
     */
    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.dynamicList).iterator();
    }

    /**
     * Добавление элементов в список.
     *
     * @param model добавляемый элемент.
     */
    @Override
    public synchronized void add(T model) {
        this.dynamicList.add(model);
    }

    /**
     * Получение элемента списка по индексу в списке.
     *
     * @param index индекс элемента
     * @return элемент
     */
    public synchronized T get(int index) {
        return this.dynamicList.get(index);
    }

    /**
     * Изменить элемент по индексу.
     *
     * @param index индекс элемента в списке.
     * @param model новый объект
     */
    public synchronized void set(int index, T model) {
        this.dynamicList.set(index, model);
    }

    /**
     * Удаление элемента из списка по индексу.
     *
     * @param index индекс удаляемого элемента.
     */
    public synchronized void delete(int index) {
        this.dynamicList.delete(index);
    }

    /**
     * Получить длину списка.
     *
     * @return длина списка.
     */
    public synchronized int length() {
        return this.dynamicList.length();
    }

    /**
     * Создать снапшот для fail-safe итерации по списку.
     *
     * @param dynamicList объект для итерации.
     * @return объект-снапшот.
     */
    private DynamicList<T> copy(DynamicList<T> dynamicList) {
        DynamicList<T> result = new DynamicList<>();
        for (T each : dynamicList) {
            result.add(each);
        }
        return result;
    }
}