package ru.job4j.tracker.tracker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Класс, описывающий трекер и его методы для работы с заявками.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Tracker {
    /**
     * Список заявок
     */
    private final ArrayList<Item> items = new ArrayList<>();

    /**
     * Специальный метод, генерирующий уникальный ID для завки.
     * Используется только при добавлении заявок.
     *
     * @return уникальный id.
     */
    private String generateId() {
        Random randomizer = new Random();
        long time = System.currentTimeMillis();
        int randomInt = randomizer.nextInt(100);
        return Integer.toString(randomInt) + Long.toString(time);
    }

    /**
     * Добавление заявки в список всех существующих заявок.
     *
     * @param item новая заявка, которой нужно присвоить уникальный идентификатор и сохранить в списке заявок.
     * @return добавленная в трекер заявка.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Обновление заявки.
     * Это не замена заявки в трекере, а обновление полей заявки с сохранением ее ID.
     *
     * @param item заявка, которую требуется обновить.
     */
    public void update(Item item) {
        for (Item nextItem : this.items) {
            if (nextItem != null && nextItem.getId().equals(item.getId())) {
                nextItem.setCreated(item.getCreated());
                nextItem.setDescription(item.getDescription());
                nextItem.setSummary(item.getSummary());
            }
        }
    }

    /**
     * Удаление заявки из трекера.
     *
     * @param item заявка, которую требуется удалить.
     */
    public void delete(Item item) {
        Item nextItem;
        Iterator<Item> iter = this.items.iterator();
        while (iter.hasNext()) {
            nextItem = iter.next();
            if (nextItem.getId().equals(item.getId())) {
                iter.remove();
            }
        }
    }

    /**
     * Получение списка всех существующих заявок в трекере.
     *
     * @return список заявок
     */
    public ArrayList<Item> findAll() {
        return this.items;

    }

    /**
     * Поиск заявок по имени.
     * Так как имя заявки - неуникально, заявок может быть несколько, поэтому возвращается список совпадающих заявок.
     *
     * @param name имя, по которому нужно отыскать заявку.
     * @return список заявок с подходящим именем.
     */
    public ArrayList<Item> findByName(String name) {
        ArrayList<Item> result = new ArrayList<>();
        for (Item nextItem : this.items) {
            if (nextItem.getSummary().equals(name)) {
                result.add(nextItem);
            }
        }
        return result.size() > 0 ? result : null;
    }

    /**
     * Поиск заявки по ID.
     *
     * @param id идентификатор заявки, которую требуется найти.
     * @return заявка с искомым id.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item.getId().equals(id)) {
                result = item;
            }
        }
        return result;
    }
}