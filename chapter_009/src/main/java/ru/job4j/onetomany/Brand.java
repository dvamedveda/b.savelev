package ru.job4j.onetomany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Модель данных для марки машины
 */
@Entity
@Table(name = "brand")
public class Brand {

    /**
     * Идентификатор марки машины.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Название марки машины.
     */
    @Column
    private String name;

    /**
     * Список моделей марки машины.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Model> models = new ArrayList<>();

    public Brand(String name) {
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

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    /**
     * Добавление модели в список для марки.
     * @param model модель.
     */
    public void addModel(Model model) {
        this.models.add(model);
    }
}