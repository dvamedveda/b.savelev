package ru.job4j.list;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;

/**
 * Тесты класса LinkedContainer.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class LinkedContainerTest {

    /**
     * Проверяется добавление одного элемента в связанный список.
     */
    @Test
    public void whenAddOneElementToLinkedContainerThenAddsSuccess() {
        LinkedContainer<Integer> integers = new LinkedContainer<>();
        integers.add(1);
        Assert.assertThat(integers.length(), is(1));
        Assert.assertThat(integers.get(0), is(1));
    }

    /**
     * Проверяется добавление элементов в связанный список.
     */
    @Test
    public void whenAddElementToLinkedContainerThenAddsSuccess() {
        LinkedContainer<Integer> integers = new LinkedContainer<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        Assert.assertThat(integers.length(), is(3));
        Assert.assertThat(integers.get(0), is(1));
        Assert.assertThat(integers.get(1), is(2));
        Assert.assertThat(integers.get(2), is(3));

    }

    /**
     * Проверяется рост списка при добавлении элементов в него.
     */
    @Test
    public void whenAddTenElementToLinkedContainerThenAddsSuccessAndLengthIsTen() {
        LinkedContainer<Integer> integers = new LinkedContainer<>();
        for (int count = 0; count < 10; count++) {
            integers.add(count);
        }
        Assert.assertThat(integers.length(), is(10));
        for (int next = 0; next < 10; next++) {
            Assert.assertThat(integers.get(next), is(next));
        }
    }

    /**
     * Проверяется последовательный обход списка итератором.
     */
    @Test
    public void whenItersLinkedContainerThenItersSuccess() {
        LinkedContainer<Integer> integers = new LinkedContainer<>();
        for (int count = 0; count < 10; count++) {
            integers.add(count);
        }
        Iterator<Integer> iterator = integers.iterator();
        for (int eachNext = 0; eachNext < 10; eachNext++) {
            Assert.assertThat(iterator.next(), is(eachNext));
        }
    }

    /**
     * Проверяется наличие следующего элемента при последовательном обходе списка итератором.
     */
    @Test
    public void whenItersLinkedContainerThenHasNextIsRight() {
        LinkedContainer<Integer> integers = new LinkedContainer<>();
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
     * Проверяется, что проверка наличия следующего элемента не меняет состояние итератора для списка.
     */
    @Test
    public void whenCheckHasNextForLinkedContainerThenIteratorNotIters() {
        LinkedContainer<Integer> integers = new LinkedContainer<>();
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
     * Проверяется, что при отсутствии следующего элемента
     * и попытке получить следующий элемент, получаем
     * исключенье NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenItersLinkedContainerAndHasNextIsFalseThenGetNoSuchElementException() {
        LinkedContainer<Integer> integers = new LinkedContainer<>();
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
     * Проверяется, что при итерации по списку,
     * который был модифицирован добавлением элемента,
     * и попытке получить следующий элемент,
     * получаем исключенье ConcurrentModificationException.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenItersLinkedContainerAndListModifiedAddThenGetConcurrentModificationException() {
        LinkedContainer<Integer> integers = new LinkedContainer<>();
        for (int count = 0; count < 5; count++) {
            integers.add(count);
        }
        Iterator<Integer> iterator = integers.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        integers.add(11);
        iterator.next();
    }

    /**
     * Проверяется, что список действительно является параметризуемым.
     */
    @Test
    public void whenWeWantGenericLinkedContainerThenItIsGeneric() {
        LinkedContainer<Integer> integers = new LinkedContainer<>();
        integers.add(1);
        Assert.assertThat(integers.get(0), is(1));
        DynamicList<String> strings = new DynamicList<>();
        strings.add("abc");
        Assert.assertThat(strings.get(0), is("abc"));
        LinkedContainer<LinkedContainer<Integer>> linkedContainer = new LinkedContainer<>();
        linkedContainer.add(integers);
        Assert.assertThat(linkedContainer.get(0), is(integers));
    }
}