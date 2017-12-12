package ru.job4j.tracker.ui;

import org.junit.Test;
import ru.job4j.tracker.tracker.Item;
import ru.job4j.tracker.tracker.Tracker;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;

public class StubInputTest {

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
}