package ru.job4j.tdd;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

/**
 * Интерфейс кинотеатра.
 */
public interface Cinema {

    /**
     * Поиск доступных сеансов.
     *
     * @param filter фильтр сеансов.
     * @return список сеансов.
     */
    List<Session> find(Predicate<Session> filter);

    /**
     * Покупка билета.
     *
     * @param account аккаунт пользователя.
     * @param row     ряд.
     * @param col     место.
     * @param date    дата.
     * @return билет.
     */
    Ticket buy(Account account, int row, int col, Calendar date);

    /**
     * Добавление нового сеанса.
     *
     * @param session новый сеанс.
     */
    void add(Session session);
}