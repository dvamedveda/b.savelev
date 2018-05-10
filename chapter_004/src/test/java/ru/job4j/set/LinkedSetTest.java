package ru.job4j.set;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;

/**
 * Тесты класса LinkedSet.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class LinkedSetTest {

    /**
     * Проверка на успешное добавление данных во множество.
     */
    @Test
    public void whenAddMoreElementsThenSuccessAdding() {
        LinkedSet<Integer> set = new LinkedSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        Iterator it = set.iterator();
        Assert.assertThat(1, is(it.next()));
        Assert.assertThat(2, is(it.next()));
        Assert.assertThat(3, is(it.next()));
        Assert.assertThat(4, is(it.next()));
        Assert.assertThat(5, is(it.next()));
    }

    /**
     * Проверка на корректную итерацию по множеству.
     */
    @Test
    public void whenIterateOverSetThenItersCorrect() {
        LinkedSet<Integer> set = new LinkedSet<>();
        for (int i = 1; i < 6; i++) {
            set.add(i);
        }
        Iterator it = set.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertThat(1, is(it.next()));
        Assert.assertTrue(it.hasNext());
        Assert.assertThat(2, is(it.next()));
        Assert.assertTrue(it.hasNext());
        Assert.assertThat(3, is(it.next()));
        Assert.assertTrue(it.hasNext());
        Assert.assertThat(4, is(it.next()));
        Assert.assertTrue(it.hasNext());
        Assert.assertThat(5, is(it.next()));
        Assert.assertFalse(it.hasNext());
    }

    /**
     * Проверка на невозможность добавлять дубликаты во множество.
     */
    @Test
    public void whenAddDuplicatesThenAddingWithoutDuplicates() {
        LinkedSet<Integer> set = new LinkedSet<>();
        set.add(1);
        set.add(2);
        set.add(2);
        set.add(3);
        set.add(1);
        Iterator it = set.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertThat(1, is(it.next()));
        Assert.assertTrue(it.hasNext());
        Assert.assertThat(2, is(it.next()));
        Assert.assertTrue(it.hasNext());
        Assert.assertThat(3, is(it.next()));
        Assert.assertFalse(it.hasNext());
    }
}