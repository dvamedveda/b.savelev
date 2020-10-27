package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализует простой пункт меню.
 */
public class SimpleMenuItem implements MenuItem {

    /**
     * Название пункта меню.
     */
    private String name;

    /**
     * Потомки пункта меню.
     */
    private List<MenuItem> childs = new ArrayList<>();

    /**
     * Действие пункта меню.
     */
    private String action;

    /**
     * Создать пункт меню с заданным действием.
     *
     * @param name   название пункта меню.
     * @param action действие для выполнения при выборе пункта в меню.
     */
    public SimpleMenuItem(String name, String action) {
        this.name = name;
        this.action = action;
    }

    /**
     * Получить имя меню.
     *
     * @return имя меню.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Добавить потомка итема.
     *
     * @param item итем-потомок данного итема.
     */
    @Override
    public void add(MenuItem item) {
        this.childs.add(item);
    }

    /**
     * Получить список прямых потомков итема.
     *
     * @return список прямых потомков.
     */
    @Override
    public List<MenuItem> getChilds() {
        return new ArrayList<>(this.childs);
    }

    /**
     * Определить поведение пункта при выборе в меню.
     */
    @Override
    public void execute() {
        System.out.println(this.action);
    }

    /**
     * Напечатать итем.
     *
     * @param indent отступ для печати, если требуется.
     */
    @Override
    public void printSelf(String indent) {
        System.out.println(indent + this.getName());
    }

    /**
     * Напечатать потомков итема.
     *
     * @param indent отступ при печати
     */
    @Override
    public void printChilds(String indent) {
        for (MenuItem item : this.getChilds()) {
            item.printSelf(indent);
            item.printChilds(indent + indent);
        }
    }

    /**
     * Получить пункт меню по имени, если он содержится в данном пункте меню или потомках.
     *
     * @param name название пункта меню.
     * @return пункт меню или null, если пункта меню с таким названием не найдено.
     */
    @Override
    public MenuItem getIfContains(String name) {
        MenuItem result = null;
        if (this.name.equals(name)) {
            result = this;
        }
        if (result == null) {
            for (MenuItem item : this.childs) {
                result = item.getIfContains(name);
                if (result != null) {
                    break;
                }
            }
        }
        return result;
    }
}