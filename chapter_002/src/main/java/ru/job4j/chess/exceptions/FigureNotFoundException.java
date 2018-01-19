package ru.job4j.chess.exceptions;

/**
 * Исключение для ситуации, когда фигура не найдена на доске.
 */
public class FigureNotFoundException extends RuntimeException {

    /**
     * Простой конструктор.
     */
    public FigureNotFoundException() {
        super();
    }

    /**
     * Конструктор с сообщением.
     *
     * @param message сообщение.
     */
    public FigureNotFoundException(String message) {
        super(message);
    }
}