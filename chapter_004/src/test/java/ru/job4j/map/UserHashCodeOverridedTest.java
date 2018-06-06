package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Тесты класса UserHashCodeOverridedTest.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class UserHashCodeOverridedTest {

    /**
     * Здесь проверяется поведение карты,
     * если в нее добавляются объекты, у которых
     * переопределен HashCode.
     */
    @Test
    public void whenOverridedOnlyHashCodeThenPrintsOneUser() {
        User first = new UserHashCodeOverrided("Dima", 21, Calendar.getInstance());
        User second = new UserHashCodeOverrided("Dima", 21, Calendar.getInstance());
        Map<User, Object> users = new HashMap<>();
        users.put(first, new Object());
        users.put(second, new Object());

        System.out.println(users);
    }
}