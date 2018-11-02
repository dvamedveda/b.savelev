package ru.job4j.threads.notify;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

/**
 * Класс, реализующий тест для класса SimpleBlockingQueue.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class SimpleBlockingQueueTest {

    /**
     * Проверяем работу блокирующей очереди в двух потоках.
     * Если очередь полна, то продюсер ждет освобождения места в очереди.
     * Если очередь пуста, то консьюмер ждет появления места в очереди.
     *
     * @throws InterruptedException в случае прерывания программы, когда один из потоков ждет
     *                              появления места или элементов в очереди.
     */
    @Test
    public void whenQueueOverloadedThenProducerWaits() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                queue.put(i);
                System.out.println(String.format("Queue put: %d", i));
            }
        });
        Thread consumer1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Assert.assertThat(queue.poll(), is(i));
                System.out.println(String.format("Queue poll: %d", i));
            }
        });
        producer.start();
        consumer1.start();
        producer.join();
        consumer1.join();
    }
}