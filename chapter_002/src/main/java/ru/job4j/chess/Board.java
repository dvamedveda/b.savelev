package ru.job4j.chess;

import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.figures.Figure;

public class Board {
    private Figure[][] fields = new Figure[8][8];
    private Figure currentFigure;

    public void setCurrentFigure(Figure figure) {
        this.currentFigure = figure;
        this.placeFigure(this.currentFigure, this.currentFigure.getCurrentCell());
    }

    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {

        boolean result = true;

        if (fields[source.getX()][source.getY()] == null) {
            result = false;
            throw new FigureNotFoundException();
        }

        Figure figure = fields[source.getX()][source.getY()];

        Cell[] way = figure.wayFromTo(source, dest);

        for (Cell cell : way) {
            if (fields[cell.getX()][cell.getY()] != null) {
                result = false;
                throw new OccupiedWayException();
            }
        }

        this.placeFigure(this.currentFigure, dest);
        return result;
    }

    private void placeFigure(Figure figure, Cell cell) {
        this.fields[cell.getX()][cell.getY()] = figure.copy(cell);
    }
}