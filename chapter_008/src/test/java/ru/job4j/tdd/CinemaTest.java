package ru.job4j.tdd;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class CinemaTest {

    /**
     * Здесь проверяем покупку билета в кинотеатре.
     */
    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.NOVEMBER, 10, 23, 0, 0);
        Ticket ticket = cinema.buy(account, 1, 1, calendar);
        Assert.assertThat(ticket.getPlace(), is("1:1"));
        Assert.assertThat(ticket.getDate(), is(calendar.getTimeInMillis() / 1000));
    }

    /**
     * Здесь проверяем поиск сеанса в кинотеатре.
     */
    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        Assert.assertThat(sessions.size(), is(1));
    }

    /**
     * Здесь проверяем добавление нового сеанса в кинотеатре.
     */
    @Test
    public void add() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        Assert.assertThat(sessions.get(0).getType(), is("3D"));
    }
}