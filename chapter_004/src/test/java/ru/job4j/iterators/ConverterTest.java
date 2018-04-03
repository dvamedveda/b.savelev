package ru.job4j.iterators;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Тесты класса Converter.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class ConverterTest {
    private Iterator<Integer> resultIterator;

    /**
     * Задаем исходные данные:
     * набор итераторов чисел, которые преобразуем в итератор итераторов и затем используем в тесте.
     */
    @Before
    public void setUp() {
        Iterator<Integer> iterator1 = Arrays.asList(1, 2, 3).iterator();
        Iterator<Integer> iterator2 = Arrays.asList(4, 5, 6).iterator();
        Iterator<Integer> iterator3 = Arrays.asList(7, 8, 9).iterator();
        Iterator<Iterator<Integer>> its = Arrays.asList(iterator1, iterator2, iterator3).iterator();
        Converter iteratorOfIterators = new Converter();
        resultIterator = iteratorOfIterators.convert(its);
    }

    /**
     * Тест проверяет последовательное получение правильных чисел
     * из всего итератора итераторов и наличие следующего
     * числа в итераторе после каждой итерации.
     */
    @Test
    public void hasNextNextSequentialInvocation() {
        assertThat(resultIterator.hasNext(), is(true));
        assertThat(resultIterator.next(), is(1));
        assertThat(resultIterator.hasNext(), is(true));
        assertThat(resultIterator.next(), is(2));
        assertThat(resultIterator.hasNext(), is(true));
        assertThat(resultIterator.next(), is(3));
        assertThat(resultIterator.hasNext(), is(true));
        assertThat(resultIterator.next(), is(4));
        assertThat(resultIterator.hasNext(), is(true));
        assertThat(resultIterator.next(), is(5));
        assertThat(resultIterator.hasNext(), is(true));
        assertThat(resultIterator.next(), is(6));
        assertThat(resultIterator.hasNext(), is(true));
        assertThat(resultIterator.next(), is(7));
        assertThat(resultIterator.hasNext(), is(true));
        assertThat(resultIterator.next(), is(8));
        assertThat(resultIterator.hasNext(), is(true));
        assertThat(resultIterator.next(), is(9));
        assertThat(resultIterator.hasNext(), is(false));
    }

    /**
     * Этот тест проверяет последовательное получение чисел из итератора
     * подряд, одно за другим.
     */
    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        assertThat(resultIterator.next(), is(1));
        assertThat(resultIterator.next(), is(2));
        assertThat(resultIterator.next(), is(3));
        assertThat(resultIterator.next(), is(4));
        assertThat(resultIterator.next(), is(5));
        assertThat(resultIterator.next(), is(6));
        assertThat(resultIterator.next(), is(7));
        assertThat(resultIterator.next(), is(8));
        assertThat(resultIterator.next(), is(9));
    }

    /**
     * Этот тест проверяет, не влияет ли на выдачу чисел итератором
     * проверка наличия следующего числа в итераторе.
     */
    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(resultIterator.hasNext(), is(true));
        assertThat(resultIterator.hasNext(), is(true));
        assertThat(resultIterator.next(), is(1));
        assertThat(resultIterator.next(), is(2));
        assertThat(resultIterator.next(), is(3));
        assertThat(resultIterator.next(), is(4));
        assertThat(resultIterator.next(), is(5));
        assertThat(resultIterator.next(), is(6));
        assertThat(resultIterator.next(), is(7));
        assertThat(resultIterator.next(), is(8));
        assertThat(resultIterator.next(), is(9));
    }

    /**
     * Этот тест проверяет поведение hasNext в случае, когда все итераторы пусты.
     */
    @Test
    public void hasNextShouldReturnFalseInCaseOfEmptyIterators() {
        Iterator<Integer> emptyIterator1 = (new ArrayList<Integer>()).iterator();
        Iterator<Integer> emptyIterator2 = (new ArrayList<Integer>()).iterator();
        Iterator<Integer> emptyIterator3 = (new ArrayList<Integer>()).iterator();
        Iterator<Iterator<Integer>> emptyIterators = Arrays.asList(emptyIterator1, emptyIterator2, emptyIterator3).iterator();
        Converter iteratorOfIterators = new Converter();
        resultIterator = iteratorOfIterators.convert(emptyIterators);
        assertThat(resultIterator.hasNext(), is(false));
    }

    /**
     * Этот тест проверяет выбрасывание исключения в случае,
     * когда пытаемся получить следующее число из итератора при !hasNext.
     */
    @Test(expected = NoSuchElementException.class)
    public void invocationOfNextMethodShouldThrowNoSuchElementException() {
        Iterator<Integer> iterator1 = Arrays.asList(1, 2, 3).iterator();
        Iterator<Iterator<Integer>> aloneIterator = Arrays.asList(iterator1).iterator();
        Converter iteratorOfIterators = new Converter();
        resultIterator = iteratorOfIterators.convert(aloneIterator);
        assertThat(resultIterator.next(), is(1));
        assertThat(resultIterator.next(), is(2));
        assertThat(resultIterator.next(), is(3));
        resultIterator.next();
    }
}