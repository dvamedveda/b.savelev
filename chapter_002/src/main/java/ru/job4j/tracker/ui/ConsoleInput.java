package ru.job4j.tracker.ui;

import java.util.Scanner;

/**
 * Класс, содержащий методы для пользовательского ввода.
 */
public class ConsoleInput implements Input {

    /**
     * Метод, выводящий запрос пользователю и возвращающий ответ от него.
     *
     * @param question вопрос, который хотим задать пользователю.
     * @return ответ от пользователя.
     */
    @Override
    public String ask(String question) {
        Scanner input = new Scanner(System.in);
        System.out.println(question);
        return input.nextLine();
    }

}
