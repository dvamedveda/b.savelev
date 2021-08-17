package ru.job4j.tracker.ui;

/**
 * Исключение произвольного класса.
 * Используется в случаях, когда пользователь вводит несуществующие пункты меню трекера.
 */
public class MenuOutException extends RuntimeException {

    public MenuOutException(String message) {
        super(message);
    }

}