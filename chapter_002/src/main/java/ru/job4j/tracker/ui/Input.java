package ru.job4j.tracker.ui;

/**
 * Интерфейс, описывающий свойство объектов для ввода.
 */
public interface Input {

    /**
     * Метод для запроса данных от пользователя.
     * @param question вопрос, задаваемый пользователю.
     * @return ответ пользователя.
     */
    String ask(String question);

    /**
     * Метод для запроса данных от пользователя.
     * @param question вопрос, задаваемый пользователю.
     * @param range диапазон допустимых значений.
     * @return ответ пользователя, содержащий одно из допустимых значений.
     */
    int ask(String question, int[] range);
}