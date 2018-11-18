package ru.job4j.threads.pool;

import ru.job4j.threads.notify.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс, реализующий простой пул потоков.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class SimpleThreadPool {
    /**
     * Флаг прерывания работы пула
     */
    private boolean interrupted = false;

    /**
     * Размер пула потоков.
     * Устанавливается по количеству ядер процессора в системе.
     */
    private final int poolSize = Runtime.getRuntime().availableProcessors();

    /**
     * Список потоков в пуле.
     */
    private final List<Thread> threads = new LinkedList<>();

    /**
     * Очередь задач для пула потоков.
     */
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    /**
     * Конструктор пула.
     * Сразу запускает на выполнение все потоки.
     */
    public SimpleThreadPool() {
        for (int i = 0; i < poolSize; i++) {
            threads.add(new Thread(() -> {
                try {
                    while (!this.interrupted) {
                        this.tasks.poll().run();
                    }
                    Thread.currentThread().interrupt();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    /**
     * Добавить задачу в очередь для обработки.
     *
     * @param task задача для обработки
     * @throws InterruptedException при прерывании процесса добавления задачи в пул.
     */
    public void work(Runnable task) throws InterruptedException {
        this.tasks.put(task);
    }

    /**
     * Остановить работу пула.
     * Выставляет флажок, на который ориентируются все потоки в пуле.
     *
     * @throws InterruptedException для оповещения что работа пула потоков завершена.
     */
    public void shutdown() throws InterruptedException {
        interrupted = true;
        throw new InterruptedException("Pool thread stopped: " + Thread.currentThread().getName());
    }
}
