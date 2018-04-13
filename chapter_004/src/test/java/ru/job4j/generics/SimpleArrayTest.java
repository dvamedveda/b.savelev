package ru.job4j.generics;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;

/**
 * Тесты класса SimpleArray.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class SimpleArrayTest {

    /**
     * Проверяется добавление элементов в массив.
     */
    @Test
    public void whenAddElementToSimpleArrayThenAddsSuccess() {
        SimpleArray<Integer> integers = new SimpleArray<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        Assert.assertThat(integers.length(), is(10));
        Assert.assertThat(integers.get(0), is(1));
        Assert.assertThat(integers.get(1), is(2));
        Assert.assertThat(integers.get(2), is(3));
        Assert.assertNull(integers.get(3));
        Assert.assertNull(integers.get(4));
        Assert.assertNull(integers.get(5));
        Assert.assertNull(integers.get(6));
        Assert.assertNull(integers.get(7));
        Assert.assertNull(integers.get(8));
        Assert.assertNull(integers.get(9));

    }

    /**
     * Проверяется рост массива при недостатке места для добавления элементов в массиве.
     */
    @Test
    public void whenAddMoreElementToSimpleArrayThenAddsSuccessAndArrayGrows() {
        SimpleArray<Integer> integers = new SimpleArray<>();
        for (int count = 0; count <= 15; count++) {
            integers.add(count);
        }
        Assert.assertThat(integers.length(), is(20));
        for (int next = 0; next < 20; next++) {
            if (next < 16) {
                Assert.assertThat(integers.get(next), is(next));
            } else {
                Assert.assertNull(integers.get(next));
            }
        }
    }

    /**
     * Проверяется установка элементов в массив напрямую, по индексу.
     */
    @Test
    public void whenSetAllElementsToSimpleArrayThenSetsSuccess() {
        SimpleArray<Integer> integers = new SimpleArray<>();
        for (int count = 0; count < 10; count++) {
            integers.set(count, Math.abs(count - 9));
        }
        Assert.assertThat(integers.get(0), is(9));
        Assert.assertThat(integers.get(1), is(8));
        Assert.assertThat(integers.get(2), is(7));
        Assert.assertThat(integers.get(3), is(6));
        Assert.assertThat(integers.get(4), is(5));
        Assert.assertThat(integers.get(5), is(4));
        Assert.assertThat(integers.get(6), is(3));
        Assert.assertThat(integers.get(7), is(2));
        Assert.assertThat(integers.get(8), is(1));
        Assert.assertThat(integers.get(9), is(0));
    }

    /**
     * Проверяется удаление элементов по индексу из массива.
     */
    @Test
    public void whenRemoveElementFromSimpleArrayThenRemovesSuccess() {
        SimpleArray<Integer> integers = new SimpleArray<>();
        for (int count = 0; count < 10; count++) {
            integers.add(count);
        }
        integers.delete(5);
        integers.delete(7);
        Assert.assertThat(integers.get(0), is(0));
        Assert.assertThat(integers.get(1), is(1));
        Assert.assertThat(integers.get(2), is(2));
        Assert.assertThat(integers.get(3), is(3));
        Assert.assertThat(integers.get(4), is(4));
        Assert.assertThat(integers.get(5), is(6));
        Assert.assertThat(integers.get(6), is(7));
        Assert.assertThat(integers.get(7), is(9));
        Assert.assertNull(integers.get(9));
        Assert.assertNull(integers.get(9));

    }

    /**
     * Проверяется добавление элементов в первый свободный слот.
     */
    @Test
    public void whenAddToSimpleArrayThenAddsToFirstEmptySlot() {
        SimpleArray<Integer> integers = new SimpleArray<>();
        for (int count = 0; count < 5; count++) {
            integers.add(count);
        }
        integers.delete(1);
        integers.delete(3);
        Assert.assertThat(integers.get(0), is(0));
        Assert.assertThat(integers.get(1), is(2));
        Assert.assertThat(integers.get(2), is(3));
        Assert.assertNull(integers.get(3));
        Assert.assertNull(integers.get(4));
        integers.add(111);
        Assert.assertThat(integers.get(0), is(0));
        Assert.assertThat(integers.get(1), is(2));
        Assert.assertThat(integers.get(2), is(3));
        Assert.assertThat(integers.get(3), is(111));
        Assert.assertNull(integers.get(4));
    }

    /**
     * Проверяется последовательный обход массива итератором.
     */
    @Test
    public void whenItersSimpleArrayThenItersSuccess() {
        SimpleArray<Integer> integers = new SimpleArray<>();
        for (int count = 0; count < 10; count++) {
            integers.add(count);
        }
        Iterator<Integer> iterator = integers.iterator();
        for (int eachNext = 0; eachNext < 10; eachNext++) {
            Assert.assertThat(iterator.next(), is(eachNext));
        }
    }

    /**
     * Проверяется наличие следующего элемента при последовательном обходе массива итератором.
     */
    @Test
    public void whenItersSimpleArrayThenHasNextIsRight() {
        SimpleArray<Integer> integers = new SimpleArray<>();
        for (int count = 0; count < 5; count++) {
            integers.add(count);
        }
        Iterator<Integer> iterator = integers.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(0));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(1));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(2));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(3));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(4));
        Assert.assertFalse(iterator.hasNext());
    }

    /**
     * Проверяется, что проверка наличия следующего элемента не меняет состояние итератора для массива.
     */
    @Test
    public void whenCheckHasNextForSimpleArrayThenIteratorNotIters() {
        SimpleArray<Integer> integers = new SimpleArray<>();
        for (int count = 0; count < 5; count++) {
            integers.add(count);
        }
        Iterator<Integer> iterator = integers.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(0));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(1));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(2));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(3));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(4));
        Assert.assertFalse(iterator.hasNext());
    }

    /**
     * Проверяется, что при отсутствии следующего элемента и попытке получить следующий элемент, получаем исключенье.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenItersSimpleArrayAndHasNextIsFalseThenGetException() {
        SimpleArray<Integer> integers = new SimpleArray<>();
        for (int count = 0; count < 5; count++) {
            integers.add(count);
        }
        Iterator<Integer> iterator = integers.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }

    /**
     * Проверяется, что массив действительно является параметризуемым.
     */
    @Test
    public void whenWeWantGenericSimpleArrayThenItIsGeneric() {
        SimpleArray<Integer> integers = new SimpleArray<>();
        integers.add(1);
        Assert.assertThat(integers.get(0), is(1));
        SimpleArray<String> strings = new SimpleArray<>();
        strings.add("abc");
        Assert.assertThat(strings.get(0), is("abc"));
        SimpleArray<SimpleArray<Integer>> simpleArrays = new SimpleArray<>();
        simpleArrays.add(integers);
        Assert.assertThat(simpleArrays.get(0), is(integers));
    }
}