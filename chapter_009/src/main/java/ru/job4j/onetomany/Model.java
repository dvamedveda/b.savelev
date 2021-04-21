package ru.job4j.onetomany;

import javax.persistence.*;

/**
 * Модель данных для модели машины.
 */
@Entity
@Table(name = "model")
public class Model {

    /**
     * Идентификатор модели машины.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Название модели машины.
     */
    @Column
    private String name;

    public Model(String name) {
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
}