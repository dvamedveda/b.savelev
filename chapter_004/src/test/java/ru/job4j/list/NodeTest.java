package ru.job4j.list;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

/**
 * Тесты класса ru.job4j.list.Node.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class NodeTest {

    /**
     * Проверяем создание примитивного связанного списка.
     */
    @Test
    public void whenCreateListThenSuccessCreating() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        first.setNext(second);
        second.setNext(third);
        Assert.assertThat(second, is(first.getNext()));
        Assert.assertThat(third, is(second.getNext()));
        Assert.assertNull(third.getNext());
    }

    /**
     * Проверяем результат проверки незацикленного списка на наличие зацикленностей.
     */
    @Test
    public void whenCheckNormalListThenHasCycleIsFalse() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> fourth = new Node<>(4);
        first.setNext(second);
        second.setNext(third);
        third.setNext(fourth);
        Assert.assertFalse(first.hasCycle(first));
    }

    /**
     * Проверяем результат проверки зацикленного списка на наличие зацикленностей.
     * Список зациклен с последнего элемента на первый.
     */
    @Test
    public void whenCheckCycledFromLastToFirstListThenHasCycleIsTrue() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> fourth = new Node<>(4);
        first.setNext(second);
        second.setNext(third);
        third.setNext(fourth);
        fourth.setNext(first);
        Assert.assertTrue(first.hasCycle(first));
    }

    /**
     * Проверяем результат проверки зацикленного списка на наличие зацикленностей.
     * Список зациклен с последнего элемента на один из промежуточных.
     */
    @Test
    public void whenCheckCycledFromLastToHalfListThenHasCycleIsTrue() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> fourth = new Node<>(4);
        Node<Integer> fifth = new Node<>(5);
        first.setNext(second);
        second.setNext(third);
        third.setNext(fourth);
        fourth.setNext(fifth);
        fifth.setNext(third);
        Assert.assertTrue(first.hasCycle(first));
    }

    /**
     * Проверяем результат проверки зацикленного списка на наличие зацикленностей.
     * Список зациклен с одного из промежуточных элементов на один из промежуточных.
     */
    @Test
    public void whenCheckCycledFromHalfToHalfListThenHasCycleIsTrue() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> fourth = new Node<>(4);
        Node<Integer> fifth = new Node<>(5);
        first.setNext(second);
        second.setNext(third);
        third.setNext(second);
        fourth.setNext(fifth);
        Assert.assertTrue(first.hasCycle(first));
    }
}