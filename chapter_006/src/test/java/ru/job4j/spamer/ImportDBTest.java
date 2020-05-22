package ru.job4j.spamer;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.core.Is.is;

/**
 * Тестирование класса для работы с пользователями.
 */
public class ImportDBTest {

    /**
     * Проверка успешной загрузки списка пользователей из файла.
     *
     * @throws IOException ошибки при работе с файлами.
     */
    @Test
    public void whenDumpExistsThenLoadSuccess() throws IOException {
        InputStream in = new FileInputStream("src/test/resources/spamer.properties");
        String dump = new String("src/test/resources/dump.txt");
        Properties properties = new Properties();
        properties.load(in);
        ImportDB importDB = new ImportDB(properties, dump);
        List<User> users = importDB.load();
        User expected1 = new User("Petr Arsentev", "parsentev@yandex.ru");
        User expected2 = new User("Ivan Ivanov", "iivanov@gmail.com");
        User actual1 = users.get(0);
        User actual2 = users.get(1);
        Assert.assertThat(actual1.getName(), is(expected1.getName()));
        Assert.assertThat(actual1.getEmail(), is(expected1.getEmail()));
        Assert.assertThat(actual2.getName(), is(expected2.getName()));
        Assert.assertThat(actual2.getEmail(), is(expected2.getEmail()));
    }
}