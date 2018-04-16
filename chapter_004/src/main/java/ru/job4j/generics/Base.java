package ru.job4j.generics;

/**
 * Абстрактный класс, описывающий объекты для контейнера.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public abstract class Base {
    /**
     * Переменная для хранения идентификатора объекта.
     */
    private final String id;

    /**
     * Конструктор объекта
     *
     * @param id идентификатор объекта.
     */
    protected Base(final String id) {
        this.id = id;
    }

    /**
     * Геттер для идентификатора объекта.
     *
     * @return идентификатор объекта.
     */
    public String getId() {
        return id;
    }
}