package ru.job4j.tdd;

/**
 * Заглушечная реализация билета.
 */
public class Ticket3D implements Ticket {

    public String getPlace() {
        return "1:1";
    }

    public long getDate() {
        return 1605027600L;
    }
}