package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

import java.util.Arrays;


/**
 * Реализация фигуры Слон.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Bishop implements Figure {

    /**
     * Текущая позиция.
     */
    private final Cell currentCell;

    /**
     * Конструктор фигуры Слон.
     *
     * @param position позиция, на которой создать фигуру.
     */
    public Bishop(Cell position) {
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
        return new Bishop(position);
    }

    /**
     * Просчитать ход фигуры.
     *
     * @param source исходная клетка на доске.
     * @param dest   конечная клетка на доске.
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
        в любое направление по диагонали на любое количество клеток в пределах доски
         */
        if (Math.abs(x1 - x2) != Math.abs(y1 - y2) || (x2 >= 8 || y2 >= 8) || (x2 < 0 || y2 < 0)) {
            throw new ImpossibleMoveException();
        } else {
            if (x1 < x2 && y1 > y2) {
                // направление хода вправо-вверх
                for (int x = x1 + 1, y = y1 - 1, count = waypoints; x <= x2; x++, y--, count++) {
                    way[count] = new Cell(x, y);
                }
            } else if (x1 < x2 && y1 < y2) {
                // направление хода вправо-вниз
                for (int x = x1 + 1, y = y1 + 1, count = waypoints; x <= x2; x++, y++, count++) {
                    way[count] = new Cell(x, y);
                }
            } else if (x1 > x2 && y1 < y2) {
                // направление хода влево-вниз
                for (int x = x1 - 1, y = y1 + 1, count = waypoints; x >= x2; x--, y++, count++) {
                    way[count] = new Cell(x, y);
                }
            } else if (x1 > x2 && y1 > y2) {
                // направление хода влево-вверх
                for (int x = x1 - 1, y = y1 - 1, count = waypoints; x >= x2; x--, y--, count++) {
                    way[count] = new Cell(x, y);
                }
            }
        }

        for (int index = 0; index < way.length; index++) {
            if (way[index] == null) {
                way = Arrays.copyOf(way, index);
            }
        }

        return way;
    }
}