package ru.job4j.io.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс содержит методы для работы с конфигами.
 */
public class Config {

    /**
     * Путь к файлу конфига, с которым работаем.
     */
    private final String path;

    /**
     * Карта, содержащая пары "ключ-значение" из конфига.
     */
    private final Map<String, String> values = new HashMap<>();

    /**
     * Конструктор класса для работы с конфигом.
     *
     * @param path
     */
    public Config(final String path) {
        this.path = path;
    }

    /**
     * Метод для загрузки пар "ключ-значение" из файла конфига.
     */
    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            while (reader.ready()) {
                String line = reader.readLine();
                String key = "";
                String value = "";
                if (line.contains("#")) {
                    line = line.substring(0, line.indexOf("#"));
                }
                if (!line.isEmpty()) {
                    if (line.contains("=")) {
                        key = line.substring(0, line.indexOf("="));
                        value = line.substring(line.indexOf("=") + 1);
                    }
                }
                if (!key.isEmpty() && !value.isEmpty()) {
                    this.values.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для получения значения из конфига по его ключу.
     *
     * @param key ключ, по которому ищем значение.
     * @return значение, найденное по ключу.
     */
    public String value(String key) {
        return this.values.get(key);
    }
}