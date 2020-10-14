package ru.job4j.srp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Интерфейс хранилища.
 */
public interface Store {

    /**
     * Искать сотрудников по фильтру, используя переданную сортировку.
     *
     * @param filter объект фильтра.
     * @param sort   объект компаратора.
     * @return список сотрудников.
     */
    List<Employee> findBy(Predicate<Employee> filter, Comparator<Employee> sort);
}