package ru.job4j.tracker.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;

/**
 * Тесты для класса Tracker.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class TrackerTest {

    /**
     * Тест, который проверяет добавление заявки в трекер.
     */
    @Test
    public void whenAddingItemThenAddingIt() {
        Tracker tracker = new Tracker();
        Item item = new Item("Тестовая заявка", "Тестовое описание заявки", 12345L);
        tracker.add(item);

        assertThat(tracker.findAll()[0], is(item));
    }

    /**
     * Тест, который проверяет обновление заявки в трекере.
     */
    @Test
    public void whenUpdateItemThenItUpdating() {
        Tracker tracker = new Tracker();
        Item item = new Item("Тестовая заявка", "Тестовое описание заявки", 12345L);
        tracker.add(item);
        Item updatedItem = tracker.findAll()[0];
        Item newItem = new Item("Измененная тестовая заявка", "Измененное тестовое описание заявки", 54321L);
        newItem.setId(updatedItem.getId());
        tracker.update(newItem);


        assertThat(tracker.findById(updatedItem.getId()).getSummary(), is("Измененная тестовая заявка"));
        assertThat(tracker.findById(updatedItem.getId()).getDescription(), is("Измененное тестовое описание заявки"));
        assertThat(tracker.findById(updatedItem.getId()).getCreated(), is(54321L));
    }

    /**
     * Тест, который проверяет удаление заявки из трекера.
     */
    @Test
    public void whenDeletingItemThenItDeleting() {
        Tracker tracker = new Tracker();
        Item item = new Item("Тестовая заявка", "Тестовое описание заявки", 12345L);
        tracker.add(item);
        Item deletingItem = tracker.findAll()[0];
        String deletingSymmary = deletingItem.getSummary();
        tracker.delete(deletingItem);
        assertNull(tracker.findByName(deletingSymmary));
    }

    /**
     * Тест, который проверяет нахождение всех существующих заявок.
     */
    @Test
    public void whenExistOneMoreItemsThenFindAll() {
        Item itemOne = new Item("Тестовая заявка 1", "Тестовое описание заявки 1", 12345L);
        Item itemTwo = new Item("Тестовая заявка 2", "Тестовое описание заявки 2", 23456L);
        Item itemThree = new Item("Тестовая заявка 3", "Тестовое описание заявки 3", 34567L);
        Item[] expected = {itemOne, itemTwo, itemThree};

        Tracker tracker = new Tracker();
        tracker.add(itemOne);
        tracker.add(itemTwo);
        tracker.add(itemThree);

        assertThat(tracker.findAll(), arrayContainingInAnyOrder(expected));
    }

    /**
     * Тест, проверяющий нахождение заявок с заданным именем, в случае, когда все заявки в трекере - с заданными именем.
     */
    @Test
    public void whenExistTwoItemWithSameNameThenFindTwoItems() {
        Tracker tracker = new Tracker();
        Item itemOne = new Item("Тестовая заявка", "Тестовое описание заявки 1", 12345L);
        Item itemTwo = new Item("Тестовая заявка", "Тестовое описание заявки 2", 54321L);
        tracker.add(itemOne);
        tracker.add(itemTwo);
        String sameSummary = "Тестовая заявка";
        Item[] expected = {itemOne, itemTwo};
        assertThat(expected, is(tracker.findByName(sameSummary)));
    }

    /**
     * Тест, проверяющий нахождение заявки с именем, в случае, когда не все заявки в трекере - с заданным именем.
     */
    @Test
    public void whenExistOneItemWithNameThenFindingOneItem() {
        Tracker tracker = new Tracker();
        Item itemOne = new Item("Первая тестовая заявка", "Тестовое описание первой заявки", 12345L);
        Item itemTwo = new Item("Вторая тестовая заявка", "Тестовое описание второй заявки", 54321L);
        tracker.add(itemOne);
        tracker.add(itemTwo);
        String summary = "Первая тестовая заявка";
        Item[] expected = {itemOne};
        assertThat(expected, is(tracker.findByName(summary)));
    }

    /**
     * Тест, проверяющий нахождение заявки с именем, в случае, когда в трекере нет заявок с таким имененм.
     */
    @Test
    public void whenFindingByNotExistingNameThenNotFindingIt() {
        Tracker tracker = new Tracker();
        Item item = new Item("Тестовая заявка", "Тестовое описание заявки", 12345L);
        tracker.add(item);
        assertNull(tracker.findByName(""));
    }

    /**
     * Тест, проверяющий поиск заявки по id, в случае, когда заявка с таким id существует.
     */
    @Test
    public void whenFindingByIdThenFindingIt() {
        Tracker tracker = new Tracker();
        Item item = new Item("Тестовая заявка", "Тестовое описание заявки", 12345L);
        tracker.add(item);
        String id = tracker.findAll()[0].getId();
        assertThat(id, is(tracker.findById(id).getId()));
    }

    /**
     * Тест, проверяющий поиск заявки по id, в случае, когда заявки с таким id не существует.
     */
    @Test
    public void whenFindingByNotExistingIdThenReturnsNull() {
        Tracker tracker = new Tracker();
        Item item = new Item("Тестовая заявка", "Тестовое описание заявки", 12345L);
        tracker.add(item);
        String id = "123";
        assertNull(tracker.findById(id));
    }
}