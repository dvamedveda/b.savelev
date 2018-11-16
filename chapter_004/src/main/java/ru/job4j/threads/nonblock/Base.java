package ru.job4j.threads.nonblock;

/**
 * Класс, реализующий модель для исопльзования в неблокирующем кеше.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Base {
    /**
     * Идентификатор
     */
    private int id;

    /**
     * Версия
     */
    private int version;

    /**
     * Имя
     */
    private String name;

    /**
     * Конструктор
     *
     * @param id   идентификатор объекта
     * @param name имя объекта
     */
    public Base(int id, String name) {
        this.id = id;
        this.version = 0;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    private void incrementVersion() {
        this.version++;
    }

    public String getName() {
        return name;
    }

    /**
     * Метод для обновления модели.
     * При обновлении увеличивает версию модели.
     *
     * @param name новое имя
     * @return измененный объект.
     */
    public Base update(String name) {
        this.name = name;
        this.incrementVersion();
        return this;
    }
}