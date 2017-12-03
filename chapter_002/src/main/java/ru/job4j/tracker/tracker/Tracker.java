package ru.job4j.tracker.tracker;

import java.util.Arrays;
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
     * Массив заявок
     */
    private final Item[] items = new Item[100];

    /**
     * Первый свободный слот в масииве заявок.
     * Используется для определения, куда в массиве заявок сохранять заявку.
     */
    private int position = 0;

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
     * Добавление заявки в массив всех существующих заявок.
     *
     * @param item новая заявка, которой нужно присвоить уникальный идентификатор и сохранить в массиве заявок.
     * @return добавленная в трекер заявка.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position] = item;
        this.position++;
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
        for (int deletingIndex = 0; deletingIndex < this.items.length; deletingIndex++) {
            if (this.items[deletingIndex] != null && this.items[deletingIndex].getId().equals(item.getId())) {
                this.items[deletingIndex] = null;
            }
        }
    }

    /**
     * Получение списка всех существующих заявок в трекере.
     *
     * @return список заявок
     */
    public Item[] findAll() {
        Item[] notEmptyArray = new Item[this.items.length];
        int resultCount = 0;
        for (Item nextItem : this.items) {
            if (nextItem != null) {
                notEmptyArray[resultCount] = nextItem;
                resultCount++;
            }
        }
        return Arrays.copyOf(notEmptyArray, resultCount);

    }

    /**
     * Поиск заявок по имени.
     * Так как имя заявки - неуникально, заявок может быть несколько, поэтому возвращается массив.
     *
     * @param name имя, по которому нужно отыскать заявку.
     * @return массив заявок с подходящим именем.
     */
    public Item[] findByName(String name) {
        Item[] matches = new Item[this.items.length];
        int resultCount = 0;
        for (Item nextItem : this.items) {
            if (nextItem != null && nextItem.getSummary().equals(name)) {
                matches[resultCount] = nextItem;
                resultCount++;
            }
        }
        return resultCount > 0 ? Arrays.copyOf(matches, resultCount) : null;
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
            if (item != null && item.getId().equals(id)) {
                result = item;
            }
        }

        return result;
    }
}