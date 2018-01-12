package ru.job4j.chess.exceptions;

public class FigureNotFoundException extends RuntimeException {

    public FigureNotFoundException() {
        super();
    }

    public FigureNotFoundException(String message) {
        super(message);
    }
}