package ru.job4j.chess.exceptions;

/**
 * Исключение для ситуации, когда ход фигурой невозможен по тем или иным причинам.
 */
public class ImpossibleMoveException extends RuntimeException {

    /**
     * Простой конструктор.
     */
    public ImpossibleMoveException() {
        super();
    }

    /**
     * Конструктор с сообщением.
     *
     * @param message сообщение.
     */
    public ImpossibleMoveException(String message) {
        super(message);
    }
}