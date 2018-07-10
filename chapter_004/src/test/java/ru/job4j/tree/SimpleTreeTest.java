package ru.job4j.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleTreeTest {

    /**
     * Проверка, что дерево содержит минимум одно значение при создании.
     */
    @Test
    public void whenAddRootElementThenAddingSuccess() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        assertThat(tree.findBy(1).isPresent(), is(true));
    }

    /**
     * Проверка, что дерево содержит значения, которые в него были добавлены.
     */
    @Test
    public void whenAddElementsThenElementsFindingSuccess() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(1).isPresent(), is(true));
        assertThat(tree.findBy(2).isPresent(), is(true));
        assertThat(tree.findBy(3).isPresent(), is(true));
        assertThat(tree.findBy(4).isPresent(), is(true));
        assertThat(tree.findBy(5).isPresent(), is(true));
    }

    /**
     * Проверка, что дерево не содержит значений, которые в него не добавлялись.
     */
    @Test
    public void whenElementNotExistsThenResultNodeEmpty() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(6).isPresent(), is(false));
    }

    /**
     * Проверка, что в дерево не могут быть добавлены дубликаты значений.
     */
    @Test
    public void whenAddDuplicatedElementsThenAddingUnsuccess() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        Assert.assertTrue(tree.add(1, 2));
        Assert.assertFalse(tree.add(1, 2));
        assertThat(tree.findBy(1).isPresent(), is(true));
        assertThat(tree.findBy(2).isPresent(), is(true));
    }

    /**
     * Проверка, что итерация по дереву происходит корректно, и при этом корректно меняется флажок hasNext().
     */
    @Test
    public void whenIterateOverSimpleTreeThenIteratingCorrect() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(3, 7);
        Iterator iterator = tree.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(1));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(2));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(3));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(4));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(5));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(6));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(7));
        Assert.assertFalse(iterator.hasNext());
    }

    /**
     * Проверка, что при итерации по дереву вызов флажка hasNext() не меняет состояние очереди.
     */
    @Test
    public void whenCallHasNextThenCallingNotAffectNext() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        Iterator iterator = tree.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(1));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(2));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(3));
        Assert.assertFalse(iterator.hasNext());
    }

    /**
     * Проверка, что при итерации по дереву где кончились элементы, будет брошено исключение.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenTryItersOutFromIteratorThenGetException() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        Iterator iterator = tree.iterator();
        iterator.next();
        iterator.next();
    }

    /**
     * Проверка работы метода isBinary в случае, когда дерево - бинарное.
     */
    @Test
    public void whenTreeIsBinaryThenIsBinaryIsTrue() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(3, 7);
        Assert.assertTrue(tree.isBinary());
    }

    /**
     * Проверка работы метода isBinary в случае, когда дерево - новосозданное.
     */
    @Test
    public void whenTreeIsNewlyCreatedThenIsBinaryIsTrue() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        Assert.assertTrue(tree.isBinary());
    }

    /**
     * Проверка работы метода isBinary в случае, когда дерево - не бинарное.
     */
    @Test
    public void whenTreeIsNotBinaryThenIsBinaryIsFalse() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(4, 7);
        Assert.assertFalse(tree.isBinary());
    }
}