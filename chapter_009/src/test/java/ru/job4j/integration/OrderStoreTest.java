package ru.job4j.integration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;

/**
 * Тесты класса OrderStore.
 */
public class OrderStoreTest {

    /**
     * Пул соединений с хранилищем.
     */
    private BasicDataSource pool = new BasicDataSource();

    /**
     * Подготовка пула соединений, тестовой бд в памяти для тестов.
     *
     * @throws SQLException в случае проблем при работе с бд.
     */
    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("test");
        pool.setPassword("test");
        pool.setMaxTotal(2);
        StringBuilder updateScript = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                OrderStoreTest.class.getClassLoader().getResourceAsStream("./db/update_001.sql")))) {
            reader.lines().forEach(line -> updateScript.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(updateScript.toString()).executeUpdate();
    }

    /**
     * Очистка тестовой бд после тестов.
     *
     * @throws SQLException
     */
    @After
    public void setDown() throws SQLException {
        pool.getConnection().prepareStatement("drop table orders").executeUpdate();
    }

    /**
     * Проверка присутствия заказа в списке всех заказов при сохранении.
     */
    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrderStore store = new OrderStore(pool);
        store.save(Order.of("test order", "test description"));
        List<Order> result = (List<Order>) store.findAll();
        Assert.assertThat(result.size(), is(1));
        Assert.assertThat(result.get(0).getName(), is("test order"));
        Assert.assertThat(result.get(0).getDescription(), is("test description"));
    }

    /**
     * Проверка поиска заказа по идентификатору.
     */
    @Test
    public void whenSearchByIdThenCorrect() {
        OrderStore store = new OrderStore(pool);
        Order order = store.save(Order.of("order by id name", "order by id description"));
        Order result = store.findById(order.getId());
        Assert.assertNotNull(result);
        Assert.assertThat(result.getId(), is(order.getId()));
        Assert.assertThat(result.getName(), is(order.getName()));
        Assert.assertThat(result.getDescription(), is(order.getDescription()));
        Assert.assertThat(result.getCreated(), is(order.getCreated()));
    }

    /**
     * Проверка поиска заказа по имени.
     */
    @Test
    public void whenSearchByNameThenCorrect() {
        OrderStore store = new OrderStore(pool);
        Order order = store.save(Order.of("name of order by name", "description of order by name"));
        Order result = store.findByName(order.getName());
        Assert.assertNotNull(result);
        Assert.assertThat(result.getId(), is(order.getId()));
        Assert.assertThat(result.getName(), is(order.getName()));
        Assert.assertThat(result.getDescription(), is(order.getDescription()));
        Assert.assertThat(result.getCreated(), is(order.getCreated()));
    }

    /**
     * Проверка обновления заказа в хранилище.
     */
    @Test
    public void whenUpdateOrderThenCorrect() {
        OrderStore store = new OrderStore(pool);
        Order order = store.save(Order.of("name that will be updated", "description that will be updated"));
        Order forUpdate = new Order(order.getId(), "updated name", "updated description", order.getCreated());
        store.updateOrder(forUpdate);
        Order result = store.findById(order.getId());
        Assert.assertNotNull(result);
        Assert.assertThat(result.getId(), is(order.getId()));
        Assert.assertThat(result.getName(), is(forUpdate.getName()));
        Assert.assertThat(result.getDescription(), is(forUpdate.getDescription()));
        Assert.assertThat(result.getCreated(), is(order.getCreated()));
    }
}