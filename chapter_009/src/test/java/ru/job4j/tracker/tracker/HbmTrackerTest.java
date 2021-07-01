package ru.job4j.tracker.tracker;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;

/**
 * Тесты класса HbmTracker
 */
public class HbmTrackerTest {

    /**
     * Проверка добавления заявки в трекер.
     */
    @Test
    public void whenAddItemThenSuccess() {
        try (Store tracker = new HbmTracker()) {
            Item item = new Item("test name", "test description", 1L);
            Item addedItem = tracker.add(item);
            List<Item> resultList = tracker.findAll();
            Assert.assertThat(resultList.size(), is(1));
            Assert.assertThat(resultList.get(0).getId(), is(addedItem.getId()));
            Assert.assertThat(resultList.get(0).getName(), is(addedItem.getName()));
            Assert.assertThat(resultList.get(0).getDescription(), is(addedItem.getDescription()));
            Assert.assertThat(resultList.get(0).getCreated(), is(addedItem.getCreated()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверка обновления заявки в трекере.
     */
    @Test
    public void whenReplaceItemThenSuccess() {
        try (Store tracker = new HbmTracker()) {
            Item item = new Item("must update name", "must update description", 11L);
            Item toUpdate = tracker.add(item);
            Item updater = new Item("updated name", "updated description", 22L);
            tracker.replace(Integer.toString(toUpdate.getId()), updater);
            List<Item> resultList = tracker.findAll();
            Assert.assertThat(resultList.size(), is(1));
            Item result = resultList.get(0);
            Assert.assertThat(result.getId(), is(toUpdate.getId()));
            Assert.assertThat(result.getName(), is(updater.getName()));
            Assert.assertThat(result.getDescription(), is(updater.getDescription()));
            Assert.assertThat(result.getCreated(), is(updater.getCreated()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверка удаления заявки из трекера.
     */
    @Test
    public void whenDeleteItemThenSuccess() {
        try (Store tracker = new HbmTracker()) {
            Item item = new Item("must delete name", "must delete description", 111L);
            Item toDelete = tracker.add(item);
            tracker.delete(Integer.toString(toDelete.getId()));
            List<Item> resultList = tracker.findAll();
            Assert.assertThat(resultList.size(), is(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверка поиска по имени заявки в трекере.
     */
    @Test
    public void whenFindByNameThenSuccess() {
        try (Store tracker = new HbmTracker()) {
            Item itemOne = new Item("for find by name name one", "for find by name description one", 4L);
            Item itemTwo = new Item("for find by name name two", "for find by name description two", 5L);
            Item addedOne = tracker.add(itemOne);
            Item addedTwo = tracker.add(itemTwo);
            List<Item> resultList = tracker.findByName(itemTwo.getName());
            Assert.assertThat(resultList.size(), is(1));
            Item result = resultList.get(0);
            Assert.assertThat(result.getId(), is(addedTwo.getId()));
            Assert.assertThat(result.getName(), is(addedTwo.getName()));
            Assert.assertThat(result.getDescription(), is(addedTwo.getDescription()));
            Assert.assertThat(result.getCreated(), is(addedTwo.getCreated()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверка поиска по идентификатору заявки в трекере.
     */
    @Test
    public void whenFindByIdThenSuccess() {
        try (Store tracker = new HbmTracker()) {
            Item itemOne = new Item("for find by id name one", "for find by id description one", 6L);
            Item itemTwo = new Item("for find by id name two", "for find by id description two", 7L);
            Item addedOne = tracker.add(itemOne);
            Item addedTwo = tracker.add(itemTwo);
            Item result = tracker.findById(Integer.toString(addedOne.getId()));
            Assert.assertThat(result.getId(), is(addedOne.getId()));
            Assert.assertThat(result.getName(), is(addedOne.getName()));
            Assert.assertThat(result.getDescription(), is(addedOne.getDescription()));
            Assert.assertThat(result.getCreated(), is(addedOne.getCreated()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}