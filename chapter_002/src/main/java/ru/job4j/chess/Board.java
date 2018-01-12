package ru.job4j.chess;

import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.figures.Bishop;
import ru.job4j.chess.figures.Figure;

public class Board {
    private Figure[][] fields = new Figure[8][8];

    public void move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {

        if (fields[source.getX()][source.getY()] == null) {
            throw new FigureNotFoundException();
        }

        Figure figure = fields[source.getX()][source.getY()];

        Cell[] way = figure.wayFromTo(source, dest);

        for (Cell cell : way) {
            if (fields[cell.getX()][cell.getY()] != null) {
                throw new OccupiedWayException();
            }
        }

        this.placeFigure(new Bishop(dest), dest);
    }

    public void placeFigure(Figure figure, Cell cell) {
        this.fields[cell.getX()][cell.getY()] = figure.placeFigure(cell);
    }
}