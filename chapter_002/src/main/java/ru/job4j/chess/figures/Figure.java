package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;

/**
 * Абстрактная фигура.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public interface Figure {

    /**
     * Получаем текущую клетку фигуры.
     *
     * @return текущая клетка на доске.
     */
    Cell getCurrentCell();

    /**
     * Просчитать путь фигуры по правилам, в зависимости от типа самой фигуры.
     *
     * @param source исходная клетка на доске.
     * @param dest   конечная клетка на доске.
     * @return путь который нужно пройти фигуре.
     * @throws ImpossibleMoveException бросается, если ход невозможен.
     */
    Cell[] wayFromTo(Cell source, Cell dest) throws ImpossibleMoveException;

    /**
     * Создать копию фигуры для ее переноса в другую ячейку на доске.
     *
     * @param position позиция, на которой создать фигуру.
     * @return новая фигура.
     */
    Figure copy(Cell position);

    default boolean equals(Figure figure) {
        return this.getCurrentCell().getX() == figure.getCurrentCell().getX()
                && this.getCurrentCell().getY() == figure.getCurrentCell().getY()
                && this.getClass() == figure.getClass();
    }
}