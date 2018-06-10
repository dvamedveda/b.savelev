package ru.job4j.map;

import java.util.Calendar;

/**
 * Класс, расширяющий модель User, с переопределением equals().
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class UserEqualsOverrided extends User {

    public UserEqualsOverrided(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        UserEqualsOverrided ueo = (UserEqualsOverrided) obj;
        if (this.name.equals(ueo.name) && this.children == ueo.children && this.birthday.equals(ueo.birthday)) {
            result = true;
        }
        if (ueo == this) {
            result = true;
        }
        return result;
    }
}