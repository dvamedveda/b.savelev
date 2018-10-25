package ru.job4j.threads.synch.wrapper;

import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

/**
 * Конкретный декоратор для списка DynamicList.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
@ThreadSafe
public class ThreadSafeDynamicList<T> extends DynamicListWrapper<T> {

    public ThreadSafeDynamicList() {
        super();
    }

    @Override
    public synchronized void add(T model) {
        super.add(model);
    }

    @Override
    public synchronized T get(int index) {
        return super.get(index);
    }

    @Override
    public synchronized void set(int index, T model) {
        super.set(index, model);
    }

    @Override
    public synchronized void delete(int index) {
        super.delete(index);
    }

    @Override
    public synchronized int length() {
        return super.length();
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return super.iterator();
    }
}