package ru.job4j.exam;

import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Класс, реализующий тесты для банка.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class BankTest {

    /**
     * Тест проверяет интервал и максимум посететилей,
     * для случая, когда был всего один посетитель.
     */
    @Test
    public void whenOneVisitThenMaxIsCorrect() {
        List<Bank.Visit> visits = Arrays.asList(
                new Bank.Visit(time(8, 10), time(8, 20)));
        assertThat(new Bank().max(visits),
                is(Arrays.asList(new Bank.Info(1, time(8, 10), time(8, 20)))));
    }

    /**
     * Тест проверяет интервал и максимум посетителей,
     * для случая, когда было двое посетителей с пересечением визитов.
     */
    @Test
    public void whenCrossTwoVisitThenMaxIsCorrect() {
        List<Bank.Visit> visits = Arrays.asList(
                new Bank.Visit(time(8, 10), time(8, 50)),
                new Bank.Visit(time(8, 30), time(9, 15))
        );
        assertThat(new Bank().max(visits),
                is(Arrays.asList(new Bank.Info(2, time(8, 30), time(8, 50)))));
    }

    /**
     * Тест проверяет интервал и максимум посетителей,
     * для случая, когда было трое посетителей с пересечением визитов.
     */
    @Test
    public void whenThreeCrossVisitThenMaxIsCorrect() {
        List<Bank.Visit> visits = Arrays.asList(
                new Bank.Visit(time(8, 10), time(8, 50)),
                new Bank.Visit(time(8, 30), time(9, 15)),
                new Bank.Visit(time(8, 40), time(9, 20))
        );
        assertThat(new Bank().max(visits),
                is(Arrays.asList(new Bank.Info(3, time(8, 40), time(8, 50)))));
    }

    /**
     * Тест проверяет интервал и максимум посетителей,
     * для случая, когда было пятнадцать разных посетителей, как с пересечением визитов, так и без.
     */
    @Test
    public void whenFifteenRandomVisitThenMaxIsCorrect() {
        List<Bank.Visit> visits = Arrays.asList(
                new Bank.Visit(time(15, 35), time(15, 45)),
                new Bank.Visit(time(14, 50), time(15, 55)),
                new Bank.Visit(time(13, 0), time(13, 20)),
                new Bank.Visit(time(15, 0), time(15, 20)),
                new Bank.Visit(time(15, 5), time(15, 30)),
                new Bank.Visit(time(13, 50), time(14, 15)),
                new Bank.Visit(time(14, 5), time(14, 35)),
                new Bank.Visit(time(12, 55), time(14, 10)),
                new Bank.Visit(time(12, 10), time(12, 25)),
                new Bank.Visit(time(14, 30), time(16, 25)),
                new Bank.Visit(time(10, 45), time(11, 50)),
                new Bank.Visit(time(13, 5), time(16, 0)),
                new Bank.Visit(time(13, 25), time(15, 40)),
                new Bank.Visit(time(15, 10), time(15, 50)),
                new Bank.Visit(time(14, 55), time(15, 15))
        );
        assertThat(new Bank().max(visits),
                is(Arrays.asList(new Bank.Info(8, time(15, 10), time(15, 15)))));
    }

    /**
     * Тест проверяет интервал и максимум посетителей,
     * для случая, когда было двое посетителей без пересечения визитов.
     */
    @Test
    public void whenTwoNotCrossThenMaxIsOneOverAllIntervals() {
        List<Bank.Visit> visits = Arrays.asList(
                new Bank.Visit(time(8, 10), time(8, 50)),
                new Bank.Visit(time(9, 30), time(10, 15))
        );
        assertThat(new Bank().max(visits),
                is(Arrays.asList(new Bank.Info(1, time(8, 10), time(10, 15)))));
    }

    private long time(int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }
}
