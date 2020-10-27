package ru.job4j.isp;

/**
 * Интерфейс, описывающий пункт меню, который может выполняться,
 * распечатываться, иметь потомков и методы для работы с потомками.
 */
public interface MenuItem extends Executable, Childable, Printable {

    /**
     * Получить имя меню.
     *
     * @return имя меню.
     */
    String getName();

    /**
     * Получить пункт меню по имени, если он содержится в данном пункте меню.
     *
     * @param name название пункта меню.
     * @return пункт меню или null, если пункта меню с таким названием не найдено.
     */
    MenuItem getIfContains(String name);
}