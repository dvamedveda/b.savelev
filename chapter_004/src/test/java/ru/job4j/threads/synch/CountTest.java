package ru.job4j.threads.synch;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

/**
 * Класс, реализующий тест для класса Count.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class CountTest {

    /**
     * Приватный класс, реализующий поток для инкремента значения в классе Count.
     */
    private class ThreadCount extends Thread {
        /**
         * Счетчик.
         */
        private final Count count;

        /**
         * Конструктор потока.
         *
         * @param count объект счетчика.
         */
        private ThreadCount(Count count) {
            this.count = count;
        }

        public void run() {
            try {
                for (int i = 0; i < 500; i++) {
                    this.count.increment();
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Проверяется ситуация, когда значение одного счетчика увеличивается из двух разных потоков.
     *
     * @throws InterruptedException
     */
    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        final Count count = new Count();
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        first.start();
        second.start();
        first.join();
        second.join();
        Assert.assertThat(count.getValue(), is(1000));
    }
}