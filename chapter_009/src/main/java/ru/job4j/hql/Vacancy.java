package ru.job4j.hql;

import javax.persistence.*;

/**
 * Сущность Вакансия.
 */
@Entity
@Table(name = "vacancies")
public class Vacancy {

    /**
     * Идентификатор вакансии.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Описание вакансии.
     */
    @Column
    private String description;

    /**
     * Максимальная зарплата на вакансии.
     */
    @Column
    private int maximumSalary;

    public Vacancy() {
    }

    public Vacancy(String description, int maximumSalary) {
        this.description = description;
        this.maximumSalary = maximumSalary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaximumSalary() {
        return maximumSalary;
    }

    public void setMaximumSalary(int maximumSalary) {
        this.maximumSalary = maximumSalary;
    }

    @Override
    public String toString() {
        return "Vacancy{"
                + "id=" + id
                + ", description='" + description + '\''
                + ", maximumSalary=" + maximumSalary
                + '}';
    }
}