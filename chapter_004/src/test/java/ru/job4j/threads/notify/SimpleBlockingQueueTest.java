package ru.job4j.threads.notify;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

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
                try {
                    queue.put(i);
                    System.out.println(String.format("Queue put: %d", i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread consumer1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Assert.assertThat(queue.poll(), is(i));
                    System.out.println(String.format("Queue poll: %d", i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.start();
        consumer1.start();
        producer.join();
        consumer1.join();
    }

    /**
     * Проверка того, что все данные, отправленные в очередь, из нее получаются.
     *
     * @throws InterruptedException в случае, если поток ждал, но был прерван.
     */
    @Test
    public void whenProducerGivesElementsThenComsumerGetsItAll() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(
                () -> IntStream.range(0, 5).forEach(
                        (i) -> {
                            try {
                                queue.put(i);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                )
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        Assert.assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}