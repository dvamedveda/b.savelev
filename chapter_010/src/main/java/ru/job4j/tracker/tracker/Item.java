package ru.job4j.tracker.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, описывающий заявку для трекера.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Item {
    /**
     * Id заявки.
     * Уникальное поле, по которому заявки идентифицируются.
     */
    private String id = "";

    /**
     * Имя заявки.
     * Может быть неуникальным.
     */
    private String summary = "";

    /**
     * Описание заявки, содержащее разную информацию.
     * Может быть неуникальным.
     */
    private String description = "";

    /**
     * Комментарии к заявке.
     */
    private List<String> comments = new ArrayList<>();

    /**
     * Время создания заявки.
     */
    private long created = 0L;

    /**
     * Конструктор класса заявки.
     *
     * @param summary     имя заявки.
     * @param description описание заявки.
     * @param created     время создания заявки.
     */
    public Item(String summary, String description, long created) {
        this.summary = summary;
        this.description = description;
        this.created = created;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreated() {
        return this.created;
    }

    public void setCreated(long created) {
        this.created = created;
    }
}