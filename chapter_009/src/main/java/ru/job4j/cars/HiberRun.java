package ru.job4j.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Демонстрация работы с сущностями Владелец, Машина, Двигатель, Запись о владении.
 */
public class HiberRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("cars.hibernate.cfg.xml").build();
        try {
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            //Создаем моторы и машины.
            // Все машины изначально принадлежат системному пользователю.
            Engine engineOne = new Engine("GT-530");
            Engine engineTwo = new Engine("VTE-700");
            session.save(engineOne);
            session.save(engineTwo);
            Driver nobody = new Driver("NOBODY");
            Driver driver = new Driver("Ivan");
            session.save(nobody);
            session.save(driver);
            Car carOne = new Car("Honda", "Accord", engineOne, nobody);
            Car carTwo = new Car("Toyota", "Prius", engineTwo, nobody);
            Car carThree = new Car("Lada", "Vesta", engineTwo, nobody);
            session.save(carOne);
            session.save(carTwo);
            session.save(carThree);

            // Человек приобретает машину и при передаче она забирается у системного владельца.
            carOne.setDriver(driver);
            driver.addCar(carOne);
            nobody.removeCar(carOne);
            session.save(driver);
            session.save(nobody);

            //Проходит время, которое можно отличить по истории...
            Thread.sleep(1000);

            //Человек меняет машину. Ему присваивается другая машина, первая машина возвращается системному пользователю.
            driver.removeCar(carOne);
            carOne.setDriver(nobody);
            nobody.addCar(carOne);
            nobody.removeCar(carTwo);
            carTwo.setDriver(driver);
            driver.addCar(carTwo);
            session.save(driver);
            session.save(nobody);

            //История владения машинами человеком.
            System.out.println(driver.getHistory());
            System.out.println(driver.getCars());
            System.out.println("-----------------");
            //История владения машинами системным пользователем.
            System.out.println(nobody.getHistory());
            System.out.println(nobody.getCars());

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}