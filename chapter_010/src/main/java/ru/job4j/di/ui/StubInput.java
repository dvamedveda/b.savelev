package ru.job4j.di.ui;

import java.util.ArrayList;

/**
 * Класс, содержащий методы для тестирования с имитацией пользовательского ввода.
 */
public class StubInput implements Input {

    /**
     * Массив заданных ответов пользователя для тестов.
     */
    private String[] answers;

    /**
     * Переменная для указания следующего ответа из массива.
     */
    private int index = 0;

    /**
     * Конструктор класса.
     * @param answers массив заранее заданных ответов пользователя.
     */
    public StubInput(String[] answers) {

        this.answers = answers;
    }

    /**
     * Метод, имитирующий запрос данных у пользователя.
     * Возвращает последовательно ответы из заданного в конструкторе массива.
     * @param question строка, выводящаяся пользователю (на самом деле не выводящаяся, но из-за переопределения метода
     *                 из интерфейса, параметр должен быть передан в данную реализацию метода).
     * @return следующий ответ пользователя из массива.
     */
    @Override
    public String ask(String question) {
        String answer = answers[index];
        index++;
        return answer;
    }

    /**
     * Метод, запрашивающий от пользователя пункт меню и проверяющий, существует ли такой пункт меню.
     * @param question вопрос, задаваемый пользователю.
     * @param range диапазон допустимых значений.
     * @return один из существующих пунктов меню.
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