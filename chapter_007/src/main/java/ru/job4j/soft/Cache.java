package ru.job4j.soft;

/**
 * Интерфейс кэша
 */
public interface Cache {

    /**
     * Получить содержимое ресурса по ключу.
     *
     * @param key имя файла.
     * @return содержимое файла.
     */
    String get(String key);
}