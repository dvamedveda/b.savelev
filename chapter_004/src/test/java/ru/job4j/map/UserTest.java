package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Тесты класса User.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class UserTest {

    /**
     * Здесь проверяется поведение карты,
     * если в нее добавляются объекты, у которых не
     * переопределен ни Equals, ни HashCode.
     */
    @Test
    public void whenNotOverrideHashCodeAndEqualsThenPrintsTwoUser() {
        User first = new User("Vasiliy", 21, Calendar.getInstance());
        User second = new User("Vasiliy", 21, Calendar.getInstance());
        Map<User, Object> users = new HashMap<>();
        users.put(first, new Object());
        users.put(second, new Object());

        System.out.println(users);
    }
}