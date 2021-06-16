package ru.job4j.cars;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "engine")
    private List<Car> cars = new ArrayList<>();

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

    public List<Car> getCars() {
        return cars;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "id=" + id
                + ", model='" + model + '\''
                + '}';
    }
}