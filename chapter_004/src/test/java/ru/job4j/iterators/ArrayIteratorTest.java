package ru.job4j.iterators;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;

/**
 * Тесты класса ArrayIterator.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class ArrayIteratorTest {

    /**
     * Проверка, что метод hasNext возвращает значение true, если следующий элемент существует.
     */
    @Test
    public void whenIteratingArrayAndNextIsPresentThenHasNextIsTrue() {
        int[][] array = {{1, 2}, {3, 4}};
        ArrayIterator iterator = new ArrayIterator(array);
        iterator.next();
        Assert.assertTrue(iterator.hasNext());
    }

    /**
     * Проверка, что метод hasNext возвращает значение false, если следующий элемент не существует.
     */
    @Test
    public void whenIteratingArrayAndNextIsNotPresentThenHasNextIsFalse() {
        int[][] array = {{1, 2}, {3, 4}};
        ArrayIterator iterator = new ArrayIterator(array);
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        Assert.assertFalse(iterator.hasNext());
    }

    /**
     * Проверка, что метод next последовательно возвращает правильные значения из квадратного массива.
     */
    @Test
    public void whenIteratingNormalArrayThenGetValidNumbers() {
        int[][] array = {{1, 2}, {3, 4}};
        ArrayIterator iterator = new ArrayIterator(array);
        Assert.assertThat(iterator.next(), is(1));
        Assert.assertThat(iterator.next(), is(2));
        Assert.assertThat(iterator.next(), is(3));
        Assert.assertThat(iterator.next(), is(4));
    }

    /**
     * Проверка, что метод next последовательно возвращает правильные значения из неполного двумерного массива.
     */
    @Test
    public void whenIteratingJaggedArrayThenGetValidNumbers() {
        int[][] array = {{1, 2}, {3}, {4, 5, 6}};
        ArrayIterator iterator = new ArrayIterator(array);
        Assert.assertThat(iterator.next(), is(1));
        Assert.assertThat(iterator.next(), is(2));
        Assert.assertThat(iterator.next(), is(3));
        Assert.assertThat(iterator.next(), is(4));
        Assert.assertThat(iterator.next(), is(5));
        Assert.assertThat(iterator.next(), is(6));
    }

    /**
     * Проверка, что метод hasNext не влияет на изменение следующего полученного элемента.
     */
    @Test
    public void whenHasNextCallsThenHeNotAffectIndexIncrement() {
        int[][] array = {{1, 2}, {3, 4}};
        ArrayIterator iterator = new ArrayIterator(array);
        iterator.hasNext();
        iterator.hasNext();
        Assert.assertThat(iterator.next(), is(1));
        Assert.assertTrue(iterator.hasNext());
    }

    /**
     * Проверка, что при вызове next у неинициализированного двумерного массива
     * выбрасывается исключение NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenTryIterateEmptyArrayThenGetException() {
        ArrayIterator iterator = new ArrayIterator(new int[][]{});
        iterator.next();
    }
}