package ru.job4j.tracker.ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.tracker.ConnectionAuto;
import ru.job4j.tracker.tracker.SqlTracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты классы ValidateInput.
 */
public class ValidateInputTest {

    /**
     * Стандартный поток вывода.
     * Используется для приведения системы в исходное состояние.
     */
    private final PrintStream stdout = System.out;
    /**
     * Байтовый поток вывода.
     * Используется для чтения и проверки вывода программы на соответствие ожидаемому.
     */
    private final ByteArrayOutputStream byteout = new ByteArrayOutputStream();

    /**
     * Разделитель строк.
     * Вынесен в отдельную переменную для удобства использования.
     */
    private final String delimiter = System.lineSeparator();

    /**
     * Устанавливаем новый поток вывода.
     */
    @Before
    public void loadByteOut() {
        System.setOut(new PrintStream(this.byteout));
    }

    /**
     * Возвращаем стандартный поток вывода.
     */
    @After
    public void loadStandartOut() {
        System.setOut(stdout);
    }

    /**
     * Вывод программы при показе меню.
     * Вынесен в отдельную переменную для удобства использования.
     */
    private final String menu = new StringBuilder()
            .append("+++++++++++++++++++++++++++++++++").append(delimiter)
            .append("Меню программы :").append(delimiter)
            .append("0. Выход из программы.").append(delimiter)
            .append("1. Создание новой заявки.").append(delimiter)
            .append("2. Обновление существующей заявки.").append(delimiter)
            .append("3. Удаление заявки по ее ID.").append(delimiter)
            .append("4. Поиск всех заявок.").append(delimiter)
            .append("5. Поиск заявки по названию.").append(delimiter)
            .append("6. Поиск заявки по ID.").append(delimiter)
            .append("+++++++++++++++++++++++++++++++++").append(delimiter)
            .append(delimiter)
            .toString();

    /**
     * Тест, проверяющий вывод программы для случая, когда введены некорректные данные.
     */
    @Test
    public void whenInvalidInputFromUserThenGetError() {
        SqlTracker sqlTracker = new SqlTracker(new ConnectionAuto().init());
        Input input = new ValidateInput(new StubInput(new String[]{"abc", "0"}));
        StartUI ui = new StartUI(sqlTracker, input);
        ui.startWork();
        String expected = new StringBuilder()
                .append(delimiter)
                .append(this.menu)
                .append("Введены некорректные данные. Пожалуйста, выберите один из пунктов меню.").append(delimiter)
                .append("Выбран пункт меню 0. Выход из программы. До свидания! -=^_^=-").append(delimiter)
                .toString();
        String result = new String(this.byteout.toByteArray());
        assertThat(result, is(expected));
    }

    /**
     * Тест, проверяющий вывод программы для случая, когда введен некорректный пункт меню.
     */
    @Test
    public void whenInvalidMenuNumberThenGetError() {
        SqlTracker sqlTracker = new SqlTracker(new ConnectionAuto().init());
        Input input = new ValidateInput(new StubInput(new String[]{"-1", "0"}));
        StartUI ui = new StartUI(sqlTracker, input);
        ui.startWork();
        String expected = new StringBuilder()
                .append(delimiter)
                .append(this.menu)
                .append("Такого пункта меню не существует. Выберите существующий пункт меню.").append(delimiter)
                .append("Выбран пункт меню 0. Выход из программы. До свидания! -=^_^=-").append(delimiter)
                .toString();
        String result = new String(this.byteout.toByteArray());
        assertThat(result, is(expected));
    }
}