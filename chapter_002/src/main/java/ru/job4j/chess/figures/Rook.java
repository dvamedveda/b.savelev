package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

import java.util.Arrays;

/**
 * Реализация фигуры Ладья.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Rook implements Figure {

    /**
     * Текущая позиция.
     */
    private final Cell currentCell;

    /**
     * Конструктор фигуры Ладья.
     *
     * @param position позиция, на которой создать фигуру.
     */
    public Rook(Cell position) {
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
        return new Rook(position);
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
        /*
        Проверка, может ли фигура ходить так и просчитываем пройденные клетки.
        Иначе бросаем исключение.
         */
        if (!((x1 != x2 && y1 == y2) || (x1 == x2 && y1 != y2)) || (x2 >= 8 || y2 >= 8) || (x2 < 0 || y2 < 0)) {
            throw new ImpossibleMoveException();
        } else {
            int moveLength = (x1 != x2) ? Math.abs(x1 - x2) : Math.abs(y1 - y2);
            Cell[] waypoints = new Cell[moveLength];
            int horizontal = Integer.compare(x2, x1);
            int vertical = Integer.compare(y2, y1);
            for (int index = 0; index != moveLength; index++) {
                x1 += horizontal;
                y1 += vertical;
                waypoints[index] = new Cell(x1, y1);
            }
            return waypoints;
        }
    }
}