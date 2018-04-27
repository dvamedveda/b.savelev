package ru.job4j.list;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;

/**
 * Тесты класса SimpleQueue.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class SimpleQueueTest {

    /**
     * Проверяется успешное добавление и получение элемента.
     */
    @Test
    public void whenPushOneElementToQueueAndPollThenPollingSuccess() {
        SimpleQueue<Integer> iQueue = new SimpleQueue<>();
        iQueue.push(1);
        Assert.assertThat(iQueue.poll(), is(1));
    }

    /**
     * Проверяется добавление многих элементов, и затем получение их по принципу FIFO
     */
    @Test
    public void whenPushMoreElementToQueuekAndPollThenAlwaysPollingFirst() {
        SimpleQueue<Integer> iQueue = new SimpleQueue<>();
        iQueue.push(5);
        iQueue.push(4);
        iQueue.push(3);
        iQueue.push(2);
        iQueue.push(1);
        Assert.assertThat(iQueue.poll(), is(5));
        Assert.assertThat(iQueue.poll(), is(4));
        Assert.assertThat(iQueue.poll(), is(3));
        Assert.assertThat(iQueue.poll(), is(2));
        Assert.assertThat(iQueue.poll(), is(1));
    }

    /**
     * Проверяется выпадение исключения в случае получение элемента из пустой очереди.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenPollElementFromEmptyQueueThenGetException() {
        SimpleQueue<Integer> iQueue = new SimpleQueue<>();
        iQueue.push(1);
        iQueue.poll();
        iQueue.poll();
    }
}