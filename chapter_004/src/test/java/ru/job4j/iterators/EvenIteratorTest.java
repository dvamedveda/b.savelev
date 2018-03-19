package ru.job4j.iterators;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;

/**
 * Тесты класса EvenIterator.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class EvenIteratorTest {
    /**
     * Проверка, что метод hasNext возвращает значение true, если следующий элемент существует.
     */
    @Test
    public void whenIteratingEvenArrayAndNextIsPresentThenHasNextIsTrue() {
        int[] array = {2, 1, 3, 4};
        EvenIterator iterator = new EvenIterator(array);
        iterator.next();
        Assert.assertTrue(iterator.hasNext());
    }

    /**
     * Проверка, что метод hasNext возвращает значение false, если следующий элемент не существует.
     */
    @Test
    public void whenIteratingArrayAndNextIsNotPresentThenHasNextIsFalse() {
        int[] array = {2, 11, 7, 1};
        EvenIterator iterator = new EvenIterator(array);
        iterator.next();
        Assert.assertFalse(iterator.hasNext());
    }

    /**
     * Проверка, что метод next последовательно возвращает правильные значения из заданного массива.
     */
    @Test
    public void whenIteratingNormalArrayThenGetValidNumbers() {
        int[] array = {1, 2, 11, 4, 23, 8, 1, 10, 15, 22, 40};
        EvenIterator iterator = new EvenIterator(array);
        Assert.assertThat(iterator.next(), is(2));
        Assert.assertThat(iterator.next(), is(4));
        Assert.assertThat(iterator.next(), is(8));
        Assert.assertThat(iterator.next(), is(10));
        Assert.assertThat(iterator.next(), is(22));
        Assert.assertThat(iterator.next(), is(40));
    }

    /**
     * Проверка, что метод hasNext не влияет на изменение следующего полученного элемента.
     */
    @Test
    public void whenHasNextCallsThenHeNotAffectIndexIncrement() {
        int[] array = {1, 2, 11, 4, 23, 8, 1, 10, 15, 22, 40};
        EvenIterator iterator = new EvenIterator(array);
        iterator.hasNext();
        iterator.hasNext();
        Assert.assertThat(iterator.next(), is(2));
        Assert.assertTrue(iterator.hasNext());
    }

    /**
     * Проверка, что при вызове next у массива, в котором нет четных чисел,
     * выбрасывается исключение NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenTryIterateEmptyArrayThenGetException() {
        EvenIterator iterator = new EvenIterator(new int[]{1, 3, 5, 7, 9});
        iterator.next();
    }
}