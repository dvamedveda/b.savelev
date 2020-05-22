package ru.job4j.spamer;

/**
 * Класс описывает пользователя.
 */
public class User {
    /**
     * Поле для хранения имени человека.
     */
    private String name;

    /**
     * Поле для храненя почты человека.
     */
    private String email;

    /**
     * Конструктор
     *
     * @param name  имя человека.
     * @param email почта человека.
     */
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Вернуть имя человека.
     *
     * @return имя человека.
     */
    public String getName() {
        return name;
    }

    /**
     * Вернуть почту человека.
     *
     * @return почту человека.
     */
    public String getEmail() {
        return email;
    }
}