package ru.job4j.bank;

/**
 * Класс, реализующий клиента банка.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class User implements Comparable<User> {

    /**
     * Имя клиента банка.
     */
    private String name;

    /**
     * Паспортные данные клиента банка.
     */
    private String passport;

    /**
     * Конструктор клиента банка.
     *
     * @param name     имя.
     * @param passport паспортные данные.
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * Получение паспортных данных клиента.
     *
     * @return паспортные данные.
     */
    public String getPassport() {
        return passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        if (!name.equals(user.name)) {
            return false;
        }
        return passport.equals(user.passport);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + passport.hashCode();
        return result;
    }

    @Override
    public int compareTo(User o) {
        int result = this.name.compareTo(o.name);
        return result != 0 ? result : this.passport.compareTo(o.passport);
    }
}