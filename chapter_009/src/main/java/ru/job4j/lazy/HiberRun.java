package ru.job4j.lazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Демонстрация обхода LazyInitializationException.
 */
public class HiberRun {
    public static void main(String[] args) {
        List<Brand> list = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("lazy.hibernate.cfg.xml").build();
        try {
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
//            Brand brandOne = new Brand("Toyota");
//            Model modelOne = new Model("Ipsum", brandOne);
//            brandOne.getModels().add(modelOne);
//            Model modelTwo = new Model("Celica", brandOne);
//            brandOne.getModels().add(modelTwo);
//            Model modelThree = new Model("Avensis", brandOne);
//            brandOne.getModels().add(modelThree);
//            session.save(modelOne);
//            session.save(modelTwo);
//            session.save(modelThree);
//            session.save(brandOne);
            list = session.createQuery("select distinct b from Brand b join fetch b.models").list();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (Brand brand : list) {
            for (Model model : brand.getModels()) {
                System.out.println(model);
            }
        }
    }
}