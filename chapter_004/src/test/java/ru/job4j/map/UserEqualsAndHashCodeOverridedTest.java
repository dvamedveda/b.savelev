package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Тесты класса UserEqualsAndHashCodeOverrided.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class UserEqualsAndHashCodeOverridedTest {

    /**
     * Здесь проверяется поведение карты,
     * если в нее добавляются объекты, у которых
     * переопределены и equals(), и hashCode().
     */
    @Test
    public void whenOverridedEqualsAndHashCodeThenPrintsOneUser() {
        Calendar birthday = Calendar.getInstance();
        User first = new UserEqualsAndHashCodeOverrided("Dima", 21, birthday);
        User second = new UserEqualsAndHashCodeOverrided("Dima", 21, birthday);
        Map<User, Object> users = new HashMap<>();
        users.put(first, new Object());
        users.put(second, new Object());

        System.out.println(users);
    }
}