package ru.job4j.srp;

import java.util.Comparator;

/**
 * Класс, хранящий сортировки, требуемые для отчетов.
 */
public class Sort {

    /**
     * Сортировка по уменьшению зарплат сотрудников.
     *
     * @return объект компаратора.
     */
    public static Comparator<Employee> bySalaryDesc() {
        return (o1, o2) -> {
            int result;
            if (o1.getSalary() == o2.getSalary()) {
                result = 0;
            } else {
                result = o1.getSalary() < o2.getSalary() ? 1 : -1;
            }
            return result;
        };
    }

    /**
     * Сортировка по умолчанию (без сортировки)
     *
     * @return объект компаратора.
     */
    public static Comparator<Employee> withoutSort() {
        return (o1, o2) -> 0;
    }
}