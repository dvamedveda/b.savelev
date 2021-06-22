package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Демонстрация работы с сущностью при помощи HQL.
 * Важно понимать, что ручные манипуляции с бд рассинхронизовывают хранящиеся в кэше сущности.
 */
public class HiberRun {

    public static void main(String[] args) {

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hql.hibernate.cfg.xml").build();
        try {
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            /*
            Сохраняем трех кандидатов. Также выполняем принудительный сброс контекста в бд, чтобы хибернейт не затер
            наши ручные изменения запросами в бд при коммите и закрытии сессии.
             */
            Candidate candidateOne = new Candidate("Ivan", "Junior", 10000);
            Candidate candidateTwo = new Candidate("Vasiliy", "Middle", 20000);
            Candidate candidateThree = new Candidate("Petr", "Senior", 30000);
            session.save(candidateOne);
            session.save(candidateTwo);
            session.save(candidateThree);
            session.flush();

            //Выполняем выборку в hql всех сущностей
            List<Candidate> allCandidates = session.createQuery("from Candidate").getResultList();
            System.out.println("All candidates: ");
            for (Candidate candidate : allCandidates) {
                System.out.println(candidate);
            }
            System.out.println("--------------------");

            //Выполняем выборку сущности в hql по id
            Candidate byId = (Candidate) session
                    .createQuery("from Candidate c where c.id = :cId")
                    .setParameter("cId", candidateOne.getId())
                    .getSingleResult();
            System.out.println("By id: ");
            System.out.println(byId);
            System.out.println("--------------------");

            //Выполняем выборку сущности в hql по имени
            Candidate byName = (Candidate) session
                    .createQuery("from Candidate c where c.name = :cName")
                    .setParameter("cName", candidateThree.getName())
                    .getSingleResult();
            System.out.println("By name: ");
            System.out.println(byName);
            System.out.println("--------------------");

            /*
            Выполняем обновление данных в базе при помощи hql.
            Также предварительно ощищаем кэш первого уровня, чтобы при следующей загрузке сущностей они заново загрузились из бд.
            Если этого не сделать, данные сущностей в кэше и в бд окажутся рассинхронизированными,
            а обновление может быть утеряно, когда сущность из кэша будет изменена, и как следствие - сохранена.
             */
            session.clear();
            session.createQuery("update Candidate c set c.experience = :level where c.name = :cName")
                    .setParameter("level", "Middle").setParameter("cName", candidateOne.getName()).executeUpdate();
            List<Candidate> allAfterUpdate = session.createQuery("from Candidate").getResultList();
            System.out.println("After update: ");
            for (Candidate candidate : allAfterUpdate) {
                System.out.println(candidate);
            }
            System.out.println("--------------------");

            /*
            Выполняем удаление данных в базе при помощи hql.
            Также предварительно ощищаем кэш первого уровня, чтобы при следующей загрузке сущностей они заново загрузились из бд.
            Если этого не сделать, данные сущностей в кэше и в бд окажутся рассинхронизированными,
            а обновление может быть утеряно, когда сущность из кэша будет изменена, и как следствие - сохранена.
             */
            session.clear();
            session.createQuery("delete from Candidate c where c.id = :cId")
                    .setParameter("cId", candidateTwo.getId()).executeUpdate();
            List<Candidate> allAfterDelete = session.createQuery("from Candidate").getResultList();
            System.out.println("After delete: ");
            for (Candidate candidate : allAfterDelete) {
                System.out.println(candidate);
            }
            System.out.println("--------------------");

            /*
            Закрываем сессию. Так как с последнего сброса данных контекста сущности не менялись,
            то при коммите ничего не записывается.
             */
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}