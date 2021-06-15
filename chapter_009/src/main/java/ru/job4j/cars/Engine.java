package ru.job4j.cars;

import javax.persistence.*;

/**
 * Сущность Двигатель.
 */
@Entity
@Table(name = "engine")
public class Engine {

    /**
     * Идентификатор двигателя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Название модели двигателя.
     */
    @Column(unique = true)
    private String model;

    public Engine() {

    }

    public Engine(String model) {
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "id=" + id
                + ", model='" + model + '\''
                + '}';
    }
}