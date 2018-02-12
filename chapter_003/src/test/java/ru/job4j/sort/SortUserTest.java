package ru.job4j.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * Тест проверяет сортировку списка по длине имени(по возрастанию).
     */
    @Test
    public void whenSortListByNameLengthThenGetListSortedByNameLength() {
        ArrayList<User> users = new ArrayList<>();
        User one = new User("Vasiliy", 45);
        User two = new User("Boris", 29);
        User three = new User("Ira", 32);
        User four = new User("Ivan", 29);
        users.add(one);
        users.add(two);
        users.add(three);
        users.add(four);
        List<User> expected = new ArrayList<>();
        expected.add(three);
        expected.add(four);
        expected.add(two);
        expected.add(one);
        SortUser sortUser = new SortUser();
        List<User> result = sortUser.sortNameLength(users);
        Assert.assertThat(result, is(expected));
    }

    /**
     * Тест проверяет сортировку списка по длине имени(по возрастанию) в случае, если есть именя одинаковой длины.
     */
    @Test
    public void whenSortListByNameLengthWithDupsThenGetListSortedByNameLength() {
        ArrayList<User> users = new ArrayList<>();
        User one = new User("Vasiliy", 45);
        User two = new User("Boris", 29);
        User three = new User("Ira", 32);
        User four = new User("Ivan", 29);
        User five = new User("Nikolay", 45);
        users.add(one);
        users.add(two);
        users.add(three);
        users.add(four);
        users.add(five);
        List<User> expected = new ArrayList<>();
        expected.add(three);
        expected.add(four);
        expected.add(two);
        expected.add(one);
        expected.add(five);
        SortUser sortUser = new SortUser();
        List<User> result = sortUser.sortNameLength(users);
        Assert.assertThat(result, is(expected));
    }

    /**
     * Тест проверяет сортировку списка юзеров по именам лексикографически, в случае, если одинаковых имен нет.
     */
    @Test
    public void whenSortListByAllFieldsWithoutDupNamesThenGetListSortedByNames() {
        ArrayList<User> users = new ArrayList<>();
        User one = new User("Vasiliy", 45);
        User two = new User("Boris", 29);
        User three = new User("Ira", 32);
        User four = new User("Ivan", 29);
        User five = new User("Nikolay", 45);
        users.add(one);
        users.add(two);
        users.add(three);
        users.add(four);
        users.add(five);
        List<User> expected = new ArrayList<>();
        expected.add(two);
        expected.add(three);
        expected.add(four);
        expected.add(five);
        expected.add(one);
        SortUser sortUser = new SortUser();
        List<User> result = sortUser.sortByAllFields(users);
        Assert.assertThat(result, is(expected));
    }

    /**
     * Тест проверяет сортировку списка юзеров по именам лексикографически.
     * Если в списке есть одинаковые имена, то дополнительно список сортируется по возрасту.
     */
    @Test
    public void whenSortListByAllFieldsWithDupNamesThenGetListSortedByAllFields() {
        ArrayList<User> users = new ArrayList<>();
        User one = new User("Ivan", 45);
        User two = new User("Boris", 29);
        User three = new User("Ivan", 29);
        User four = new User("Boris", 45);
        User five = new User("Ira", 64);
        users.add(one);
        users.add(two);
        users.add(three);
        users.add(four);
        users.add(five);
        List<User> expected = new ArrayList<>();
        expected.add(two);
        expected.add(four);
        expected.add(five);
        expected.add(three);
        expected.add(one);
        SortUser sortUser = new SortUser();
        List<User> result = sortUser.sortByAllFields(users);
        Assert.assertThat(result, is(expected));
    }
}