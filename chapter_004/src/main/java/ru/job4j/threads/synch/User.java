package ru.job4j.threads.synch;

/**
 * Класс, реализующий пользователя для класса UserStorage.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class User {
    /**
     * Идентификатор пользователя
     */
    private int id;
    /**
     * Количество денег на счету пользователя.
     */
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}