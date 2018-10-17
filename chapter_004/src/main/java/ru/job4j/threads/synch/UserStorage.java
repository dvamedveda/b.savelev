package ru.job4j.threads.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, реализующий потокобезопасное хранилище данных.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
@ThreadSafe
public class UserStorage {

    /**
     * Хранилище пользователей.
     * Ресурс с общим доступом.
     * Использует блокировку при любых своих изменениях.
     */
    @GuardedBy("this")
    private List<User> users;

    UserStorage() {
        this.users = new ArrayList<>();
    }

    /**
     * Синхронизированный метод добавления пользователей.
     *
     * @param user объект пользователя.
     * @return результат добавления пользователя в хранилище.
     */
    public synchronized boolean add(User user) {
        return this.users.add(user);
    }

    /**
     * Синхронизированный метод изменения пользователей.
     *
     * @param user   объект пользователя.
     * @param amount новое количество денег.
     * @return результат изменения пользователя в хранилище.
     */
    private synchronized boolean update(User user, int amount) {
        boolean result = false;
        for (User eachUser : users) {
            if (eachUser.getId() == user.getId()) {
                eachUser.setAmount(amount);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Синхронизированный метод удаления пользователей.
     *
     * @param user объект удаляемого пользователя.
     * @return результат удаления пользователя в хранилище.
     */
    public synchronized boolean delete(User user) {
        boolean result = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.remove(i);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Синхронизированный метод перевода денег со счета одного пользователя на счет другого.
     *
     * @param fromId идентификатор пользователя-источника.
     * @param toId   идентификатор пользователя-получателя.
     * @param amount сумма для перевода между пользователями.
     * @return результат перевода суммы.
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User from = this.getById(fromId);
        User to = this.getById(toId);
        boolean result = false;
        if (from != null && to != null) {
            if (from.getAmount() >= amount) {
                result = this.update(from, from.getAmount() - amount)
                        && this.update(to, to.getAmount() + amount);
            }
        }
        return result;
    }

    /**
     * Метод для получения объекта хранилища по его id.
     * Для внутреннего пользования.
     * Метод не синхронизирован, поскольку не меняет данные в хранилище.
     *
     * @param id идентификатор пользователя.
     * @return объект пользователя.
     */
    User getById(int id) {
        User searching = null;
        for (User user : users) {
            if (user.getId() == id) {
                searching = user;
                break;
            }
        }
        return searching;
    }
}