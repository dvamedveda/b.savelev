package ru.job4j.sort;

import java.util.List;
import java.util.TreeSet;

/**
 * Класс, содержащий методы сортировки коллекций.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class SortUser {

    /**
     * Сортировка списка юзеров в упорядоченное множество.
     * Сортировка происходит по возрасту в порядке возрастания.
     * Если возраст одинаковый, сортируется по имени.
     *
     * @param list список объектов User.
     * @return отсортированное множество.
     */
    public TreeSet<User> sort(List<User> list) {
        TreeSet<User> result = new TreeSet<>();
        for (User user : list) {
            result.add(user);
        }
        return result;
    }
}