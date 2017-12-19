package ru.job4j.tracker.ui;

/**
 * Класс, содержащий реализацию пользовательского ввода, с обработкой исключительных ситуаций.
 */
public class ValidateInput extends ConsoleInput {

    /**
     * Метод, запрашивающий данные от пользователя и возвращающий номер пункта меню.
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
                result = super.ask(question, range);
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