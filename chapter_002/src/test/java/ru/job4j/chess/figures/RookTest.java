package ru.job4j.chess.figures;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.chess.Board;
import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;

/**
 * Класс, содержащий тесты для класса Rook(Ладья).
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class RookTest {

    /**
     * Тест, проверяющий возможность хода для ладьи вверх.
     */
    @Test
    public void whenMoveRookWithValidUpWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(3, 0);
        board.setCurrentFigure(new Rook(source));
        board.move(source, dest);
        Figure expected = new Rook(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для ладьи вправо.
     */
    @Test
    public void whenMoveRookWithValidRightWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(6, 3);
        board.setCurrentFigure(new Rook(source));
        board.move(source, dest);
        Figure expected = new Rook(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для ладьи вниз.
     */
    @Test
    public void whenMoveRookWithValidDownWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(3, 6);
        board.setCurrentFigure(new Rook(source));
        board.move(source, dest);
        Figure expected = new Rook(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для ладьи влево.
     */
    @Test
    public void whenMoveRookWithValidLeftWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(0, 3);
        board.setCurrentFigure(new Rook(source));
        board.move(source, dest);
        Figure expected = new Rook(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий невозможность хода для ладьи, если это не разрешено правилами хода фигуры.
     *
     * @throws ImpossibleMoveException исключение бросается, если ход невозможен.
     */
    @Test
    public void whenMoveRookWithInvalidWayThenGetException() throws ImpossibleMoveException {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(5, 1);
        board.setCurrentFigure(new Rook(source));
        try {
            board.move(source, dest);
        } catch (ImpossibleMoveException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }

    /**
     * Тест, проверяющий невозможность хода для ладьи, если конечная ячейка расположена за пределами доски..
     *
     * @throws ImpossibleMoveException исключение бросается, если ход невозможен.
     */
    @Test
    public void whenMoveRookWithInvalidWayOutOfBoardThenGetException() throws ImpossibleMoveException {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(3, 10);
        board.setCurrentFigure(new Rook(source));
        try {
            board.move(source, dest);
        } catch (ImpossibleMoveException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }

    /**
     * Тест, проверяющий невозможность хода для ладьи, если на ее пути стоит другая фигура.
     *
     * @throws ImpossibleMoveException исключение бросается, если ход невозможен.
     */
    @Test
    public void whenMoveRookWithValidOccupiedWayThenGetException() throws OccupiedWayException {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(3, 0);
        Cell occupiedSource = new Cell(0, 1);
        Cell occupiedDest = new Cell(3, 1);
        board.setCurrentFigure(new Rook(occupiedSource));
        board.move(occupiedSource, occupiedDest);
        board.setCurrentFigure(new Rook(source));
        try {
            board.move(source, dest);
        } catch (OccupiedWayException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }
}