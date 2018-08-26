package ru.job4j.exam;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, который реализует хранилище пользователей, а также самих пользователей.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Store {

    /**
     * Список пользователей.
     */
    private List<User> users = new ArrayList<>();

    /**
     * Метод, возвращающий дифф между двумя коллекциями пользователей.
     * @param previous некая предыдущая коллекция пользователей.
     * @param current текущая коллекция пользователей
     * @return объект диффа между коллекциями.
     */
    public Info diff(List<User> previous, List<User> current) {
        return new Info(previous, current);
    }

    /**
     * Получить список всех пользователей.
     * @return список пользователей текущего хранилища.
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Метод для добавления пользователя в хранилище.
     * @param user новый пользователь.
     * @return успешность добавления.
     */
    public boolean addUser(User user) {
        return this.users.add(user);
    }

    /**
     * Метод для удаления пользователя из хранилища.
     * @param user удаляемый пользователь.
     * @return успешность удаления.
     */
    public boolean deleteUser(User user) {
        return this.users.remove(user);
    }

    /**
     * Метод для редактирования пользователя, находящегося в хранилище.
     * @param user редактируемый пользователь.
     * @param name новое имя пользователя.
     */
    public void editUser(User user, String name) {
        user.name = name;
        user.changed = true;
    }

    /**
     * Класс, реализующий пользователя.
     */
    public static class User {
        /**
         * Идентификатор.
         */
        private int id;

        /**
         * Имя пользователя.
         */
        private String name;

        /**
         * Признак изменения пользователя.
         */
        private boolean changed = false;

        /**
         * Конструктор пользователя.
         * @param id идентификатор.
         * @param name имя.
         */
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * Узнать, менялся ли пользователь.
         * @return признак изменения пользователя.
         */
        public boolean isChanged() {
            return this.changed;
        }
    }
}
