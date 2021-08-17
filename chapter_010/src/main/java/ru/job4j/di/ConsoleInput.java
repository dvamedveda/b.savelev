package ru.job4j.di;

import java.util.Scanner;

/**
 * Класс для получения данных от пользователя с консоли.
 */
public class ConsoleInput {

    /**
     * Поток ввода.
     */
    private Scanner input = new Scanner(System.in);

    /**
     * Запросить еще строку
     *
     * @return введенная строка.
     */
    public String ask() {
        System.out.println("Enter some string:");
        return input.nextLine();
    }
}