package ru.job4j.tdd;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

/**
 * Заглушечная реализация кинотеатра.
 */
public class Cinema3D implements Cinema {
    @Override
    public List<Session> find(Predicate<Session> filter) {
        return Arrays.asList(new Session3D());
    }

    @Override
    public Ticket buy(Account account, int row, int col, Calendar date) {
        return new Ticket3D();
    }

    @Override
    public void add(Session session) {

    }
}