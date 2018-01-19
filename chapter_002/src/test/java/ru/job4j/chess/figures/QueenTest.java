package ru.job4j.chess.figures;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.chess.Board;
import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;

/**
 * Класс, содержащий тесты для класса Queen(Королева).
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class QueenTest {
    /**
     * Тест, проверяющий возможность хода для королевы вверх.
     */
    @Test
    public void whenMoveQueenWithValidUpWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(3, 0);
        board.setCurrentFigure(new Queen(source));
        board.move(source, dest);
        Figure expected = new Queen(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для королевы вправо.
     */
    @Test
    public void whenMoveQueenWithValidRightWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(6, 3);
        board.setCurrentFigure(new Queen(source));
        board.move(source, dest);
        Figure expected = new Queen(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для королевы вниз.
     */
    @Test
    public void whenMoveQueenWithValidDownWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(3, 6);
        board.setCurrentFigure(new Queen(source));
        board.move(source, dest);
        Figure expected = new Queen(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для королевы влево.
     */
    @Test
    public void whenMoveQueenWithValidLeftWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(0, 3);
        board.setCurrentFigure(new Queen(source));
        board.move(source, dest);
        Figure expected = new Queen(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для королевы вправо-вверх.
     */
    @Test
    public void whenMoveQueenWithValidPositiveUpWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 4);
        Cell dest = new Cell(6, 1);
        board.setCurrentFigure(new Queen(source));
        board.move(source, dest);
        Figure expected = new Queen(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для королевы вправо-вниз.
     */
    @Test
    public void whenMoveQueenWithValidPositiveDownWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 4);
        Cell dest = new Cell(6, 7);
        board.setCurrentFigure(new Queen(source));
        board.move(source, dest);
        Figure expected = new Queen(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для королевы влево-вверх.
     */
    @Test
    public void whenMoveQueenWithValidNegativeUpWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 4);
        Cell dest = new Cell(0, 1);
        board.setCurrentFigure(new Queen(source));
        board.move(source, dest);
        Figure expected = new Queen(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для королевы влево-вниз.
     */
    @Test
    public void whenMoveQueenWithValidNegativeDownWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 4);
        Cell dest = new Cell(0, 7);
        board.setCurrentFigure(new Queen(source));
        board.move(source, dest);
        Figure expected = new Queen(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий невозможность хода для королевы, если это не разрешено правилами хода фигуры.
     *
     * @throws ImpossibleMoveException исключение бросается, если ход невозможен.
     */
    @Test
    public void whenMoveQueenWithInvalidWayThenGetException() throws ImpossibleMoveException {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(5, 0);
        board.setCurrentFigure(new Queen(source));
        try {
            board.move(source, dest);
        } catch (ImpossibleMoveException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }

    /**
     * Тест, проверяющий невозможность хода для королевы, если конечная ячейка расположена за пределами доски.
     *
     * @throws ImpossibleMoveException исключение бросается, если ход невозможен.
     */
    @Test
    public void whenMoveQueenWithInvalidWayOutOfBoardThenGetException() throws ImpossibleMoveException {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(8, 8);
        board.setCurrentFigure(new Queen(source));
        try {
            board.move(source, dest);
        } catch (ImpossibleMoveException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }

    /**
     * Тест, проверяющий невозможность хода для королевы, если на ее пути стоит другая фигура.
     *
     * @throws OccupiedWayException исключение бросается, если ход невозможен.
     */
    @Test
    public void whenMoveQueenWithValidOccupiedWayThenMove() throws OccupiedWayException {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(3, 0);
        Cell occupiedSource = new Cell(0, 1);
        Cell occupiedDest = new Cell(3, 1);
        board.setCurrentFigure(new Rook(occupiedSource));
        board.move(occupiedSource, occupiedDest);
        board.setCurrentFigure(new Queen(source));
        try {
            board.move(source, dest);
        } catch (OccupiedWayException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }
}