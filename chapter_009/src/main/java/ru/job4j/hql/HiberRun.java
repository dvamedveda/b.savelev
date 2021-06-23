package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Демонстрация работы с сущностью при помощи HQL.
 * Рассматривается механизм изменения стратегии загрузки при запросе.
 */
public class HiberRun {

    public static void main(String[] args) {
        List<Candidate> result = null;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hql.hibernate.cfg.xml").build();
        try {
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            /*
            Сохраняем трех кандидатов с их персональными списками вакансий.
            Затем заполняем их списки вакансиями.
             */
            Candidate candidateOne = new Candidate("Ivan", "Junior", 10000, new VacancyList("Ivan vacancies list"));
            Candidate candidateTwo = new Candidate("Vasiliy", "Middle", 20000, new VacancyList("Vasiliy vacancies list"));
            Candidate candidateThree = new Candidate("Petr", "Senior", 30000, new VacancyList("Petr vacancies list"));
            Candidate candidateFour = new Candidate("Boris", "Intern", 5000, new VacancyList("Boris vacancies list"));
            session.save(candidateOne);
            session.save(candidateTwo);
            session.save(candidateThree);
            session.save(candidateFour);

            candidateTwo.getVacancyList().addVacancy(new Vacancy("Middle to Sber", 10000));
            candidateTwo.getVacancyList().addVacancy(new Vacancy("Middle to Yandex", 12000));
            candidateThree.getVacancyList().addVacancy(new Vacancy("Middle to Yandex", 15000));
            candidateThree.getVacancyList().addVacancy(new Vacancy("Senoir to LANIT", 16000));
            candidateThree.getVacancyList().addVacancy(new Vacancy("Senior to Tinkoff", 20000));
            candidateOne.getVacancyList().addVacancy(new Vacancy("Junior to T-System", 9000));

            session.save(candidateOne);
            session.save(candidateTwo);
            session.save(candidateThree);

            /*
            Получаем список всех кандидатов с их вакансиями.
             */
            result = session.createQuery("select distinct c from Candidate c "
                    + "join fetch c.vacancyList l "
                    + "left join fetch l.vacancies v "
                    + "order by c.id", Candidate.class).list();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

        /*
        Выводим на печать список всех кандидатов с их вакансиями.
         */
        for (Candidate candidate : result) {
            System.out.println(candidate);
        }
    }
}