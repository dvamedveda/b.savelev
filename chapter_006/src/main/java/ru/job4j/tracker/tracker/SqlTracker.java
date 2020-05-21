package ru.job4j.tracker.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Класс реализующий хранилище заявок с использованием PostgreSQL.
 */
public class SqlTracker implements Store {
    private Connection connection;

    /**
     * Инициализация подключения к базе данных.
     */
    @Override
    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    /**
     * Закрытие соединения с бд.
     *
     * @throws Exception ошибки закрытия бд.
     */
    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * Добавление заявки в хранилище.
     *
     * @param item заявка.
     * @return добавленная заявка.
     */
    @Override
    public Item add(Item item) {
        try (PreparedStatement statement = connection.prepareStatement(
                "insert into items(name, description, created) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getSummary());
            statement.setString(2, item.getDescription());
            statement.setTimestamp(3, new Timestamp(item.getCreated()));
            statement.executeUpdate();
            try (ResultSet ids = statement.getGeneratedKeys()) {
                while (ids.next()) {
                    item.setId(ids.getString("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    /**
     * Замена заявки по ее идентификатору.
     *
     * @param id   идентификатор.
     * @param item новая заявка.
     * @return результат замены.
     */
    @Override
    public boolean replace(String id, Item item) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement("select * from items where id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setInt(1, Integer.parseInt(id));
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                resultSet.updateString("name", item.getSummary());
                resultSet.updateString("description", item.getDescription());
                resultSet.updateTimestamp("created", new Timestamp(item.getCreated()));
                resultSet.updateRow();
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Удаление заявки по ее идентификатору.
     *
     * @param id идентификатор.
     * @return результат удаления.
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement("delete from items where id = ?")) {
            statement.setInt(1, Integer.parseInt(id));
            int modified = statement.executeUpdate();
            if (modified > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Поиск всех заявок.
     *
     * @return список заявок.
     */
    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("select * from items")) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Item resultItem = new Item(result.getString("name"), result.getString("description"), result.getTimestamp("created").getTime());
                    resultItem.setId(String.valueOf(result.getInt("id")));
                    items.add(resultItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Поиск заявки по имени.
     *
     * @param key имя заявки.
     * @return найденная заявка.
     */
    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("select * from items where name = ?")) {
            statement.setString(1, key);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Item resultItem = new Item(result.getString("name"), result.getString("description"), result.getTimestamp("created").getTime());
                    resultItem.setId(String.valueOf(result.getInt("id")));
                    items.add(resultItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Поиск заявки по идентификатору.
     *
     * @param id идентификатор.
     * @return найденная заявка.
     */
    @Override
    public Item findById(String id) {
        Item item = null;
        try (PreparedStatement statement = connection.prepareStatement("select * from items where id = ?")) {
            statement.setInt(1, Integer.parseInt(id));
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    item = new Item(result.getString("name"), result.getString("description"), result.getTimestamp("created").getTime());
                    item.setId(String.valueOf(result.getInt("id")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
}