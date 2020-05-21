package ru.job4j.tracker.tracker;

import java.util.List;

/**
 * Интерфейс, описывающий хранилище заявок и методы для работы с ним.
 */
public interface Store extends AutoCloseable {

    /**
     * Инициализация подключения к базе данных.
     */
    void init();

    /**
     * Добавление заявки в хранилище.
     *
     * @param item заявка.
     * @return добавленная заявка.
     */
    Item add(Item item);

    /**
     * Замена заявки по ее идентификатору.
     *
     * @param id   идентификатор.
     * @param item новая заявка.
     * @return результат замены.
     */
    boolean replace(String id, Item item);

    /**
     * Удаление заявки по ее идентификатору.
     *
     * @param id идентификатор.
     * @return результат удаления.
     */
    boolean delete(String id);

    /**
     * Поиск всех заявок.
     *
     * @return список заявок.
     */
    List<Item> findAll();

    /**
     * Поиск заявки по имени.
     *
     * @param key имя заявки.
     * @return найденная заявка.
     */
    List<Item> findByName(String key);

    /**
     * Поиск заявки по идентификатору.
     *
     * @param id идентификатор.
     * @return найденная заявка.
     */
    Item findById(String id);
}