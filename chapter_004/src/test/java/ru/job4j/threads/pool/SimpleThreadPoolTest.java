package ru.job4j.threads.pool;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;

/**
 * Класс, реализующий тесты для класса ru.job4j.threads.pool.SimpleThreadPool.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class SimpleThreadPoolTest {

    /**
     * Здесь проверяем, что пул корректно обрабатывает очередь, не теряя задачи.
     * При этом работа идет в заданное количество потоков.
     * Для подтверждения выводит задачи на печать.
     *
     * @throws InterruptedException если добавление задачи в очередь было прервано.
     */
    @Test
    public void whenAddHundredTasksThenRunsSuccess() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleThreadPool pool = new SimpleThreadPool();
        for (int count = 0; count < 100; count++) {
            pool.work(new PrintTask(count, buffer));
        }
        Thread.sleep(5000);
        List<Integer> expected = new ArrayList<>();
        IntStream.range(0, 100).forEach((i) -> expected.add(i));
        buffer.sort((Integer::compareTo));
        Assert.assertThat(buffer, is(expected));
    }

    /**
     * Проверяем, что при завершении работы пула потоков падает исключение.
     *
     * @throws InterruptedException при прерывании работы пула или добавления задачи в очередь.
     */
    @Test(expected = InterruptedException.class)
    public void whenInterruptPoolThenGetException() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleThreadPool pool = new SimpleThreadPool();
        for (int count = 0; count < 100; count++) {
            pool.work(new PrintTask(count, buffer));
        }
        pool.shutdown();
    }

    /**
     * Внутренний класс, описывающий Runnable задачу для тестов.
     */
    private class PrintTask implements Runnable {

        private Object object;
        private CopyOnWriteArrayList buffer;

        public PrintTask(Object o, CopyOnWriteArrayList buffer) {
            this.object = o;
            this.buffer = buffer;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": " + this.object);
            this.buffer.add(object);
        }
    }
}

