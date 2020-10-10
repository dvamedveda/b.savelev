package ru.job4j.kiss;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

/**
 * Здесь проверяются методы класса MaxMin.
 */
public class MaxMinTest {

    /**
     * Если в списке один элемент - он максимальный.
     */
    @Test
    public void whenOneNumberThenNumberMax() {
        MaxMin mm = new MaxMin();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        Integer actual = mm.max(list, Integer::compare);
        Integer expected = 1;
        Assert.assertThat(actual, is(expected));
    }

    /**
     * Если в списке один элемент - он также и минимальный.
     */
    @Test
    public void whenOneNumberThenNumberMin() {
        MaxMin mm = new MaxMin();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        Integer actual = mm.min(list, Integer::compare);
        Integer expected = 1;
        Assert.assertThat(actual, is(expected));
    }

    /**
     * Если в списке несколько элементов, то методы правильно возвращают максимальный элемент.
     */
    @Test
    public void whenSeveralNumberThenMaxCorrect() {
        MaxMin mm = new MaxMin();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(5);
        list.add(3);
        list.add(4);
        Integer actual = mm.max(list, Integer::compare);
        Integer expected = 5;
        Assert.assertThat(actual, is(expected));
    }

    /**
     * Если в списке несколько элементов, то методы правильно возвращают минимальный элемент.
     */
    @Test
    public void whenSeveralNumberThenMinCorrect() {
        MaxMin mm = new MaxMin();
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(2);
        list.add(5);
        list.add(1);
        list.add(3);
        Integer actual = mm.min(list, Integer::compare);
        Integer expected = 1;
        Assert.assertThat(actual, is(expected));
    }

    /**
     * Если в списке нет элементов, то возвращается Null.
     */
    @Test
    public void whenListEmptyThenReturnsNull() {
        MaxMin mm = new MaxMin();
        List<Integer> list = new ArrayList<>();
        Integer actualmin = mm.min(list, Integer::compare);
        Assert.assertNull(actualmin);
        Integer actualmax = mm.max(list, Integer::compare);
        Assert.assertNull(actualmax);
    }
}