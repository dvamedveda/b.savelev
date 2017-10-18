package ru.job4j.calculator;

/**
 * класс Calculator, который содержит методы для арифметических операций над числами.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version 1.0
 * @since 0.1
 */
public class Calculator {
    /**
     * Поле в котором хранится результат.
     * тип double
     */
    private double result;

    /**
     * Метод, который вычисляет сумму двух чисел.
     *
     * @param first  первое слагаемое, тип double
     * @param second второе слагаемое, тип double
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Метод, который вычисляет разность двух чисел.
     *
     * @param first  уменьшаемое, тип double
     * @param second вычитаемое, тип double
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Метод, который вычисляет частное двух чисел.
     *
     * @param first  делимое, тип double
     * @param second делитель, тип double
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Метод, который вычисляет произведение двух чисел.
     *
     * @param first  умножаемое, тип double
     * @param second множитель, тип double
     */
    public void multiply(double first, double second) {
        this.result = first * second;
    }

    /**
     * Возвращает значение поля result.
     *
     * @return возвращает тип double
     */
    public double getResult() {
        return this.result;
    }
}