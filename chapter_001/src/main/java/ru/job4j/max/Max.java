package ru.job4j.max;

/**
 * Класс Max, для вычисления максимального числа.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Max {

    /**
     * Метод, возвращающий максимальное число из двух переданных.
     *
     * @param first  Первый параметр для сравнения.
     * @param second Второй параметр для сравнения.
     * @return возвращается максимальное число.
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }
}