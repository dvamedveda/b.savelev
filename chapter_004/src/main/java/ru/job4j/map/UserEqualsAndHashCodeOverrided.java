package ru.job4j.map;

import java.util.Calendar;

/**
 * Класс расширяющий модель User с переопределением hashCode() и equals().
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class UserEqualsAndHashCodeOverrided extends User {

    public UserEqualsAndHashCodeOverrided(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    @Override
    public int hashCode() {
        return 31 * this.name.hashCode() * this.children * this.birthday.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        UserEqualsAndHashCodeOverrided ueo = (UserEqualsAndHashCodeOverrided) obj;
        if (this.name.equals(ueo.name) && this.children == ueo.children && this.birthday.equals(ueo.birthday)) {
            result = true;
        }
        if (ueo == this) {
            result = true;
        }
        return result;
    }
}