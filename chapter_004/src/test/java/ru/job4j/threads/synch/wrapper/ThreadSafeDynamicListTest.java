package ru.job4j.threads.synch.wrapper;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;

/**
 * Класс, реализующий тест для класса ThreadSafeDynamicList.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class ThreadSafeDynamicListTest {

    /**
     * Проверка, что в список можно добавлять элементы.
     */
    @Test
    public void whenCreateTSDLThenAddingIsSuccess() {
        ThreadSafeDynamicList<Integer> tsdl = new ThreadSafeDynamicList<>();
        tsdl.add(1);
        tsdl.add(2);
        tsdl.add(3);
        Assert.assertThat(tsdl.get(0), is(1));
        Assert.assertThat(tsdl.get(1), is(2));
        Assert.assertThat(tsdl.get(2), is(3));
    }

    /**
     * Проверка, что в списке можно обновлять элементы.
     */
    @Test
    public void whenSetValueInTSDLThenSettingIsSuccess() {
        ThreadSafeDynamicList<Integer> tsdl = new ThreadSafeDynamicList<>();
        tsdl.add(1);
        tsdl.add(2);
        tsdl.add(3);
        tsdl.set(1, -1);
        Assert.assertThat(tsdl.get(0), is(1));
        Assert.assertThat(tsdl.get(1), is(-1));
        Assert.assertThat(tsdl.get(2), is(3));
    }

    /**
     * Проверка, что в списке можно удалять элементы.
     */
    @Test
    public void whenDeleteValueInTSDLThenDeletingIsSuccess() {
        ThreadSafeDynamicList<Integer> tsdl = new ThreadSafeDynamicList<>();
        tsdl.add(1);
        tsdl.add(2);
        tsdl.add(3);
        tsdl.delete(1);
        Assert.assertThat(tsdl.get(0), is(1));
        Assert.assertThat(tsdl.get(1), is(3));
        Assert.assertNull(tsdl.get(2));
    }

    /**
     * Проверка длины списка.
     */
    @Test
    public void whenCallLengthTSDLThenLengthIsCorrect() {
        ThreadSafeDynamicList<Integer> tsdl = new ThreadSafeDynamicList<>();
        tsdl.add(1);
        tsdl.add(2);
        tsdl.add(3);
        Assert.assertThat(tsdl.get(0), is(1));
        Assert.assertThat(tsdl.get(1), is(2));
        Assert.assertThat(tsdl.get(2), is(3));
        Assert.assertThat(tsdl.length(), is(10));
    }

    /**
     * Проверка, что итерация за пределы списка вызывает исключение.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenNoElementsInTSDLThenGetException() {
        ThreadSafeDynamicList<Integer> tsdl = new ThreadSafeDynamicList<>();
        tsdl.add(1);
        tsdl.add(2);
        tsdl.add(3);
        Iterator<Integer> iterator = tsdl.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }

    /**
     * Проверка, что итерация в пределах списка корректна.
     */
    @Test
    public void whenItersOverTSDLThenIterationIsCorrect() {
        ThreadSafeDynamicList<Integer> tsdl = new ThreadSafeDynamicList<>();
        tsdl.add(1);
        tsdl.add(2);
        tsdl.add(3);
        Iterator<Integer> iterator = tsdl.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(1));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(2));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(3));
        Assert.assertFalse(iterator.hasNext());
    }

    /**
     * Проверка, что при итерации создается снапшот списка,
     * по которому можно итерироваться во время добавления элементов в список
     * и такая итерация корректна.
     */
    @Test
    public void whenItersOverTSDLAndElementsAddingThenIterationGoesOverSnapshot() throws InterruptedException {
        ThreadSafeDynamicList<Integer> tsdl = new ThreadSafeDynamicList<>();
        tsdl.add(1);
        tsdl.add(2);
        tsdl.add(3);
        Iterator<Integer> iterator = tsdl.iterator();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                tsdl.add(i + 3);
            }
        });
        thread.start();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(1));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(2));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(3));
        Assert.assertFalse(iterator.hasNext());
        thread.join();
    }

    /**
     * Проверка, что при итерации создается снапшот списка,
     * по которому можно итерироваться во время удаления элемента из списка
     * и такая итерация корректна.
     */
    @Test
    public void whenItersOverTSDLAndElementsDeletingThenIterationGoesOverSnapshot() throws InterruptedException {
        ThreadSafeDynamicList<Integer> tsdl = new ThreadSafeDynamicList<>();
        for (int i = 0; i < 50000; i++) {
            tsdl.add(i);
        }
        Iterator<Integer> iterator = tsdl.iterator();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                tsdl.delete(i);
            }
        });
        thread.start();
        Thread.sleep(1000);
        for (int i = 0; i < 50000; i++) {
            Assert.assertThat(iterator.next(), is(i));
        }
        thread.join();
    }
}