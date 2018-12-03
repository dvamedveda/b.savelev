package ru.job4j.threads.pool;

/**
 * Класс, реализующий пользователя для отправки почты через ExecutorService.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class User {
    /**
     * Имя юзера.
     */
    private String user;

    /**
     * Почта юзера.
     */
    private String email;

    /**
     * Конструктор для объектов пользователя.
     *
     * @param user  имя юзера.
     * @param email почта юзера.
     */
    public User(String user, String email) {
        this.user = user;
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public String getEmail() {
        return email;
    }
}
