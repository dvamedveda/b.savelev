package ru.job4j.hql;


import javax.persistence.*;

/**
 * Сущность Кандидат.
 */
@Entity
@Table(name = "candidate")
public class Candidate {

    /**
     * Идентификатор сущности.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Имя кандидата.
     */
    @Column
    private String name;

    /**
     * Уровень знаний кандидата.
     */
    @Column
    private String experience;

    /**
     * Зарплата кандидата.
     */
    @Column
    private int salary;

    public Candidate() {
    }

    public Candidate(String name, String experience, int salary) {
        this.name = name;
        this.experience = experience;
        this.salary = salary;
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

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Candidate{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", experience='" + experience + '\''
                + ", salary=" + salary + '}';
    }
}