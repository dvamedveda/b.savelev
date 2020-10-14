package ru.job4j.srp;

import java.util.function.Predicate;

/**
 * Интерфейс движка генерации отчета.
 * Генерирует список работников с использованием фильтра.
 */
public interface Engine {

    /**
     * Сгенерировать отчет.
     *
     * @param filter фильтр для отбора сотрудников в отчет.
     * @return строка содержащая отчет.
     */
    String generate(Predicate<Employee> filter);
}