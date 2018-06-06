package ru.job4j.map;

import java.util.Calendar;

/**
 * Класс расширяющий модель User с переопределением HashCode.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class UserHashCodeOverrided extends User {

    public UserHashCodeOverrided(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    @Override
    public int hashCode() {
        return 31 * this.name.hashCode() * this.children * this.birthday.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}