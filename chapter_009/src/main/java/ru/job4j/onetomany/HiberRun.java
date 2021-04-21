package ru.job4j.onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Демонстрация добавления моделей машины в список для марки машины.
 */
public class HiberRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("onetomany.hibernate.xml.cfg").build();
        try {
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Model accord = new Model("Accord");
            session.save(accord);
            Model inspire = new Model("Inspire");
            session.save(inspire);
            Model fit = new Model("Fit");
            session.save(fit);
            Model civic = new Model("Civic");
            session.save(civic);
            Model crv = new Model("CR-V");
            session.save(crv);
            Brand brand = new Brand("Honda");
            brand.addModel(accord);
            brand.addModel(inspire);
            brand.addModel(fit);
            brand.addModel(civic);
            brand.addModel(crv);
            session.save(brand);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}