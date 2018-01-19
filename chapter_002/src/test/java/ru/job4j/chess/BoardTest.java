package ru.job4j.chess;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.chess.exceptions.FigureNotFoundException;

/**
 * Класс, содержащий тесты для класса Board.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class BoardTest {
    @Test

    /**
     * Тест, проверяющий, что нельзя двигать фигуру, которой нет в исходной ячейке.
     */
    public void whenMoveUnexistFigureThenGetException() throws FigureNotFoundException {
        Board board = new Board();
        Cell source = new Cell(3, 4);
        Cell dest = new Cell(5, 2);
        try {
            board.move(source, dest);
            Assert.fail("FigureNotFoundExcepion expected");
        } catch (FigureNotFoundException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }
}