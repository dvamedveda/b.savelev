package ru.job4j.chess;

import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.Knight;

/**
 * Реализация шахматной доски.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Board {

    /**
     * Массив клеток шахматного поля.
     */
    private Figure[][] fields = new Figure[8][8];

    /**
     * Текущая фигура для хода.
     */
    private Figure currentFigure;

    /**
     * Установить текущую фигуру для хода.
     *
     * @param figure фигура для хода.
     */
    public void setCurrentFigure(Figure figure) {
        this.currentFigure = figure;
        this.placeFigure(this.currentFigure, this.currentFigure.getCurrentCell());
    }

    public Figure[][] getFields() {
        return this.fields;
    }

    /**
     * Произвести ход фигурой на доске.
     *
     * @param source исходная клетка для хода.
     * @param dest конечная клетка для хода.
     * @return ход выполнен или нет.
     * @throws ImpossibleMoveException бросяется, если ход нельзя выполнить по правилам.
     * @throws OccupiedWayException    бросается, если путь для фигуры занят другой фигурой.
     * @throws FigureNotFoundException бросается, если фигура для хода отсутствует.
     */
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
        this.placeFigure(this.currentFigure, way[way.length - 1]);
        this.fields[source.getX()][source.getY()] = null;
        return result;
    }

    /**
     * Расположить фигуру на клетке доски.
     *
     * @param figure фигура
     * @param cell   клетка
     */
    private void placeFigure(Figure figure, Cell cell) {
        this.fields[cell.getX()][cell.getY()] = figure.copy(cell);
    }
}