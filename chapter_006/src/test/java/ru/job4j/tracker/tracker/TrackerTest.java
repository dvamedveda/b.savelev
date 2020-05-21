package ru.job4j.tracker.tracker;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

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
        MemTracker memTracker = new MemTracker();
        Item item = new Item("Тестовая заявка", "Тестовое описание заявки", 12345L);
        memTracker.add(item);

        assertThat(memTracker.findAll().get(0), is(item));
    }

    /**
     * Тест, который проверяет обновление заявки в трекере.
     */
    @Test
    public void whenUpdateItemThenItUpdating() {
        MemTracker memTracker = new MemTracker();
        Item item = new Item("Тестовая заявка", "Тестовое описание заявки", 12345L);
        memTracker.add(item);
        Item updatedItem = memTracker.findAll().get(0);
        Item newItem = new Item("Измененная тестовая заявка", "Измененное тестовое описание заявки", 54321L);
        newItem.setId(updatedItem.getId());
        memTracker.update(newItem);
        Item foundedItem = memTracker.findById(updatedItem.getId());

        assertThat(foundedItem.getSummary(), is("Измененная тестовая заявка"));
        assertThat(foundedItem.getDescription(), is("Измененное тестовое описание заявки"));
        assertThat(foundedItem.getCreated(), is(54321L));
    }

    /**
     * Тест, который проверяет удаление заявки из трекера.
     */
    @Test
    public void whenDeletingItemThenItDeleting() {
        MemTracker memTracker = new MemTracker();
        Item item = new Item("Тестовая заявка", "Тестовое описание заявки", 12345L);
        memTracker.add(item);
        Item deletingItem = memTracker.findAll().get(0);
        String deletingSymmary = deletingItem.getSummary();
        memTracker.delete(deletingItem);
        assertNull(memTracker.findByName(deletingSymmary));
    }

    /**
     * Тест, который проверяет нахождение всех существующих заявок.
     */
    @Test
    public void whenExistOneMoreItemsThenFindAll() {
        ArrayList<Item> expected = new ArrayList<>();
        Item itemOne = new Item("Тестовая заявка 1", "Тестовое описание заявки 1", 12345L);
        Item itemTwo = new Item("Тестовая заявка 2", "Тестовое описание заявки 2", 23456L);
        Item itemThree = new Item("Тестовая заявка 3", "Тестовое описание заявки 3", 34567L);
        expected.add(itemOne);
        expected.add(itemTwo);
        expected.add(itemThree);

        MemTracker memTracker = new MemTracker();
        memTracker.add(itemOne);
        memTracker.add(itemTwo);
        memTracker.add(itemThree);

        assertThat(memTracker.findAll(), is(expected));
    }

    /**
     * Тест, проверяющий нахождение заявок с заданным именем, в случае, когда все заявки в трекере - с заданными именем.
     */
    @Test
    public void whenExistTwoItemWithSameNameThenFindTwoItems() {
        MemTracker memTracker = new MemTracker();
        Item itemOne = new Item("Тестовая заявка", "Тестовое описание заявки 1", 12345L);
        Item itemTwo = new Item("Тестовая заявка", "Тестовое описание заявки 2", 54321L);
        memTracker.add(itemOne);
        memTracker.add(itemTwo);
        String sameSummary = "Тестовая заявка";
        ArrayList<Item> expected = new ArrayList<>();
        expected.add(itemOne);
        expected.add(itemTwo);
        assertThat(expected, is(memTracker.findByName(sameSummary)));
    }

    /**
     * Тест, проверяющий нахождение заявки с именем, в случае, когда не все заявки в трекере - с заданным именем.
     */
    @Test
    public void whenExistOneItemWithNameThenFindingOneItem() {
        MemTracker memTracker = new MemTracker();
        Item itemOne = new Item("Первая тестовая заявка", "Тестовое описание первой заявки", 12345L);
        Item itemTwo = new Item("Вторая тестовая заявка", "Тестовое описание второй заявки", 54321L);
        memTracker.add(itemOne);
        memTracker.add(itemTwo);
        String summary = "Первая тестовая заявка";
        ArrayList<Item> expected = new ArrayList<>();
        expected.add(itemOne);
        assertThat(expected, is(memTracker.findByName(summary)));
    }

    /**
     * Тест, проверяющий нахождение заявки с именем, в случае, когда в трекере нет заявок с таким имененм.
     */
    @Test
    public void whenFindingByNotExistingNameThenNotFindingIt() {
        MemTracker memTracker = new MemTracker();
        Item item = new Item("Тестовая заявка", "Тестовое описание заявки", 12345L);
        memTracker.add(item);
        assertNull(memTracker.findByName(""));
    }

    /**
     * Тест, проверяющий поиск заявки по id, в случае, когда заявка с таким id существует.
     */
    @Test
    public void whenFindingByIdThenFindingIt() {
        MemTracker memTracker = new MemTracker();
        Item item = new Item("Тестовая заявка", "Тестовое описание заявки", 12345L);
        memTracker.add(item);
        String id = memTracker.findAll().get(0).getId();
        assertThat(id, is(memTracker.findById(id).getId()));
    }

    /**
     * Тест, проверяющий поиск заявки по id, в случае, когда заявки с таким id не существует.
     */
    @Test
    public void whenFindingByNotExistingIdThenReturnsNull() {
        MemTracker memTracker = new MemTracker();
        Item item = new Item("Тестовая заявка", "Тестовое описание заявки", 12345L);
        memTracker.add(item);
        String id = "123";
        assertNull(memTracker.findById(id));
    }
}