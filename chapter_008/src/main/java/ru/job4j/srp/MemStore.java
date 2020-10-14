package ru.job4j.srp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Реализация хранилища в памяти.
 */
public class MemStore implements Store {
    private final List<Employee> employees = new ArrayList<>();

    /**
     * Добавить сотрудника в хранилище.
     *
     * @param employee объект сотрудника.
     */
    public void add(Employee employee) {
        employees.add(employee);
    }

    /**
     * Найти список сотрудников в хранилище.
     *
     * @param filter  объект фильтра.
     * @param sorting объект сортировки.
     * @return список сотрудников.
     */
    public List<Employee> findBy(Predicate<Employee> filter, Comparator<Employee> sorting) {
        List<Employee> result = employees.stream().filter(filter).sorted(sorting).collect(Collectors.toList());
        return result;
    }
}