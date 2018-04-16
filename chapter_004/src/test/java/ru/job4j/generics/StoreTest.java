package ru.job4j.generics;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

/**
 * Тесты классов AbstractStore и его наследников.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class StoreTest {

    /**
     * Проверка, что наследник абстрактного хранилища UserStore может хранить объекты типа User.
     */
    @Test
    public void whenCreateUserStoreThenCreationSuccess() {
        UserStore store = new UserStore();
        User user1 = new User("1");
        User user2 = new User("2");
        User user3 = new User("3");
        store.add(user1);
        store.add(user2);
        store.add(user3);
        Assert.assertThat(user1, is(store.findBy("1")));
        Assert.assertThat(user2, is(store.findBy("2")));
        Assert.assertThat(user3, is(store.findBy("3")));
    }

    /**
     * Проверка, что наследник абстрактного хранилища RoleStore может хранить объекты типа User.
     */
    @Test
    public void whenCreateRoleStoreThenCreationSuccess() {
        RoleStore store = new RoleStore();
        Role role1 = new Role("1");
        Role role2 = new Role("2");
        Role role3 = new Role("3");
        store.add(role1);
        store.add(role2);
        store.add(role3);
        Assert.assertThat(role1, is(store.findBy("1")));
        Assert.assertThat(role2, is(store.findBy("2")));
        Assert.assertThat(role3, is(store.findBy("3")));
    }

    /**
     * Проверка, что наследник абстрактного хранилища выполняет замену объекта по валидному идентификатору.
     */
    @Test
    public void whenReplaceUserWithValidIdThenReplaceIsTrue() {
        UserStore store = new UserStore();
        User user1 = new User("1");
        User user2 = new User("2");
        User user3 = new User("3");
        User user4 = new User("111");
        store.add(user1);
        store.add(user2);
        store.add(user3);
        Assert.assertTrue(store.replace("1", user4));
        Assert.assertThat(user4, is(store.findBy("111")));
        Assert.assertThat(user2, is(store.findBy("2")));
        Assert.assertThat(user3, is(store.findBy("3")));
    }

    /**
     * Проверка, что наследник абстрактного хранилища не выполняет замену объекта по невалидному идентификатору.
     */
    @Test
    public void whenReplaceUserWithNotValidIdThenReplaceIsFalse() {
        UserStore store = new UserStore();
        User user1 = new User("1");
        User user2 = new User("2");
        User user3 = new User("3");
        User user4 = new User("123");
        store.add(user1);
        store.add(user2);
        store.add(user3);
        Assert.assertFalse(store.replace("123", user4));
        Assert.assertThat(user1, is(store.findBy("1")));
        Assert.assertThat(user2, is(store.findBy("2")));
        Assert.assertThat(user3, is(store.findBy("3")));
    }

    /**
     * Проверка, что наследник абстрактного хранилища выполняет удаление объекта по валидному идентификатору.
     */
    @Test
    public void whenDeleteUserByValidIdThenDeleteIsTrue() {
        UserStore store = new UserStore();
        User user1 = new User("1");
        User user2 = new User("2");
        User user3 = new User("3");
        store.add(user1);
        store.add(user2);
        store.add(user3);
        Assert.assertTrue(store.delete("1"));
        Assert.assertThat(user2, is(store.findBy("2")));
        Assert.assertThat(user3, is(store.findBy("3")));
        Assert.assertNull(store.findBy("1"));
    }

    /**
     * Проверка, что наследник абстрактного хранилища выполняет удаление объекта по невалидному идентификатору.
     */
    @Test
    public void whenDeleteUserByNotValidIdThenDeleteIsFalse() {
        UserStore store = new UserStore();
        User user1 = new User("1");
        User user2 = new User("2");
        User user3 = new User("3");
        store.add(user1);
        store.add(user2);
        store.add(user3);
        Assert.assertFalse(store.delete("123"));
        Assert.assertThat(user1, is(store.findBy("1")));
        Assert.assertThat(user2, is(store.findBy("2")));
        Assert.assertThat(user3, is(store.findBy("3")));
    }
}