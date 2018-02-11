package ru.job4j.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;

/**
 * Класс содержащий тесты класса SortUser.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class SortUserTest {

    /**
     * Проверяется сортировка простого списка, без одинаковых возрастов.
     */
    @Test
    public void whenSortListThenGetSetSortedByAge() {
        ArrayList<User> users = new ArrayList<>();
        User one = new User("Vasiliy", 45);
        User two = new User("Boris", 29);
        User three = new User("Nikolay", 32);
        User four = new User("Roman", 68);
        users.add(one);
        users.add(two);
        users.add(three);
        users.add(four);
        TreeSet<User> expected = new TreeSet<>();
        expected.add(two);
        expected.add(three);
        expected.add(one);
        expected.add(four);
        SortUser sortUser = new SortUser();
        TreeSet<User> result = sortUser.sort(users);
        Assert.assertThat(result, is(expected));
    }

    /**
     * Проверяется сортировка списка, в котором есть разные юзеры одного возраста.
     */
    @Test
    public void whenSortListWithDuplicatedAgeThenGetSetSortedByNameToo() {
        ArrayList<User> users = new ArrayList<>();
        User one = new User("Vasiliy", 45);
        User two = new User("Boris", 29);
        User three = new User("Nikolay", 32);
        User four = new User("Roman", 29);
        users.add(one);
        users.add(two);
        users.add(three);
        users.add(four);
        TreeSet<User> expected = new TreeSet<>();
        expected.add(two);
        expected.add(four);
        expected.add(three);
        expected.add(one);
        SortUser sortUser = new SortUser();
        TreeSet<User> result = sortUser.sort(users);
        Assert.assertThat(result, is(expected));
    }
}