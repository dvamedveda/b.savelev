package ru.job4j.loop;

/**
 * Класс Counter, который содержит метод, использующий цикл.
 */
public class Counter {

    /**
     * Метод, подсчитывающий сумму четных чисел в заданном диапазоне.
     *
     * @param start  начало диапазона.
     * @param finish конец диапазона.
     * @return сумма четных чисел в диапазоне.
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int number = start; number <= finish; number++) {
            if (number % 2 == 0) {
                sum = sum + number;
            }
        }
        return sum;
    }
}