package ru.job4j.process;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Класс содержащий тесты класса UserConvert.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class UserConverTest {

    /**
     * Тест проверяет конвертацию списка юзеров в мапу.
     */
    @Test
    public void whenGiveListThenGetMap() {
        List<User> toConvert = new ArrayList<>();
        User one = new User(1, "Boris", "Bishkek");
        User two = new User(2, "Petr", "Bryansk");
        User three = new User(3, "Sergey", "Moscow");
        toConvert.add(one);
        toConvert.add(two);
        toConvert.add(three);
        HashMap<Integer, User> result = new UserConvert().process(toConvert);
        Assert.assertTrue(result.get(1).equals(one));
        Assert.assertTrue(result.get(2).equals(two));
        Assert.assertTrue(result.get(3).equals(three));
    }
}
