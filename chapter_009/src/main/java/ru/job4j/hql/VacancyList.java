package ru.job4j.hql;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Сущность База вакансий кандидата.
 * Хранит в себе вакансии, которые подходят кандидату.
 */
@Entity
@Table(name = "vacancy_lists")
public class VacancyList {

    /**
     * Идентификатор базы вакансий.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Название базы вакансий.
     */
    @Column
    private String name;

    /**
     * Список вакансий в базе.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Vacancy> vacancies = new ArrayList<>();

    public VacancyList() {
    }

    public VacancyList(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void addVacancy(Vacancy vacancy) {
        this.vacancies.add(vacancy);
    }

    @Override
    public String toString() {
        return "VacancyList{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", vacancies=" + vacancies
                + '}';
    }
}