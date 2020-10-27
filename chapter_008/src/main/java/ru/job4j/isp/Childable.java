package ru.job4j.isp;

import java.util.List;

/**
 * Интерфейс, описывающий итем, имеющий потомков.
 */
public interface Childable {

    /**
     * Получить список прямых потомков итема.
     *
     * @return список прямых потомков.
     */
    List<MenuItem> getChilds();

    /**
     * Напечатать потомков итема.
     *
     * @param indent отступ при печати
     */
    void printChilds(String indent);

    /**
     * Добавить потомка итема.
     *
     * @param item итем-потомок данного итема.
     */
    void add(MenuItem item);
}