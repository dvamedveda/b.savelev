package ru.job4j.threads.nonblock;

/**
 * Класс, реализующий исключение для неблокирующего кэша.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class OptimisticException extends RuntimeException {

    public OptimisticException(String message) {
        super(message);
    }
}