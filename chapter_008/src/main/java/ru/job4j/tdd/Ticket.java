package ru.job4j.tdd;

/**
 * Интерфейс билета
 */
public interface Ticket {

    /**
     * Получить место в билете.
     *
     * @return место.
     */
    String getPlace();

    /**
     * Получить дату в билете.
     *
     * @return дата в билете.
     */
    long getDate();
}