package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

public interface Figure {

    Cell[] wayFromTo(Cell source, Cell dest) throws ImpossibleMoveException;

    Figure placeFigure(Cell position);
}