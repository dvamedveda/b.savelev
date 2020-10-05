package ru.job4j.soft;

import java.io.*;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация кэша на мягких ссылках.
 */
public class SoftCache implements Cache {

    /**
     * Место хранения закэшированных данных.
     */
    private Map<String, SoftReference<String>> cache = new HashMap<>();

    /**
     * Путь по которому загружаются файлы.
     */
    private String path;

    public SoftCache(String path) {
        this.path = path + File.separator;
    }

    /**
     * Получение содержимого ресурса по его пути.
     * Если ресурс загружен - содержимое вернется из кэша.
     * Если ресурс не в кэше - он будет загружен или перезагружен(с уведомлением в консоль).
     *
     * @param key имя файла.
     * @return содержимое файла.
     */
    public String get(String key) {
        String result;
        String value;
        if (!cache.containsKey(key)) {
            value = load(key);
            cache.put(key, new SoftReference<String>(value));
            result = cache.get(key).get();
        } else if (cache.containsKey(key) && cache.get(key).get() == null) {
            System.out.println("===========================================================");
            System.out.println("No resource loaded, reload into cache...");
            System.out.println("===========================================================");
            value = load(key);
            cache.replace(key, new SoftReference<String>(value));
            result = cache.get(key).get();
        } else {
            result = cache.get(key).get();
        }
        return result;
    }

    /**
     * Вспомогательный метод для загрузки содержимого файла в кэш.
     *
     * @param name путь к файлу.
     * @return содержимое файла.
     */
    private String load(String name) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(new File(this.path + name)))) {
            while (in.ready()) {
                builder.append(in.readLine()).append('\n');
            }
        } catch (FileNotFoundException e) {
            System.out.println("File with that name not found! Please, try another name...");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}