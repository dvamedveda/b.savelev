package ru.job4j.tracker.tracker;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Класс, описывающий заявку для трекера.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
@Entity
@Table(name = "items")
public class Item {
    /**
     * Id заявки.
     * Уникальное поле, по которому заявки идентифицируются.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;

    /**
     * Имя заявки.
     * Может быть неуникальным.
     */
    private String name = "";

    /**
     * Описание заявки, содержащее разную информацию.
     * Может быть неуникальным.
     */
    private String description = "";

    /**
     * Время создания заявки.
     */
    private Timestamp created = new Timestamp(0L);

    /**
     * Конструктор класса заявки.
     *
     * @param name     имя заявки.
     * @param description описание заявки.
     * @param created     время создания заявки.
     */
    public Item(String name, String description, long created) {
        this.name = name;
        this.description = description;
        this.created = new Timestamp(created);
    }

    public Item() {

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreated() {
        return this.created.toInstant().toEpochMilli();
    }

    public void setCreated(long created) {
        this.created = new Timestamp(created);
    }
}