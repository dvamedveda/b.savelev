package ru.job4j.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Демонстрация работы хибернейта с отношением many-to-many.
 */
public class HiberRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("manytomany.hibernate.cfg.xml").build();
        try {
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

//            Book one = new Book("Книга про медицину", 1950);
//            Book two = new Book("Книга про космонавтику", 1954);
//            Book three = new Book("Энциклопедия", 1960);
//            Author first = new Author("Иванов Иван Иванович");
//            Author second = new Author("Петров Петр Петрович");
//            Author third = new Author("Сидоров Сидор Сидорович");
//            first.getBooks().add(one);
//            first.getBooks().add(two);
//            first.getBooks().add(three);
//            second.getBooks().add(one);
//            second.getBooks().add(three);
//            third.getBooks().add(three);
//            session.persist(first);
//            session.persist(second);
//            session.persist(third);
            Author author = session.get(Author.class, 2);
            session.remove(author);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}