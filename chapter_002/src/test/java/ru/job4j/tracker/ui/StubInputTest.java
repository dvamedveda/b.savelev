package ru.job4j.tracker.ui;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Categories;
import ru.job4j.tracker.tracker.Item;
import ru.job4j.tracker.tracker.Tracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты класса StartUI с имитированием пользовательского ввода.
 */
public class StubInputTest {
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
     * Тест, проверяющий, что при выборе пункта меню "Добавить заявку", она добавляется.
     */
    @Test
    public void whenChooseAddItemThenAddingIt() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"1", "Тестовая заявка номер 1.", "Описание тестовой заявки номер 1.", "0"});
        StartUI ui = new StartUI(tracker, input);
        ui.startWork();
        Item addedItem = tracker.findAll()[0];
        assertThat(addedItem.getSummary(), is("Тестовая заявка номер 1."));
        assertThat(addedItem.getDescription(), is("Описание тестовой заявки номер 1."));
    }

    /**
     * Тест, проверяющий, что при выборе пункта меню "Обновить заявку", она обновляется.
     */
    @Test
    public void whenUpdatingItemThenUpdateIt() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Заявка 1", "Описание 1", 123L));
        Item updatingItem = tracker.findAll()[0];
        Input input = new StubInput(new String[]{"2", updatingItem.getId(), "Заявка 2", "Описание 2", "0"});
        StartUI ui = new StartUI(tracker, input);
        ui.startWork();
        Item updatedItem = tracker.findAll()[0];
        assertThat(updatedItem.getSummary(), is("Заявка 2"));
        assertThat(updatedItem.getDescription(), is("Описание 2"));
    }

    /**
     * Тест, проверяющий, что при выборе пункта меню "Удалить заявку", она удаляется.
     */
    @Test
    public void whenDeleteItemThenItDeleting() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Заявка 1", "Описание 1", 123L));
        Item deletingItem = tracker.findAll()[0];
        Input input = new StubInput(new String[]{"3", deletingItem.getId(), "0"});
        StartUI ui = new StartUI(tracker, input);
        ui.startWork();
        assertThat(tracker.findAll(), is(new Item[]{}));
    }

    /**
     * Тест, проверяющий работу пункта меню "4. Поиск всех заявок".
     * Проверяется случай, когда в трекере есть только одна заявка.
     */
    @Test
    public void whenSearchAllAndHaveOneThenFindOne() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Заявка 1", "Описание 1", 123L));
        Item firstItem = tracker.findAll()[0];
        Input input = new StubInput(new String[]{"4", "0"});
        this.loadByteOut();
        String expected = new StringBuilder()
                .append(delimiter)
                .append(this.menu)
                .append("Выбран пункт меню 4. Поиск всех заявок.")
                .append(delimiter)
                .append("Найдены следующие заявки : ")
                .append(delimiter)
                .append(this.printItem(firstItem))
                .append(delimiter)
                .append(this.menu)
                .append("Выбран пункт меню 0. Выход из программы. До свидания! -=^_^=-")
                .append(delimiter)
                .toString();
        StartUI ui = new StartUI(tracker, input);
        ui.startWork();
        String result = new String(this.byteout.toByteArray());
        assertThat(expected, is(result));
        this.loadStandartOut();
    }

    /**
     * Тест, проверяющий работу пункта меню "4. Поиск всех заявок".
     * Проверяется случай, когда в трекере есть больше одной заявки.
     */
    @Test
    public void whenSearchAllAndHaveTwoThenFindTwo() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Заявка 1", "Описание 1", 123L));
        tracker.add(new Item("Заявка 2", "Описание 2", 123L));
        Item firstItem = tracker.findAll()[0];
        Item secondItem = tracker.findAll()[1];
        Input input = new StubInput(new String[]{"4", "0"});
        this.loadByteOut();
        StartUI ui = new StartUI(tracker, input);
        ui.startWork();
        String expected = new StringBuilder()
                .append(delimiter)
                .append(this.menu)
                .append("Выбран пункт меню 4. Поиск всех заявок.")
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
     * Тест, проверяющий работу пункта меню "4. Поиск всех заявок".
     * Проверяется случай, когда в трекере нет ни одной заявки.
     */
    @Test
    public void whenSearchAllAndHaveNoItemsThenFindNothing() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"4", "0"});
        this.loadByteOut();
        StartUI ui = new StartUI(tracker, input);
        ui.startWork();
        String expected = new StringBuilder()
                .append(delimiter)
                .append(this.menu)
                .append("Выбран пункт меню 4. Поиск всех заявок.")
                .append(delimiter)
                .append("На данный момент не существует никаких заявок.")
                .append(delimiter)
                .append(delimiter)
                .append(this.menu)
                .append("Выбран пункт меню 0. Выход из программы. До свидания! -=^_^=-")
                .append(delimiter)
                .toString();
        String result = new String(this.byteout.toByteArray());
        assertThat(result, is(expected));
        this.loadStandartOut();
    }

    /**
     * Тест, проверяющий работу пункта меню "5. Поиск заявки по имени".
     * Проверяется случай, когда в трекере есть несколько заявок с одним именем.
     */
    @Test
    public void whenSearchByNameAndHaveTwoItemsAndTwoWithNameThenFindTwo() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Заявка 1", "Описание 1", 123L));
        tracker.add(new Item("Заявка 1", "Описание 2", 123L));
        Item firstItem = tracker.findAll()[0];
        Item secondItem = tracker.findAll()[1];
        Input input = new StubInput(new String[]{"5", "Заявка 1", "0"});
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
     * Тест, проверяющий работу пункта меню "5. Поиск заявки по имени".
     * Проверяется случай, когда в трекере есть только одна заявка с заданным именем.
     */
    @Test
    public void whenSearchByNameAndHaveTwoItemsButOneWithNameThenFindOne() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Заявка 1", "Описание 1", 123L));
        tracker.add(new Item("Заявка 2", "Описание 2", 123L));
        Item firstItem = tracker.findAll()[0];
        Input input = new StubInput(new String[]{"5", "Заявка 1", "0"});
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
     * Тест, проверяющий работу пункта меню "5. Поиск заявки по имени".
     * Проверяется случай, когда в трекере есть только нет ни одной заявки с заданным именем.
     */
    @Test
    public void whenSearchByNameAndHaveTwoItemsButNothingWithNameThenFindNothing() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Заявка 1", "Описание 1", 123L));
        tracker.add(new Item("Заявка 2", "Описание 2", 123L));
        Input input = new StubInput(new String[]{"5", "Заявка 3", "0"});
        this.loadByteOut();
        StartUI ui = new StartUI(tracker, input);
        ui.startWork();
        String expected = new StringBuilder()
                .append(delimiter)
                .append(this.menu)
                .append("Выбран пункт меню 5. Поиск заявки по названию.")
                .append(delimiter)
                .append("Заявки с таким названием не найдено!")
                .append(delimiter)
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
     * Тест, проверяющий работу пункта меню "6. Поиск заявки по ID".
     * Проверяется случай, когда в трекере есть заявка с заданным ID.
     */
    @Test
    public void whenSearchByIdAndHaveTwoItemsThenFindOne() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Заявка 1", "Описание 1", 123L));
        Item firstItem = tracker.findAll()[0];
        Input input = new StubInput(new String[]{"6", firstItem.getId(), "0"});
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

    /**
     * Тест, проверяющий работу пункта меню "6. Поиск заявки по ID".
     * Проверяется случай, когда в трекере нет заявок с заданным ID.
     */
    @Test
    public void whenSearchByIdAndHaveNoItemsWithIdThenFindNothing() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Заявка 1", "Описание 1", 123L));
        String unexistId = "123456789";
        Input input = new StubInput(new String[]{"6", unexistId, "0"});
        this.loadByteOut();
        StartUI ui = new StartUI(tracker, input);
        ui.startWork();
        String result = new String(this.byteout.toByteArray());
        String expected = new StringBuilder()
                .append(delimiter)
                .append(this.menu)
                .append("Выбран пункт меню 6. Поиск заявки по ID.")
                .append(delimiter)
                .append("Заявки с таким ID не найдено!")
                .append(delimiter)
                .append(delimiter)
                .append(this.menu)
                .append("Выбран пункт меню 0. Выход из программы. До свидания! -=^_^=-")
                .append(delimiter)
                .toString();
        assertThat(expected, is(result));
        this.loadStandartOut();
    }
}