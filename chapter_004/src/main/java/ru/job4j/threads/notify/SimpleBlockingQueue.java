package ru.job4j.threads.notify;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Реализация параметризованной блокирующей очереди лимитированного размера.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    /**
     * Очередь для хранения и получения элементов из нее.
     */
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    /**
     * Добавить данные в очередь.
     *
     * @param element объект данных
     */
    public void put(T element) {
        synchronized (this) {
            int limit = 2;
            try {
                while (this.queue.size() == limit) {
                    this.wait();
                }
                this.queue.offer(element);
                this.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Получить данные из очереди.
     *
     * @return объект данных или null, если очередь пуста.
     */
    public T poll() {
        T result = null;
        synchronized (this) {
            try {
                while (this.queue.size() == 0) {
                    this.wait();
                }
                result = this.queue.poll();
                this.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}