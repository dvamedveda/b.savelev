package ru.job4j.io.config;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Класс содержит тесты методов класса Config.
 */
public class ConfigTest {

    /**
     * Данный тест проверяет корректную загрузку конфига,
     * если конфиг не содержит комментариев и пустых строк, а также не пуст.
     */
    @Test
    public void whenFileWithoutCommentsThenSuccessLoad() {
        String path = "./test_configs/without_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(config.value("hibernate.connection.url"), is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
        assertThat(config.value("hibernate.connection.driver_class"), is("org.postgresql.Driver"));
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.connection.password"), is("password"));
    }

    /**
     * Данный тест проверяет корректную загрузку конфига,
     * если конфиг содержит комментарии.
     */
    @Test
    public void whenFileWithCommentsThenSuccessLoad() {
        String path = "./test_configs/with_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(config.value("hibernate.connection.url"), is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
        assertThat(config.value("hibernate.connection.driver_class"), is("org.postgresql.Driver"));
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.connection.password"), is("password"));
    }

    /**
     * Данный тест проверяет корректную загрузку конфига,
     * если конфиг содержит комментарии и пустые строки.
     */
    @Test
    public void whenFileWithEmptyLinesThenSuccessLoad() {
        String path = "./test_configs/with_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(config.value("hibernate.connection.url"), is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
        assertThat(config.value("hibernate.connection.driver_class"), is("org.postgresql.Driver"));
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.connection.password"), is("password"));
    }

    /**
     * Данный тест проверяет отсутствие значений, если файл конфига пуст.
     */
    @Test
    public void whenFileEmptyThenNotLoad() {
        String path = "./test_configs/empty.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.value("hibernate.dialect"));
        assertNull(config.value("hibernate.connection.url"));
        assertNull(config.value("hibernate.connection.driver_class"));
        assertNull(config.value("hibernate.connection.username"));
        assertNull(config.value("hibernate.connection.password"));
    }
}