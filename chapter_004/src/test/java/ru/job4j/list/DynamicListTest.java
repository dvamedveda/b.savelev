package ru.job4j.list;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;

/**
 * Тесты класса DynamicList.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class DynamicListTest {

    /**
     * Проверяется добавление элементов в список.
     */
    @Test
    public void whenAddElementToDynamicListThenAddsSuccess() {
        DynamicList<Integer> integers = new DynamicList<>();
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
     * Проверяется рост списка при недостатке места для добавления элементов в массиве.
     * Проверяется увеличение и при четном количестве элементов списка, так и не четном.
     */
    @Test
    public void whenAddMoreElementToDynamicListThenAddsSuccessAndArrayGrows() {
        DynamicList<Integer> integers = new DynamicList<>();
        for (int count = 0; count <= 15; count++) {
            integers.add(count);
        }
        Assert.assertThat(integers.length(), is(22));
        for (int next = 0; next < 20; next++) {
            if (next < 16) {
                Assert.assertThat(integers.get(next), is(next));
            } else {
                Assert.assertNull(integers.get(next));
            }
        }
    }

    /**
     * Проверяется установка элементов в список напрямую, по индексу.
     */
    @Test
    public void whenSetAllElementsToDynamicListThenSetsSuccess() {
        DynamicList<Integer> integers = new DynamicList<>();
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
     * Проверяется удаление элементов по индексу из списка.
     */
    @Test
    public void whenRemoveElementFromDynamicListThenRemovesSuccess() {
        DynamicList<Integer> integers = new DynamicList<>();
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
     * Проверяется добавление элементов в список.
     */
    @Test
    public void whenAddToSimpleArrayThenAddsToFirstEmptySlot() {
        DynamicList<Integer> integers = new DynamicList<>();
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
     * Проверяется последовательный обход списка итератором.
     */
    @Test
    public void whenItersDynamicListThenItersSuccess() {
        DynamicList<Integer> integers = new DynamicList<>();
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
    public void whenItersDynamicListThenHasNextIsRight() {
        DynamicList<Integer> integers = new DynamicList<>();
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
    public void whenCheckHasNextForDynamicListThenIteratorNotIters() {
        DynamicList<Integer> integers = new DynamicList<>();
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
    public void whenItersDynamicListAndHasNextIsFalseThenGetNoSuchElementException() {
        DynamicList<Integer> integers = new DynamicList<>();
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
    public void whenItersDynamicListAndListModifiedAddThenGetConcurrentModificationException() {
        DynamicList<Integer> integers = new DynamicList<>();
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
     * Проверяется, что при итерации по списку,
     * который был модифицирован изменением напрямую элемента,
     * и попытке получить следующий элемент,
     * получаем исключенье ConcurrentModificationException.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenItersDynamicListAndListModifiedSetThenGetConcurrentModificationException() {
        DynamicList<Integer> integers = new DynamicList<>();
        for (int count = 0; count < 5; count++) {
            integers.add(count);
        }
        Iterator<Integer> iterator = integers.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        integers.set(0, 123);
        iterator.next();
    }

    /**
     * Проверяется, что при итерации по списку,
     * который был модифицирован удалением элемента,
     * и попытке получить следующий элемент,
     * получаем исключенье ConcurrentModificationException.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenItersDynamicListAndListModifiedDeletedThenGetConcurrentModificationException() {
        DynamicList<Integer> integers = new DynamicList<>();
        for (int count = 0; count < 5; count++) {
            integers.add(count);
        }
        Iterator<Integer> iterator = integers.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        integers.delete(4);
        iterator.next();
    }

    /**
     * Проверяется, что список действительно является параметризуемым.
     */
    @Test
    public void whenWeWantGenericDynamicListThenItIsGeneric() {
        DynamicList<Integer> integers = new DynamicList<>();
        integers.add(1);
        Assert.assertThat(integers.get(0), is(1));
        DynamicList<String> strings = new DynamicList<>();
        strings.add("abc");
        Assert.assertThat(strings.get(0), is("abc"));
        DynamicList<DynamicList<Integer>> dynamicList = new DynamicList<>();
        dynamicList.add(integers);
        Assert.assertThat(dynamicList.get(0), is(integers));
    }
}