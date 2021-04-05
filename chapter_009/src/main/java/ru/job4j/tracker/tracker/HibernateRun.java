package ru.job4j.tracker.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Главный метод для демонстрации работы с Hibernate
 */
public class HibernateRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Item item = createItem(new Item("Learn Hibernate", "This item about Hibernate5", 1L), sessionFactory);
            System.out.println(item);
            item.setName("Learn Hibernate 5.");
            updateItem(item, sessionFactory);
            System.out.println(item);
            Item result = findItemById(item.getId(), sessionFactory);
            System.out.println(result);
            deleteItem(result.getId(), sessionFactory);
            List<Item> list = findAll(sessionFactory);
            for (Item it : list) {
                System.out.println(it);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    /**
     * Создание заявки при помощи Hibernate.
     *
     * @param item           объект заявки.
     * @param sessionFactory фабрика сессий.
     * @return созданная заявка.
     */
    public static Item createItem(Item item, SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    /**
     * Обновление существующей заявки при помощи Hibernate.
     *
     * @param item           объект заявки.
     * @param sessionFactory фабрика сессий.
     */
    public static void updateItem(Item item, SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Удаление заявки по идентификатору.
     *
     * @param id             идентификатор.
     * @param sessionFactory фабрика сессий.
     */
    public static void deleteItem(Integer id, SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Item item = new Item(null, null, 0);
        item.setId(id);
        session.delete(item);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Поиск всех заявок в хранилище.
     *
     * @param sessionFactory фабрика сессий.
     * @return список найденных заявок.
     */
    public static List<Item> findAll(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery("from ru.job4j.tracker.tracker.Item").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    /**
     * Найти заявку по идентификатору.
     *
     * @param id             идентификатор.
     * @param sessionFactory фабрика сессий.
     * @return найденная заявка.
     */
    public static Item findItemById(Integer id, SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Item result = session.get(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }
}