package ru.job4j.integration;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Класс хранилища заказов.
 */
public class OrderStore {

    /**
     * Пул соединений к бд.
     */
    private final BasicDataSource pool;

    /**
     * Конструктор хранилища.
     *
     * @param pool пул соединений к бд.
     */
    public OrderStore(BasicDataSource pool) {
        this.pool = pool;
    }

    /**
     * Метод сохранения заказа в хранилище.
     *
     * @param order объект заказа.
     * @return объект заказа с присвоенным идентификатором.
     */
    public Order save(Order order) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "insert into orders(name, description, created) values (?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, order.getName());
            statement.setString(2, order.getDescription());
            statement.setTimestamp(3, order.getCreated());
            statement.execute();
            ResultSet id = statement.getGeneratedKeys();
            if (id.next()) {
                order.setId(id.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    /**
     * Поиск всех заказов в хранилище.
     *
     * @return список всех заказов.
     */
    public Collection<Order> findAll() {
        List<Order> result = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from orders")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(new Order(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getTimestamp("created")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Поиск заказа по идентификатору.
     *
     * @param id идентифкатор.
     * @return найденный заказ.
     */
    public Order findById(int id) {
        Order result = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from orders where id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = new Order(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getTimestamp("created")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Обновление заказа в хранилище.
     *
     * @param order объект заказа.
     */
    public void updateOrder(Order order) {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "update orders set name = ?, description = ? where id = ?")) {
            statement.setString(1, order.getName());
            statement.setString(2, order.getDescription());
            statement.setInt(3, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Поиск заказа по названию в хранилище.
     *
     * @param name название заказа для поиска.
     * @return объект заказа.
     */
    public Order findByName(String name) {
        Order result = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from orders where name = ?")) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = new Order(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getTimestamp("created")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}