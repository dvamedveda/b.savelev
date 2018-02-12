package ru.job4j.sort;

import java.util.Comparator;
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

    /**
     * Сортировка списка юзеров по длине имени(по возрастанию).
     * @param list список, нуждающийся в сортировке.
     * @return отсортированный список.
     */
    public List<User> sortNameLength(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = Integer.compare(o1.getName().toCharArray().length, o2.getName().toCharArray().length);
                return result;
            }
        });
        return list;
    }

    /**
     * Сортировка списка юзеров имени(по возрастанию), затем по возрасту.
     * @param list список, нуждающийся в сортировке.
     * @return отсортированный список.
     */
    public List<User> sortByAllFields(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = o1.getName().compareTo(o2.getName());
                return result != 0 ? result : Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        return list;
    }
}