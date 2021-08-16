package ru.job4j.tracker.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Тесты для класса TrackerSQL.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class SqlTrackerTest {

    public Connection init() {
        Connection result = null;
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            result = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Тест, который проверяет добавление заявки в трекер.
     */
    @Test
    public void whenAddingItemThenAddingIt() {
        try (SqlTracker sqlTracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("Тестовая заявка", "Тестовое описание заявки", 12345L);
            sqlTracker.add(item);
            assertThat(sqlTracker.findByName("Тестовая заявка").size(), is(1));
            assertThat(sqlTracker.findByName("Тестовая заявка").get(0).getSummary(), is("Тестовая заявка"));
            assertThat(sqlTracker.findByName("Тестовая заявка").get(0).getDescription(), is("Тестовое описание заявки"));
            assertThat(sqlTracker.findByName("Тестовая заявка").get(0).getCreated(), is(12345L));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Тест, который проверяет обновление заявки в трекере.
     */
    @Test
    public void whenUpdateItemThenItUpdating() {
        try (SqlTracker sqlTracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("Тестовая заявка", "Тестовое описание заявки", 12345L);
            item = sqlTracker.add(item);
            Item newItem = new Item("Измененная тестовая заявка", "Измененное тестовое описание заявки", 54321L);
            sqlTracker.replace(item.getId(), newItem);
            Item foundedItem = sqlTracker.findById(item.getId());

            assertThat(foundedItem.getSummary(), is(newItem.getSummary()));
            assertThat(foundedItem.getDescription(), is(newItem.getDescription()));
            assertThat(foundedItem.getCreated(), is(newItem.getCreated()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Тест, который проверяет удаление заявки из трекера.
     */
    @Test
    public void whenDeletingItemThenItDeleting() {
        try (SqlTracker sqlTracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("Тестовая заявка", "Тестовое описание заявки", 12345L);
            Item addedItem = sqlTracker.add(item);
            sqlTracker.delete(addedItem.getId());
            assertNull(sqlTracker.findById(addedItem.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        try (SqlTracker sqlTracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            String idFirst = sqlTracker.add(itemOne).getId();
            String idSecond = sqlTracker.add(itemTwo).getId();
            String idThirst = sqlTracker.add(itemThree).getId();
            List<Item> result = sqlTracker.findAll();
            assertThat(result.size(), is(expected.size()));
            assertThat(result.get(0).getId(), is(idFirst));
            assertThat(result.get(0).getSummary(), is(itemOne.getSummary()));
            assertThat(result.get(0).getDescription(), is(itemOne.getDescription()));
            assertThat(result.get(0).getCreated(), is(itemOne.getCreated()));
            assertThat(result.get(1).getId(), is(idSecond));
            assertThat(result.get(1).getSummary(), is(itemTwo.getSummary()));
            assertThat(result.get(1).getDescription(), is(itemTwo.getDescription()));
            assertThat(result.get(1).getCreated(), is(itemTwo.getCreated()));
            assertThat(result.get(2).getId(), is(idThirst));
            assertThat(result.get(2).getSummary(), is(itemThree.getSummary()));
            assertThat(result.get(2).getDescription(), is(itemThree.getDescription()));
            assertThat(result.get(2).getCreated(), is(itemThree.getCreated()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Тест, проверяющий нахождение заявок с заданным именем, в случае, когда все заявки в трекере - с заданными именем.
     */
    @Test
    public void whenExistTwoItemWithSameNameThenFindTwoItems() {
        try (SqlTracker sqlTracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item itemOne = new Item("Тестовая заявка", "Тестовое описание заявки 1", 12345L);
            Item itemTwo = new Item("Тестовая заявка", "Тестовое описание заявки 2", 54321L);
            sqlTracker.add(itemOne);
            sqlTracker.add(itemTwo);
            String sameSummary = "Тестовая заявка";
            ArrayList<Item> expected = new ArrayList<>();
            expected.add(itemOne);
            expected.add(itemTwo);
            ArrayList<Item> foundItems = (ArrayList<Item>) sqlTracker.findByName(sameSummary);
            assertThat(expected.size(), is(foundItems.size()));
            assertThat(expected.get(0).getDescription(), is(foundItems.get(0).getDescription()));
            assertThat(expected.get(0).getCreated(), is(foundItems.get(0).getCreated()));
            assertThat(expected.get(1).getDescription(), is(foundItems.get(1).getDescription()));
            assertThat(expected.get(1).getCreated(), is(foundItems.get(1).getCreated()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Тест, проверяющий нахождение заявки с именем, в случае, когда не все заявки в трекере - с заданным именем.
     */
    @Test
    public void whenExistOneItemWithNameThenFindingOneItem() {
        try (SqlTracker sqlTracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item itemOne = new Item("Первая тестовая заявка", "Тестовое описание первой заявки", 12345L);
            Item itemTwo = new Item("Вторая тестовая заявка", "Тестовое описание второй заявки", 54321L);
            sqlTracker.add(itemOne);
            sqlTracker.add(itemTwo);
            String summary = "Первая тестовая заявка";
            ArrayList<Item> expected = new ArrayList<>();
            expected.add(itemOne);
            assertThat(expected.size(), is(sqlTracker.findByName(summary).size()));
            assertThat(expected.get(0).getDescription(), is(sqlTracker.findByName(summary).get(0).getDescription()));
            assertThat(expected.get(0).getCreated(), is(sqlTracker.findByName(summary).get(0).getCreated()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Тест, проверяющий нахождение заявки с именем, в случае, когда в трекере нет заявок с таким имененм.
     */
    @Test
    public void whenFindingByNotExistingNameThenNotFindingIt() {
        try (SqlTracker sqlTracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("Тестовая заявка", "Тестовое описание заявки", 12345L);
            sqlTracker.add(item);
            assertThat(0, is(sqlTracker.findByName("").size()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Тест, проверяющий поиск заявки по id, в случае, когда заявка с таким id существует.
     */
    @Test
    public void whenFindingByIdThenFindingIt() {
        try (SqlTracker sqlTracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("Тестовая заявка", "Тестовое описание заявки", 12345L);
            String id = sqlTracker.add(item).getId();
            assertThat(id, is(sqlTracker.findById(id).getId()));
            assertThat(item.getSummary(), is(sqlTracker.findById(id).getSummary()));
            assertThat(item.getDescription(), is(sqlTracker.findById(id).getDescription()));
            assertThat(item.getCreated(), is(sqlTracker.findById(id).getCreated()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Тест, проверяющий поиск заявки по id, в случае, когда заявки с таким id не существует.
     */
    @Test
    public void whenFindingByNotExistingIdThenReturnsNull() {
        try (SqlTracker sqlTracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = new Item("Тестовая заявка", "Тестовое описание заявки", 12345L);
            sqlTracker.add(item);
            assertNull(sqlTracker.findById("12345"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}