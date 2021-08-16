package ru.job4j.di.ui;

import org.junit.Test;
import ru.job4j.di.tracker.Item;
import ru.job4j.di.tracker.Tracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Здесь проверяется работа трекера с консольным вводом (при помощи моков)
 */
public class MockConsoleInputTest {

    private final String delimiter = System.lineSeparator();

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
     * Устанавливаем новый поток вывода.
     */
    private void loadByteOut() {
        System.setOut(new PrintStream(this.byteout));
    }

    /**
     * Возвращаем стандартный поток вывода.
     */
    private void loadStandartOut() {
        System.setOut(stdout);
    }

    /**
     * Вывод программы при показе заявки(-ок).
     * Вынесен в отдельный метод для удобства использования.
     */
    private String printItem(Item item) {
        String result = new StringBuilder()
                .append("---------------------------------")
                .append(delimiter)
                .append("Заявка с идентификатором ")
                .append(item.getId())
                .append(delimiter)
                .append("Название заявки: ")
                .append(item.getSummary())
                .append(delimiter)
                .append("---------------------------------")
                .append(delimiter)
                .append("Описание заявки:")
                .append(delimiter)
                .append(item.getDescription())
                .append(delimiter)
                .append("---------------------------------")
                .append(delimiter)
                .append(delimiter)
                .toString();
        return result;
    }

    /**
     * Проверка добавления итема в трекер.
     */
    @Test
    public void whenAddItemThenItemAddSuccess() {
        Tracker tracker = new Tracker();
        Input input = mock(ConsoleInput.class);
        when(input.ask(any(String.class), any(ArrayList.class))).thenReturn(1).thenReturn(0);
        when(input.ask(any(String.class))).thenReturn("Тестовая заявка номер 1.").thenReturn("Описание тестовой заявки номер 1.");
        StartUI ui = new StartUI(tracker, input);
        ui.startWork();
        Item addedItem = tracker.findAll().get(0);
        assertThat(addedItem.getSummary(), is("Тестовая заявка номер 1."));
        assertThat(addedItem.getDescription(), is("Описание тестовой заявки номер 1."));
    }

    /**
     * Проверка удаления итема из трекера.
     */
    @Test
    public void whenDeleteItemThenItDeleting() {
        Tracker tracker = new Tracker();
        Input input = mock(ConsoleInput.class);
        tracker.add(new Item("Заявка 1", "Описание 1", 123L));
        Item deletingItem = tracker.findAll().get(0);
        when(input.ask(any(String.class), any(ArrayList.class))).thenReturn(3).thenReturn(0);
        when(input.ask(any(String.class))).thenReturn(deletingItem.getId());
        StartUI ui = new StartUI(tracker, input);
        ui.startWork();
        assertThat(tracker.findAll(), is(new ArrayList<Item>()));
    }

    /**
     * Проверка поиска итема в трекере по имени.
     */
    @Test
    public void whenSearchByNameAndHaveTwoItemsAndTwoWithNameThenFindTwo() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Заявка 1", "Описание 1", 123L));
        tracker.add(new Item("Заявка 1", "Описание 2", 123L));
        Item firstItem = tracker.findAll().get(0);
        Item secondItem = tracker.findAll().get(1);
        Input input = mock(ConsoleInput.class);
        when(input.ask(any(String.class), any(ArrayList.class))).thenReturn(5).thenReturn(0);
        when(input.ask(any(String.class))).thenReturn("Заявка 1");
        this.loadByteOut();
        StartUI ui = new StartUI(tracker, input);
        ui.startWork();
        String expected = new StringBuilder()
                .append(delimiter)
                .append(this.menu)
                .append("Выбран пункт меню 5. Поиск заявки по названию.")
                .append(delimiter)
                .append("Найдены следующие заявки : ")
                .append(delimiter)
                .append(this.printItem(firstItem))
                .append(this.printItem(secondItem))
                .append(delimiter)
                .append(this.menu)
                .append("Выбран пункт меню 0. Выход из программы. До свидания! -=^_^=-")
                .append(delimiter)
                .toString();
        String result = new String(this.byteout.toByteArray());
        assertThat(expected, is(result));
        this.loadStandartOut();
    }

    /**
     * Проверка поиска итема в трекере по идентификатору.
     */
    @Test
    public void whenSearchByIdAndHaveTwoItemsThenFindOne() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Заявка 1", "Описание 1", 123L));
        Item firstItem = tracker.findAll().get(0);
        Input input = mock(ConsoleInput.class);
        when(input.ask(any(String.class), any(ArrayList.class))).thenReturn(6).thenReturn(0);
        when(input.ask(any(String.class))).thenReturn(firstItem.getId());
        this.loadByteOut();
        StartUI ui = new StartUI(tracker, input);
        ui.startWork();
        String expected = new StringBuilder()
                .append(delimiter)
                .append(this.menu)
                .append("Выбран пункт меню 6. Поиск заявки по ID.")
                .append(delimiter)
                .append("Найдена заявка : ")
                .append(delimiter)
                .append(this.printItem(firstItem))
                .append(delimiter)
                .append(this.menu)
                .append("Выбран пункт меню 0. Выход из программы. До свидания! -=^_^=-")
                .append(delimiter)
                .toString();
        String result = new String(this.byteout.toByteArray());
        assertThat(expected, is(result));
        this.loadStandartOut();
    }
}