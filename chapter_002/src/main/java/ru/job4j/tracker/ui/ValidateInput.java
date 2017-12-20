package ru.job4j.tracker.ui;

/**
 * Класс, содержащий реализацию пользовательского ввода, с обработкой исключительных ситуаций.
 */
public class ValidateInput extends ConsoleInput {

    /**
     * Реализация шаблона "декоратор".
     * Тут хранится ссылка на конкретный объект, реализующий интерфейс Input, который мы декорируем.
     */
    private final Input input;

    /**
     * Реализация шаблона "декоратор".
     * Конструктор, конкретный объект, реализующий интерфейс Input, который мы декорируем.
     */
    public ValidateInput(Input input) {
        this.input = input;
    }

    /**
     * Декорируем метод ask() конкретного объекта.
     * @param question вопрос, который хотим задать пользователю.
     * @return неизмененный ответ метода ask() декорируемого объекта.
     */
    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    /**
     * Декорируем валидацией перегруженный метод ask() конкретного объекта.
     * @param question вопрос, которых хотим задать пользователю.
     * @param range диапазон допустимых значений для выбора пункта меню.
     * @return одно из допустимых значений пунктов меню.
     */
    @Override
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int result = -1;
        do {
            try {
                result = this.input.ask(question, range);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Введены некорректные данные. Пожалуйста, выберите один из пунктов меню.");
            } catch (MenuOutException moe) {
                System.out.println("Такого пункта меню не существует. Выберите существующий пункт меню.");
            }

        } while (invalid);

        return result;
    }
}