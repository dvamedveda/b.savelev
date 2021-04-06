package ru.job4j.tracker.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Реализация трекера на Hibernate.
 */
public class HbmTracker implements Store, AutoCloseable {

    /**
     * Конфигурация hibernate.
     */
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

    /**
     * Фабрика сессий для выполнения запросов hibernate.
     */
    private final SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    /**
     * Добавление заявки в трекер.
     *
     * @param item заявка.
     * @return добавленная заявка.
     */
    @Override
    public Item add(Item item) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        }
        return item;
    }

    /**
     * Обновление содержимого заявки в трекере.
     *
     * @param id   идентификатор.
     * @param item новая заявка.
     * @return результат обновления.
     */
    @Override
    public boolean replace(String id, Item item) {
        boolean result = true;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Item updatingItem = session.get(Item.class, Integer.parseInt(id));
            session.evict(updatingItem);
            updatingItem.setName(item.getName());
            updatingItem.setDescription(item.getDescription());
            updatingItem.setCreated(item.getCreated());
            session.update(updatingItem);
            session.getTransaction().commit();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * Удаление заявки из трекера по идентификатору.
     *
     * @param id идентификатор.
     * @return результат удаления.
     */
    @Override
    public boolean delete(String id) {
        boolean result = true;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Item updatingItem = session.get(Item.class, Integer.parseInt(id));
            session.delete(updatingItem);
            session.getTransaction().commit();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * Поиск всех заявок в трекере.
     *
     * @return список всех заявок.
     */
    @Override
    public List<Item> findAll() {
        List result;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            result = session.createQuery("from ru.job4j.tracker.tracker.Item").list();
            session.getTransaction().commit();
        }
        return result;
    }

    /**
     * Поиск заявки по имени.
     *
     * @param key имя заявки.
     * @return список найденных заявок.
     */
    @Override
    public List<Item> findByName(String key) {
        List result;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            result = session.createQuery(String.format("from ru.job4j.tracker.tracker.Item i where i.name = '%s'", key)).list();
            session.getTransaction().commit();
        }
        return result;
    }

    /**
     * Поиск заявки по идентификатору.
     *
     * @param id идентификатор.
     * @return найденная заявка.
     */
    @Override
    public Item findById(String id) {
        Item result;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            result = session.get(Item.class, Integer.parseInt(id));
            session.getTransaction().commit();
        }
        return result;
    }

    /**
     * Закрытие трекера и освобождение ресурсов.
     *
     * @throws Exception исключения при закрытии трекера.
     */
    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}