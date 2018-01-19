package ru.job4j.chess.exceptions;

/**
 * Исключение для ситуации, когда ход фигурой невозможен из-за другой фигуры на ее пути.
 */
public class OccupiedWayException extends RuntimeException {

    /**
     * Простой конструктор.
     */
    public OccupiedWayException() {
        super();
    }

    /**
     * Конструктор с сообщением.
     *
     * @param message сообщение.
     */
    public OccupiedWayException(String message) {
        super(message);
    }
}