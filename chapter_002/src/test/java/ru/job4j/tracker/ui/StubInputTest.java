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

    /**
     * Тест, проверяющий, что при выборе пункта меню "Поиск всех заявок", и наличии больше одной заявки, они находятся.
     */
    @Test
    public void whenFindingAllItemsWhereOneMoreItemsThenFindingAll() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Заявка 1", "Описание 1", 123L));
        tracker.add(new Item("Заявка 2", "Описание 2", 231L));
        tracker.add(new Item("Заявка 3", "Описание 3", 312L));
        Item[] allItem = tracker.findAll();
        Input input = new StubInput(new String[]{});
        StartUI ui = new StartUI(tracker, input);
        assertThat(ui.findAllItems(), arrayContainingInAnyOrder(allItem));
    }

    /**
     * Тест, проверяющий, что при выборе пункта меню "Поиск всех заявок", и наличии только одной заявки, она находится.
     */
    @Test
    public void whenFindingAllItemsWhereOnlyOneItemThenFindingAll() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Заявка 1", "Описание 1", 123L));
        Item[] allItem = tracker.findAll();
        Input input = new StubInput(new String[]{});
        StartUI ui = new StartUI(tracker, input);
        assertThat(ui.findAllItems(), arrayContainingInAnyOrder(allItem));
    }

    /**
     * Тест, проверяющий, что при выборе пункта меню "Поиск всех заявок", и нет ни одной заявки, они не находятся.
     */
    @Test
    public void whenNoOneItemThenFindingNothing() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{});
        StartUI ui = new StartUI(tracker, input);
        assertNull(ui.findAllItems());
    }

    /**
     * Тест, проверяющий, что при выборе пункта меню "Поиск заявок по имени", и в наличии больше одной заявки, они находятся.
     */
    @Test
    public void whenFindingItemsByNameWhereOneMoreItemsThenFindingAll() {
        Tracker tracker = new Tracker();
        Item oneItem = new Item("Заявка 1", "Описание 1", 123L);
        Item twoItem = new Item("Заявка 1", "Описание 2", 231L);
        Item threeItem = new Item("Заявка 3", "Описание 3", 312L);
        tracker.add(oneItem);
        tracker.add(twoItem);
        tracker.add(threeItem);
        Item[] sameNameItems = {oneItem, twoItem};
        Input input = new StubInput(new String[]{"Заявка 1"});
        StartUI ui = new StartUI(tracker, input);
        assertThat(ui.findByName(), arrayContainingInAnyOrder(sameNameItems));
    }

    /**
     * Тест, проверяющий, что при выборе пункта меню "Поиск заявок по имени", и в наличии одной заявки, она находится.
     */
    @Test
    public void whenFindingItemsByNameWhereOneItemThenFindingIt() {
        Tracker tracker = new Tracker();
        Item oneItem = new Item("Заявка 1", "Описание 1", 123L);
        Item twoItem = new Item("Заявка 2", "Описание 2", 231L);
        Item threeItem = new Item("Заявка 3", "Описание 3", 312L);
        tracker.add(oneItem);
        tracker.add(twoItem);
        tracker.add(threeItem);
        Item[] searchingItem = {oneItem};
        Input input = new StubInput(new String[]{"Заявка 1"});
        StartUI ui = new StartUI(tracker, input);
        assertThat(ui.findByName(), arrayContainingInAnyOrder(searchingItem));
    }

    /**
     * Тест, проверяющий, что при выборе пункта меню "Поиск заявок по имени", и в наличии нет ни одной заявки, они не находятся.
     */
    @Test
    public void whenNoOneItemWithSomeNameThenFindingNothing() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Заявка 2", "Описание 2", 231L));
        Input input = new StubInput(new String[]{"Заявка 1"});
        StartUI ui = new StartUI(tracker, input);
        assertNull(ui.findByName());
    }

    /**
     * Тест, проверяющий, что при выборе пункта меню "Поиск заявок по Id", и в наличии есть заявка, она находится.
     */
    @Test
    public void whenFindingItemByIdThenFindingIt() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Заявка 1", "Описание 1", 123L));
        Item searchingItem = tracker.findAll()[0];
        Input input = new StubInput(new String[]{searchingItem.getId()});
        StartUI ui = new StartUI(tracker, input);
        assertThat(ui.findByID().getId(), is(searchingItem.getId()));
    }

    /**
     * Тест, проверяющий, что при выборе пункта меню "Поиск заявок по Id", и в наличии нет заявок, они не находятся.
     */
    @Test
    public void whenFindingNotExistItemThenFindIt() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Заявка 1", "Описание 1", 123L));
        Input input = new StubInput(new String[]{"123456"});
        StartUI ui = new StartUI(tracker, input);
        assertNull(ui.findByID());
    }
}