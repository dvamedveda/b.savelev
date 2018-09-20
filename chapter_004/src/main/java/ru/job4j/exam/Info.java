package ru.job4j.exam;

import java.util.HashMap;
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
     *
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
     *
     * @param prev предыдущая коллекция.
     * @param curr текущая коллекция.
     */
    private void getStatistics(List<Store.User> prev, List<Store.User> curr) {
        HashMap<Integer, String> previous = new HashMap<>();
        HashMap<Integer, String> current = new HashMap<>();
        for (Store.User cUser : curr) {
            current.put(cUser.getId(), cUser.getName());
        }
        for (Store.User pUser : prev) {
            previous.put(pUser.getId(), pUser.getName());
        }
        for (Store.User pUser : prev) {
            if (!current.containsKey(pUser.getId())) {
                removed++;
            }
        }
        for (Store.User cUser : curr) {
            if (cUser.isChanged()) {
                changed++;
            }
            if (!previous.containsKey(cUser.getId())) {
                added++;
            }
        }
    }

    /**
     * Выводим на печать дифф по двум коллекциям.
     *
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
