package ru.job4j.set;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.Matchers.is;

/**
 * Тесты класса SimpleHashSet.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class SimpleHashSetTest {

    /**
     * Проверка, что при добавлении элементов размер множества растет.
     */
    @Test
    public void whenAddElementToSetThenSizeGrows() {
        SimpleHashSet<Integer> integers = new SimpleHashSet<>();
        Assert.assertTrue(integers.size() == 0);
        integers.add(1);
        Assert.assertTrue(integers.size() == 1);
        integers.add(2);
        Assert.assertTrue(integers.size() == 2);
        integers.add(3);
        Assert.assertTrue(integers.size() == 3);
    }

    /**
     * Проверка, что при добавлении дубликатов-элементов размер множества не растет.
     */
    @Test
    public void whenAddDuplicatedElementToSetThenSizeNotGrows() {
        SimpleHashSet<Integer> unique = new SimpleHashSet<>();
        unique.add(1);
        unique.add(1);
        unique.add(1);
        Assert.assertThat(unique.size(), is(1));
    }

    /**
     * Проверка, что при добавлении элементов они все содержатся во множестве.
     */
    @Test
    public void whenAddOneMoreElementToSetThenAllElementsIsContains() {
        SimpleHashSet<Integer> set = new SimpleHashSet<>();
        set.add(1);
        set.add(4);
        set.add(7);
        Assert.assertTrue(set.contains(1));
        Assert.assertTrue(set.contains(4));
        Assert.assertTrue(set.contains(7));
    }

    /**
     * Проверка, что недобавленнные элементы - они не содержатся во множестве.
     */
    @Test
    public void whenCheckNotContainingElementThenElementsIsNotContains() {
        SimpleHashSet<Integer> integers = new SimpleHashSet<>();
        integers.add(3);
        integers.add(8);
        integers.add(11);
        Assert.assertFalse(integers.contains(2));
        Assert.assertFalse(integers.contains(4));
        Assert.assertFalse(integers.contains(12));
    }

    /**
     * Проверка, что при удалении содержащегося элемента они удаляются из множества.
     */
    @Test
    public void whenDeleteContainingElementThenElementsIsDeleting() {
        SimpleHashSet<Integer> numbers = new SimpleHashSet<>();
        numbers.add(3);
        numbers.add(8);
        numbers.add(11);
        numbers.remove(8);
        Assert.assertFalse(numbers.contains(8));
        Assert.assertTrue(numbers.contains(3));
        Assert.assertTrue(numbers.contains(11));
    }

    /**
     * Проверка, что при удалении не содержащегося во множестве элемента ничего не удаляется из множества.
     */
    @Test
    public void whenDeleteNotContainingElementThenElementsIsNotDeleting() {
        SimpleHashSet<Integer> numbers = new SimpleHashSet<>();
        numbers.add(3);
        numbers.add(8);
        numbers.add(11);
        numbers.remove(55);
        Assert.assertTrue(numbers.contains(8));
        Assert.assertTrue(numbers.contains(3));
        Assert.assertTrue(numbers.contains(11));
        Assert.assertThat(numbers.size(), is(3));
    }

    /**
     * Проверка, что при добавлении элементов выше емкости - размер множества увеличивается с сохранением элементов.
     */
    @Test
    public void whenAddElementsOverThresholdThenSetAutomaticallyResizing() {
        SimpleHashSet<Integer> numbers = new SimpleHashSet<>();
        for (int i = 1; i <= 20; i++) {
            numbers.add(i);
            Assert.assertThat(numbers.size(), is(i));
            Assert.assertTrue(numbers.contains(i));
        }
    }

    /**
     * Проверка, что при фактическом добавлении элемента метод add() возвращает true.
     */
    @Test
    public void whenAddElementsSuccessThenAddMethodReturnsTrue() {
        SimpleHashSet<Integer> numbers = new SimpleHashSet<>();
        Assert.assertTrue(numbers.add(1));
        Assert.assertTrue(numbers.contains(1));
        Assert.assertTrue(numbers.size() == 1);
    }

    /**
     * Проверка, что при добавлении элемента-дубликата метод add() возвращает false.
     */
    @Test
    public void whenAddDuplicatedElementThenAddMethodReturnsFalse() {
        SimpleHashSet<Integer> numbers = new SimpleHashSet<>();
        Assert.assertTrue(numbers.add(1));
        Assert.assertFalse(numbers.add(1));
        Assert.assertTrue(numbers.contains(1));
        Assert.assertTrue(numbers.size() == 1);
    }

    /**
     * Проверка, что при фактическом удалении элемента метод remove() возвращает true.
     */
    @Test
    public void whenDeleteElementsSuccessThenDeleteMethodReturnsTrue() {
        SimpleHashSet<Integer> numbers = new SimpleHashSet<>();
        Assert.assertTrue(numbers.add(1));
        Assert.assertTrue(numbers.remove(1));
        Assert.assertFalse(numbers.contains(1));
        Assert.assertTrue(numbers.size() == 0);
    }

    /**
     * Проверка, что при удалении несуществующего элемента метод remove() возвращает false.
     */
    @Test
    public void whenDeleteElementsUnsuccessThenDeleteMethodReturnsFalse() {
        SimpleHashSet<Integer> numbers = new SimpleHashSet<>();
        Assert.assertTrue(numbers.add(1));
        Assert.assertFalse(numbers.remove(2));
        Assert.assertTrue(numbers.contains(1));
        Assert.assertTrue(numbers.size() == 1);
    }

    /**
     * Проверка, что запрос флага hasNext не влияет на итератор.
     */
    @Test
    public void whenCheckHasNextThenCursorNotIters() {
        SimpleHashSet<Integer> numbers = new SimpleHashSet<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        Iterator iterator = numbers.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertTrue(iterator.hasNext());
        iterator.next();
        Assert.assertTrue(iterator.hasNext());
        iterator.next();
        Assert.assertTrue(iterator.hasNext());
        iterator.next();
        Assert.assertFalse(iterator.hasNext());
    }

    /**
     * Проверка итерации по множеству.
     * Проверяется, что итератор в любом порядке
     * возвратит все добавленные элементы, поскольку это хеш-множество -
     * элементы хранятся не в порядке их добавления во множество.
     */
    @Test
    public void whenIterateSetThenItersCorrect() {
        SimpleHashSet<Integer> numbers = new SimpleHashSet<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        Iterator<Integer> iterator = numbers.iterator();
        List<Integer> result = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();
        result.add(iterator.next());
        result.add(iterator.next());
        result.add(iterator.next());
        expected.add(1);
        expected.add(2);
        expected.add(3);
        Assert.assertThat(result, IsIterableContainingInAnyOrder.containsInAnyOrder(expected.toArray()));
        Assert.assertFalse(iterator.hasNext());
    }

    /**
     * Проверка разрешения коллизий хеша при добавлении элементов во множество.
     */
    @Test
    public void whenHaveCollisionThenResolvingIt() {
        SimpleHashSet<Integer> numbers = new SimpleHashSet<>();
        numbers.add(1);
        numbers.add(17);
        numbers.add(17);
        numbers.add(33);
        Iterator<Integer> iterator = numbers.iterator();
        List<Integer> result = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();
        result.add(iterator.next());
        result.add(iterator.next());
        result.add(iterator.next());
        expected.add(1);
        expected.add(17);
        expected.add(33);
        Assert.assertThat(result, IsIterableContainingInAnyOrder.containsInAnyOrder(expected.toArray()));
        Assert.assertFalse(iterator.hasNext());
    }
}