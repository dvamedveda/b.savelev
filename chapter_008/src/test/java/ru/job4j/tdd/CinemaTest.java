package ru.job4j.tdd;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.CoreMatchers.is;

public class CinemaTest {

    /**
     * Здесь проверяем покупку билета в кинотеатре.
     */
    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = Mockito.mock(Cinema3D.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.NOVEMBER, 10, 23, 0, 0);
        Ticket expected = new Ticket3D();
        Mockito.when(cinema.buy(account, 1, 1, calendar)).thenReturn(expected);
        Ticket actual = cinema.buy(account, 1, 1, calendar);
        Assert.assertThat(actual, is(expected));
        Mockito.verify(cinema, Mockito.times(1)).buy(account, 1, 1, calendar);
    }

    /**
     * Здесь проверяем поиск сеанса в кинотеатре.
     */
    @Test
    public void find() {
        Cinema cinema = Mockito.mock(Cinema3D.class);
        Session expectedSession = new Session3D();
        Predicate<Session> predicate = session -> true;
        ArrayList<Session> expectedSessions = new ArrayList<>();
        expectedSessions.add(expectedSession);
        Mockito.when(cinema.find(predicate)).thenReturn(expectedSessions);
        List<Session> actualSessions = cinema.find(predicate);
        Mockito.verify(cinema, Mockito.times(1)).find(predicate);
        Assert.assertThat(actualSessions.size(), is(1));
    }

    /**
     * Здесь проверяем добавление нового сеанса в кинотеатре.
     */
    @Test
    public void add() {
        Cinema cinema = Mockito.mock(Cinema3D.class);
        Session expected = new Session3D();
        cinema.add(expected);
        Predicate<Session> predicate = session -> true;
        ArrayList<Session> expectedSessions = new ArrayList<>();
        expectedSessions.add(expected);
        Mockito.when(cinema.find(predicate)).thenReturn(expectedSessions);
        List<Session> actualSessions = cinema.find(predicate);
        Assert.assertThat(actualSessions.get(0), is(expected));
        Assert.assertThat(actualSessions.size(), is(1));
        Mockito.verify(cinema, Mockito.times(1)).find(predicate);
        Mockito.verify(cinema, Mockito.times(1)).add(expected);
    }
}