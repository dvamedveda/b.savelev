package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, реализующий меню.
 */
public class Menu {

    /**
     * Список для хранения пунктов меню.
     */
    private List<MenuItem> items;

    /**
     * Конструктор для создания меню из нескольких готовых пунктов.
     *
     * @param menuItems готовые пункты меню.
     */
    public Menu(MenuItem... menuItems) {
        this.items = new ArrayList<>();
        for (MenuItem menuItem : menuItems) {
            this.items.add(menuItem);
        }
    }

    /**
     * Метод для добавления нового пункта меню.
     *
     * @param item новый пункт меню.
     */
    public void add(MenuItem item) {
        this.items.add(item);
    }

    /**
     * Напечатать все меню.
     */
    public void printMenu() {
        for (MenuItem item : this.items) {
            item.printSelf("");
            item.printChilds("--");
        }
    }

    /**
     * Выбрать пункт меню по названию.
     *
     * @param name название пункта меню.
     */
    public void select(String name) {
        MenuItem menuItem = null;
        for (MenuItem item : this.items) {
            menuItem = item.getIfContains(name);
            if (menuItem != null) {
                break;
            }
        }
        if (menuItem != null) {
            menuItem.execute();
        } else {
            System.out.println("Menu item with name " + name + " not found!");
        }
    }
}