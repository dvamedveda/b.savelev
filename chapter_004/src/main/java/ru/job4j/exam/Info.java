package ru.job4j.exam;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, который реализует объект, содержащий диф по коллекциям.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Info {
    /**
     * Добавлено пользователей.
     */
    private int added;

    /**
     * Удалено пользователей.
     */
    private int removed;

    /**
     * Изменено пользователей.
     */
    private int changed;

    /**
     * Конструктор объекта, содержащего дифф по коллекциям, диф подсчитывается сразу.
     * @param prev предыдущая коллекция.
     * @param curr текущая коллекция.
     */
    public Info(List<Store.User> prev, List<Store.User> curr) {
        this.added = 0;
        this.removed = 0;
        this.changed = 0;
        this.getStatistics(prev, curr);
    }

    /**
     * Внутренний метод для подсчета диффа по коллекциям.
     * @param prev предыдущая коллекциям.
     * @param curr текущая коллекция.
     */
    private void getStatistics(List<Store.User> prev, List<Store.User> curr) {
        for (Store.User user : curr) {
            if (user.isChanged()) {
                changed++;
            }
        }
        List<Store.User> temp = new ArrayList<>(prev);
        temp.removeAll(curr);
        removed = temp.size();
        curr.removeAll(prev);
        added = curr.size();
    }

    /**
     * Выводим на печать дифф по двум коллекциям.
     * @return строка диффа для печати
     */
    @Override
    public String toString() {
        return String.format(
                "Statistics: added - %d, removed - %d, changed - %d.",
                this.added,
                this.removed,
                this.changed);
    }
}
