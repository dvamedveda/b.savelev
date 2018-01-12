package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

import java.util.Arrays;

public class Bishop implements Figure {

    private final Cell currentCell;

    public Bishop(Cell position) {
        this.currentCell = position;
    }

    public Figure placeFigure(Cell position) {
         return new Bishop(position);
    }

    @Override
    public Cell[] wayFromTo(Cell source, Cell dest) throws ImpossibleMoveException {
        int x1 = source.getX();
        int y1 = source.getY();
        int x2 = dest.getX();
        int y2 = dest.getY();
        int waypoints = 0;
        Cell[] way = new Cell[8];

        if (Math.abs(x1 - x2) != Math.abs(y1 - y2) || (x2 >= 8 || y2 >= 8) || (x2 < 0 || y2 < 0)) {
            throw new ImpossibleMoveException();
        } else {
            if ( x1 < x2 && y1 > y2 ) {
                for (int x = x1 + 1, y = y1 - 1, count = waypoints; x == x2; x++, y--, count++) {
                    way[count] = new Cell(x, y);
                }
            } else if ( x1 < x2 && y1 < y2 ) {
                for (int x = x1 + 1, y = y1 + 1, count = waypoints; x <= x2; x++, y++, count++) {
                    way[count] = new Cell(x, y);
                }
            } else if ( x1 > x2 && y1 < y2 ) {
                for (int x = x1 - 1, y = y1 + 1, count = waypoints; x == x2; x--, y++, count++) {
                    way[count] = new Cell(x, y);
                }
            } else if ( x1 > x2 && y1 > y2 ) {
                for (int x = x1 - 1, y = y1 - 1, count = waypoints; x <= x2; x--, y--, count++) {
                    way[count] = new Cell(x, y);
                }
            }
        }

        for (int index = 0; index < way.length; index++) {
            if (way[index] == null) {
                way =  Arrays.copyOf(way, index);
            }
        }

        return way;
    }
}