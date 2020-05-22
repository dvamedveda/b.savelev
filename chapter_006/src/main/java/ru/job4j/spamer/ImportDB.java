package ru.job4j.spamer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * Класс, позволяющий импортировать спамеров в бд.
 */
public class ImportDB {

    /**
     * Поле хранящее настройки подключения к бд.
     */
    private Properties cfg;

    /**
     * Поле хранящее имя файла, содержающего список спамеров.
     */
    private String dump;

    /**
     * Конструктор.
     *
     * @param cfg  настройки подключения к бд.
     * @param dump имя файла со спамерами.
     */
    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    /**
     * Получение списка спамеров из файла.
     *
     * @return список спамеров.
     * @throws IOException ошибки при работе с файлом.
     */
    public List<User> load() throws IOException {
        List<User> users = new ArrayList<User>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.dump))) {
            while (reader.ready()) {
                Scanner scanner = new Scanner(reader.readLine());
                scanner.useDelimiter(";");
                users.add(new User(scanner.next(), scanner.next()));
            }
        }
        return users;
    }

    /**
     * Сохранение списка спамеров в бд.
     *
     * @param users список спамеров.
     * @throws ClassNotFoundException ошибки при загрузке драйвера бд.
     * @throws SQLException           ошибки при работе с бд.
     */
    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(this.cfg.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(this.cfg.getProperty("jdbc.url"), this.cfg.getProperty("jdbc.username"), this.cfg.getProperty("jdbc.password"))) {
            for (User user : users) {
                try (PreparedStatement preparedStatement = connection.prepareStatement("insert into users(name, email) values (?, ?)")) {
                    preparedStatement.setString(1, user.getName());
                    preparedStatement.setString(2, user.getEmail());
                    preparedStatement.executeUpdate();
                }
            }
        }
    }

    /**
     * Точка запуска.
     *
     * @param args аргументы запуска
     * @throws Exception ошибки при чтении файлов/аргументов.
     */
    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("chapter_006/src/main/resources/spamer.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "chapter_006/src/main/resources/dump.txt");
        db.save(db.load());
    }
}