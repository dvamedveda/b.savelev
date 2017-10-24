package ru.job4j.loop;

/**
 * Класс Factorial, который содержит метод для вычисления факториала.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Factorial {

    /**
     * Метод для вычисления факториала.
     *
     * @param number число, для которого нужно рассчитать факториал.
     * @return факториал числа, или 1, если передан 0.
     */
    public int calc(int number) {
        int factorial = 0;
        if (number > 0) {
            factorial = 1;
            for (int num = 1; num <= number; num++) {
                factorial = factorial * num;
            }
        } else if (number == 0) {
            factorial = 1;
        }
        return factorial;
    }
}