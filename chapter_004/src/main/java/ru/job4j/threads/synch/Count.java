package ru.job4j.threads.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Класс, иллюстрирующий работу инспекций jcip.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
@ThreadSafe
public class Count {

    /**
     * Разделяемый между потоками ресурс.
     */
    @GuardedBy("this")
    private volatile int value;

    /**
     * Метод, выполняющий изменение разделяемого ресурса.
     */
    public synchronized void increment() {
        this.value++;
    }

    /**
     * Метод, имеющий доступ к разделяемому ресурсу.
     *
     * @return значение переменной.
     */
    public synchronized int getValue() {
        return this.value;
    }
}