package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.map.SimpleHashMap.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;

/**
 * Тесты класса SimpleHashMap.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class SimpleHashMapTest {

    /**
     * Проверка успешного добавления пары в карту.
     */
    @Test
    public void whenAddElementThenSuccessfullyGettingIt() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "first");
        Assert.assertThat(map.size(), is(1));
        Assert.assertThat(map.get(1), is("first"));
    }

    /**
     * Проверка успешного добавления нескольких пар в карту.
     */
    @Test
    public void whenAddElementsThenSuccessfullyAdding() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "first");
        map.insert(2, "second");
        map.insert(3, "third");
        Assert.assertThat(map.size(), is(3));
        Assert.assertThat(map.get(1), is("first"));
        Assert.assertThat(map.get(2), is("second"));
        Assert.assertThat(map.get(3), is("third"));
    }

    /**
     * Проверка добавления пар с дублирующимися ключами пар в карту.
     */
    @Test
    public void whenAddDuplicatedKeysThenDuplicatedNotAdding() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        Assert.assertTrue(map.insert(1, "first"));
        Assert.assertFalse(map.insert(1, "first"));
        Assert.assertThat(map.size(), is(1));
        Assert.assertThat(map.get(1), is("first"));
    }

    /**
     * Проверка добавления пар с дублирующимися значениями пар в карту.
     */
    @Test
    public void whenAddDuplicatedValuesThenDuplicatedAdding() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        Assert.assertTrue(map.insert(1, "first"));
        Assert.assertTrue(map.insert(2, "first"));
        Assert.assertTrue(map.insert(3, "first"));
        Assert.assertThat(map.size(), is(3));
        Assert.assertThat(map.get(1), is("first"));
        Assert.assertThat(map.get(2), is("first"));
        Assert.assertThat(map.get(3), is("first"));
    }

    /**
     * Проверка удаления пары из карты по существующему ключу.
     */
    @Test
    public void whenDeleteElementsThenDeletingSuccess() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "first");
        map.insert(2, "second");
        map.insert(5, "fifth");
        Assert.assertTrue(map.delete(5));
        Assert.assertThat(map.size(), is(2));
        Assert.assertThat(map.get(1), is("first"));
        Assert.assertThat(map.get(2), is("second"));
    }

    /**
     * Проверка удаления пары из карты по несуществующему ключу.
     */
    @Test
    public void whenDeleteNotExistingElementsThenDeletingFails() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "first");
        map.insert(2, "second");
        map.insert(3, "third");
        Assert.assertFalse(map.delete(5));
        Assert.assertThat(map.size(), is(3));
        Assert.assertThat(map.get(1), is("first"));
        Assert.assertThat(map.get(2), is("second"));
        Assert.assertThat(map.get(3), is("third"));
    }

    /**
     * Проверка увеличения размеры карты после достижения коэффициента заполнения.
     */
    @Test
    public void whenAddingMoreElementsThenMapGrows() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "first");
        map.insert(2, "second");
        map.insert(3, "third");
        map.insert(4, "fourth");
        map.insert(5, "fifth");
        map.insert(6, "sixth");
        map.insert(7, "seventh");
        map.insert(8, "eighth");
        map.insert(9, "ninth");
        map.insert(10, "tenth");
        map.insert(11, "eleventh");
        map.insert(12, "twelfth");
        map.insert(13, "thirteenth");
        map.insert(14, "fourteenth");
        map.insert(15, "fifteenth");
        map.insert(16, "sixteenth");
        map.insert(17, "seventeenth");
        Assert.assertThat(map.size(), is(17));
        Assert.assertThat(map.get(1), is("first"));
        Assert.assertThat(map.get(2), is("second"));
        Assert.assertThat(map.get(3), is("third"));
        Assert.assertThat(map.get(4), is("fourth"));
        Assert.assertThat(map.get(5), is("fifth"));
        Assert.assertThat(map.get(6), is("sixth"));
        Assert.assertThat(map.get(7), is("seventh"));
        Assert.assertThat(map.get(8), is("eighth"));
        Assert.assertThat(map.get(9), is("ninth"));
        Assert.assertThat(map.get(10), is("tenth"));
        Assert.assertThat(map.get(11), is("eleventh"));
        Assert.assertThat(map.get(12), is("twelfth"));
        Assert.assertThat(map.get(13), is("thirteenth"));
        Assert.assertThat(map.get(14), is("fourteenth"));
        Assert.assertThat(map.get(15), is("fifteenth"));
        Assert.assertThat(map.get(16), is("sixteenth"));
        Assert.assertThat(map.get(17), is("seventeenth"));
    }

    /**
     * Проверка того, что вызов проверки наличия следующего элемента в итераторе не перемещает курсор в итераторе.
     */
    @Test
    public void whenCheckHasNextThenIteratorNotAffects() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "first");
        map.insert(2, "second");
        map.insert(3, "third");
        Iterator<Node> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertTrue(iterator.hasNext());
        SimpleHashMap.Node firstNode = iterator.next();
        Assert.assertThat(1, is(firstNode.getKey()));
        Assert.assertThat("first", is(firstNode.getValue()));
        SimpleHashMap.Node secondNode = iterator.next();
        Assert.assertThat(2, is(secondNode.getKey()));
        Assert.assertThat("second", is(secondNode.getValue()));
        SimpleHashMap.Node thirdNode = iterator.next();
        Assert.assertThat(3, is(thirdNode.getKey()));
        Assert.assertThat("third", is(thirdNode.getValue()));
        Assert.assertFalse(iterator.hasNext());
    }

    /**
     * Проверка корректной итерации по карте.
     */
    @Test
    public void whenItersMapThenItersCorrectly() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "first");
        map.insert(3, "third");
        map.insert(5, "fifth");
        Iterator<Node> iterator = map.iterator();
        SimpleHashMap.Node firstNode = iterator.next();
        Assert.assertThat(1, is(firstNode.getKey()));
        Assert.assertThat("first", is(firstNode.getValue()));
        SimpleHashMap.Node secondNode = iterator.next();
        Assert.assertThat(3, is(secondNode.getKey()));
        Assert.assertThat("third", is(secondNode.getValue()));
        SimpleHashMap.Node thirdNode = iterator.next();
        Assert.assertThat(5, is(thirdNode.getKey()));
        Assert.assertThat("fifth", is(thirdNode.getValue()));
        Assert.assertFalse(iterator.hasNext());
    }

    /**
     * Проверка выпадения исключения при попытке получить значение из итератора, в котором уже нет элементов.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenItersOutOfMapThenGetException() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        Iterator<Node> iterator = map.iterator();
        SimpleHashMap.Node firstNode = iterator.next();
    }
}