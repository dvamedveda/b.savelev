package ru.job4j.process;

import java.util.HashMap;
import java.util.List;

/**
 * Класс содержащий методы для для обработки юзеров.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class UserConvert {

    /**
     * Метод конвертирует список юзеров в мапу.
     * В качестве ключа используются идентификаторы юзеров.
     *
     * @param list список юзеров.
     * @return сконвертированная мапа.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        for (User user : list) {
            result.put(user.getId(), user);
        }
        return result;
    }
}
