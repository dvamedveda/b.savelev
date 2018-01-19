package ru.job4j.chess.figures;


import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

import java.util.Arrays;

/**
 * Реализация фигуры Пешка.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Pawn implements Figure {

    /**
     * Текущая позиция.
     */
    private final Cell currentCell;

    /**
     * Конструктор фигуры Пешка.
     *
     * @param position позиция, на которой создать фигуру.
     */
    public Pawn(Cell position) {
        this.currentCell = position;
    }

    /**
     * Геттер текущей позиции.
     *
     * @return клетка на поле доски.
     */
    public Cell getCurrentCell() {
        return this.currentCell;
    }

    /**
     * Создать копию фигуры для ее переноса в другую ячейку на доске.
     *
     * @param position позиция, на которой создать фигуру.
     * @return новая фигура.
     */
    public Figure copy(Cell position) {
        return new Pawn(position);
    }

    /**
     * Просчитать ход фигуры.
     *
     * @param source исходная клетка на доске.
     * @param dest конечная клетка на доске.
     * @return массив ячеек - путь, который нужно пройти фигуре.
     * @throws ImpossibleMoveException бросается, если ход невозможен по правилам.
     */
    @Override
    public Cell[] wayFromTo(Cell source, Cell dest) throws ImpossibleMoveException {

        int x1 = source.getX();
        int y1 = source.getY();
        int x2 = dest.getX();
        int y2 = dest.getY();
        int waypoints = 0;
        Cell[] way = new Cell[8];

         /*
        Проверка, может ли фигура ходить так:
        вверх на одну клетку, или на две, если стартовая клетка - вторая снизу, в пределах доски
         */
        if (!((x1 == x2 && y1 > y2 && (y2 == y1 - 2 && y1 == 6)) || (x1 == x2 && y1 > y2 && (y2 == y1 - 1)))
                || (x2 < 0 || y2 < 0)) {
            throw new ImpossibleMoveException();
        } else if ((y2 == y1 - 2 && y1 == 6)) {
            for (int y = y1 - 1, count = waypoints; y >= y2; y--, count++) {
                way[count] = new Cell(x1, y);
            }
        } else {
            way[waypoints] = new Cell(x1, y1 - 1);
        }

        for (int index = 0; index < way.length; index++) {
            if (way[index] == null) {
                way = Arrays.copyOf(way, index);
            }
        }

        return way;
    }
}