package ru.job4j.chess.exceptions;

public class ImpossibleMoveException extends RuntimeException {

    public ImpossibleMoveException(){
        super();
    }

    public ImpossibleMoveException(String message) {
        super(message);
    }
}