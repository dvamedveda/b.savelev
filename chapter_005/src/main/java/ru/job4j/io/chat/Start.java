package ru.job4j.io.chat;

import java.io.IOException;

/**
 * Класс для запуска консольного чата.
 */
public class Start {
    /**
     * Точка запуска консольного чата.
     *
     * @param args список аргументов.
     * @throws IOException бросается, если в процессе работы
     *                     появилось исключение в работе с потоками ввода и вывода.
     */
    public static void main(String[] args) throws IOException {
        String path = "F:\\answers.txt";
        Logic logic = new Logic(path, System.in, System.out);
        logic.start();
    }
}