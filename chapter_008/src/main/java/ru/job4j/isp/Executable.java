package ru.job4j.isp;

/**
 * Интерфейс описывает выполняющийся пункт меню.
 */
public interface Executable {

    /**
     * Определить поведение пункта при выборе в меню.
     */
    void execute();
}