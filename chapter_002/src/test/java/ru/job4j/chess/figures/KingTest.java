package ru.job4j.chess.figures;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.chess.Board;
import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;

/**
 * Класс, содержащий тесты для класса King(Король).
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class KingTest {
    /**
     * Тест, проверяющий возможность хода для короля вверх.
     */
    @Test
    public void whenMoveKingWithValidUpWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(3, 2);
        board.setCurrentFigure(new King(source));
        board.move(source, dest);
        Figure expected = new King(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для короля вправо.
     */
    @Test
    public void whenMoveKingWithValidRightWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(4, 3);
        board.setCurrentFigure(new King(source));
        board.move(source, dest);
        Figure expected = new King(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для короля вниз.
     */
    @Test
    public void whenMoveKingWithValidDownWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(3, 4);
        board.setCurrentFigure(new King(source));
        board.move(source, dest);
        Figure expected = new King(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для короля влево.
     */
    @Test
    public void whenMoveKingWithValidLeftWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(2, 3);
        board.setCurrentFigure(new King(source));
        board.move(source, dest);
        Figure expected = new King(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для короля вправо-вверх.
     */
    @Test
    public void whenMoveKingWithValidPositiveUpWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(4, 2);
        board.setCurrentFigure(new King(source));
        board.move(source, dest);
        Figure expected = new King(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для короля вправо-вниз.
     */
    @Test
    public void whenMoveKingWithValidPositiveDownWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(4, 4);
        board.setCurrentFigure(new King(source));
        board.move(source, dest);
        Figure expected = new King(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для короля влево-вверх.
     */
    @Test
    public void whenMoveKingWithValidNegativeUpWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(2, 2);
        board.setCurrentFigure(new King(source));
        board.move(source, dest);
        Figure expected = new King(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для короля влево-вниз.
     */
    @Test
    public void whenMoveKingWithValidNegativeDownWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(4, 2);
        board.setCurrentFigure(new King(source));
        board.move(source, dest);
        Figure expected = new King(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий невозможность хода для короля, если это не разрешено правилами хода фигуры.
     *
     * @throws ImpossibleMoveException исключение бросается, если ход невозможен.
     */
    @Test
    public void whenMoveKingWithInvalidWayThenGetException() throws ImpossibleMoveException {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(5, 5);
        board.setCurrentFigure(new King(source));
        try {
            board.move(source, dest);
        } catch (ImpossibleMoveException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }

    /**
     * Тест, проверяющий невозможность хода для короля, если конечная ячейка расположена за пределами доски.
     *
     * @throws ImpossibleMoveException исключение бросается, если ход невозможен.
     */
    @Test
    public void whenMoveKingWithInvalidWayOutOfBoardThenGetException() throws ImpossibleMoveException {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(9, 9);
        board.setCurrentFigure(new King(source));
        try {
            board.move(source, dest);
        } catch (ImpossibleMoveException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }

    /**
     * Тест, проверяющий невозможность хода для Короля, если на его пути стоит другая фигура.
     *
     * @throws OccupiedWayException исключение бросается, если ход невозможен.
     */
    @Test
    public void whenMoveKingWithValidOccupiedWayThenGetException() throws OccupiedWayException {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(4, 4);
        Cell occupiedSource = new Cell(4, 5);
        Cell occupiedDest = new Cell(4, 4);
        board.setCurrentFigure(new Rook(occupiedSource));
        board.move(occupiedSource, occupiedDest);
        board.setCurrentFigure(new King(source));
        try {
            board.move(source, dest);
        } catch (OccupiedWayException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }
}