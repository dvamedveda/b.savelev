package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

public interface Figure {

    Cell getCurrentCell();

    Cell[] wayFromTo(Cell source, Cell dest) throws ImpossibleMoveException;

    Figure copy(Cell position);
}