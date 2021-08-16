package ru.job4j.di.ui;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс, содержащий методы для пользовательского ввода.
 */
public class ConsoleInput implements Input {

    public ConsoleInput() {

    }

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

    /** Перегруженный метод, выводящий запрос пользователю и возвращающий ответ от него.
     *  Проверяет, находится ли заданный пользователем пункт меню в списке доступных, если нет, то бросается исключение.
     * @param question вопрос, которых хотим задать пользователю.
     * @param range диапазон допустимых значений для выбора пункта меню.
     * @return одно из допустимых значений пунктов меню.
     */
    @Override
    public int ask(String question, ArrayList<Integer> range) {
        boolean contains = false;
        int result;
        int key = Integer.parseInt(this.ask(question));
        for (int nextInt : range) {
            if (nextInt == key) {
                contains = true;
                break;
            }
        }
        if (contains) {
            result = key;
        } else {
            throw new MenuOutException("Неизвестный пункт меню.");
        }
        return result;
    }
}
