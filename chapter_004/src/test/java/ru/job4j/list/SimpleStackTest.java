package ru.job4j.list;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;

/**
 * Тесты класса SimpleStack.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class SimpleStackTest {

    /**
     * Проверяется успешное добавление и получение одного элемента в стек.
     */
    @Test
    public void whenPushOneElementToStackAndPollThenPollingSuccess() {
        SimpleStack<Integer> iStack = new SimpleStack<>();
        iStack.push(1);
        Assert.assertThat(iStack.poll(), is(1));
    }

    /**
     * Проверяется добавление многих элементов в стек и последовательного получения их по принципу LIFO.
     */
    @Test
    public void whenPushMoreElementToStackAndPollThenAlwaysPollingLast() {
        SimpleStack<Integer> iStack = new SimpleStack<>();
        iStack.push(1);
        iStack.push(2);
        iStack.push(3);
        iStack.push(4);
        iStack.push(5);
        Assert.assertThat(iStack.poll(), is(5));
        Assert.assertThat(iStack.poll(), is(4));
        Assert.assertThat(iStack.poll(), is(3));
        Assert.assertThat(iStack.poll(), is(2));
        Assert.assertThat(iStack.poll(), is(1));
    }

    /**
     * Проверяется выпадение исключения в случае получение элемента из пустого стека.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenPollElementFromEmptyStackThenGetException() {
        SimpleStack<Integer> iStack = new SimpleStack<>();
        iStack.push(1);
        iStack.poll();
        iStack.poll();
    }
}