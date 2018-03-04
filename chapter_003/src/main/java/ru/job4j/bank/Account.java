package ru.job4j.bank;

/**
 * Класс, реализующий банковский счет.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Account {

    /**
     * Количество денег на счете.
     */
    private double value;

    /**
     * Номер счета.
     */
    private String requisites;

    /**
     * Конструктор счета.
     *
     * @param requisites номер счета.
     * @param value      количество денег на счету.
     */
    public Account(String requisites, double value) {
        this.requisites = requisites;
        this.value = value;
    }

    /**
     * Получение денег на счету.
     *
     * @return количество денег.
     */
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Получение номера счета.
     *
     * @return номер счета.
     */
    public String getRequisites() {
        return requisites;
    }
}