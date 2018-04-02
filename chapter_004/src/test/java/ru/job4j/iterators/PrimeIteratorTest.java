package ru.job4j.iterators;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;

/**
 * Тесты класса PrimeIterator.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class PrimeIteratorTest {
    /**
     * Проверка, что метод hasNext возвращает значение true, если следующий элемент существует.
     */
    @Test
    public void whenIteratingPrimeArrayAndNextIsPresentThenHasNextIsTrue() {
        int[] array = {2, 3, 7, 11};
        PrimeIterator iterator = new PrimeIterator(array);
        iterator.next();
        Assert.assertTrue(iterator.hasNext());
    }

    /**
     * Проверка, что метод hasNext возвращает значение false, если следующий элемент не существует.
     */
    @Test
    public void whenIteratingArrayAndNextIsNotPresentThenHasNextIsFalse() {
        int[] array = {7, 4, 1};
        PrimeIterator iterator = new PrimeIterator(array);
        iterator.next();
        Assert.assertFalse(iterator.hasNext());
    }

    /**
     * Проверка, что метод next последовательно возвращает правильные значения из заданного массива.
     */
    @Test
    public void whenIteratingNormalArrayThenGetValidNumbers() {
        int[] array = {1, 2, 11, 4, 23, 8, 1, 10, 15, 22, 40};
        PrimeIterator iterator = new PrimeIterator(array);
        Assert.assertThat(iterator.next(), is(2));
        Assert.assertThat(iterator.next(), is(11));
        Assert.assertThat(iterator.next(), is(23));
        Assert.assertFalse(iterator.hasNext());
    }

    /**
     * Проверка, что метод hasNext не влияет на изменение следующего полученного элемента.
     */
    @Test
    public void whenHasNextCallsThenHeNotAffectIndexIncrement() {
        int[] array = {1, 2, 11, 4, 23, 8, 1, 10, 15, 22, 40};
        PrimeIterator iterator = new PrimeIterator(array);
        iterator.hasNext();
        iterator.hasNext();
        Assert.assertThat(iterator.next(), is(2));
        Assert.assertThat(iterator.next(), is(11));
        Assert.assertTrue(iterator.hasNext());
    }

    /**
     * Проверка, что при вызове next у массива, в котором нет простых чисел,
     * выбрасывается исключение NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenTryIterateEmptyArrayThenGetException() {
        PrimeIterator iterator = new PrimeIterator(new int[]{1, 4, 6, 8, 10});
        iterator.next();
    }
}