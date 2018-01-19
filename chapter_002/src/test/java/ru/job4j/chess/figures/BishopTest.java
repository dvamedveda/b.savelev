package ru.job4j.chess.figures;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.chess.Board;
import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;

/**
 * Класс, содержащий тесты для класса Bishop(Слон).
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class BishopTest {

    /**
     * Тест, проверяющий возможность хода для слона вправо-вверх.
     */
    @Test
    public void whenMoveBishopWithValidPositiveUpWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 4);
        Cell dest = new Cell(6, 1);
        board.setCurrentFigure(new Bishop(source));
        board.move(source, dest);
        Figure expected = new Bishop(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для слона вправо-вниз.
     */
    @Test
    public void whenMoveBishopWithValidPositiveDownWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 4);
        Cell dest = new Cell(6, 7);
        board.setCurrentFigure(new Bishop(source));
        board.move(source, dest);
        Figure expected = new Bishop(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для слона влево-вверх.
     */
    @Test
    public void whenMoveBishopWithValidNegativeUpWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 4);
        Cell dest = new Cell(0, 1);
        board.setCurrentFigure(new Bishop(source));
        board.move(source, dest);
        Figure expected = new Bishop(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для слона влево-вниз.
     */
    @Test
    public void whenMoveBishopWithValidNegativeDownWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 4);
        Cell dest = new Cell(0, 7);
        board.setCurrentFigure(new Bishop(source));
        board.move(source, dest);
        Figure expected = new Bishop(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий невозможность хода для слона, если это не разрешено правилами хода фигуры.
     *
     * @throws ImpossibleMoveException исключение бросается, если ход невозможен.
     */
    @Test
    public void whenMoveBishopWithInvalidWayThenGetException() throws ImpossibleMoveException {
        Board board = new Board();
        Cell source = new Cell(3, 4);
        Cell dest = new Cell(4, 1);
        board.setCurrentFigure(new Bishop(source));
        try {
            board.move(source, dest);
        } catch (ImpossibleMoveException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }

    /**
     * Тест, проверяющий невозможность хода для слона, если конечная ячейка расположена за пределами доски.
     *
     * @throws ImpossibleMoveException исключение бросается, если ход невозможен.
     */
    @Test
    public void whenMoveBishopWithInvalidWayOutOfBoardThenGetException() throws ImpossibleMoveException {
        Board board = new Board();
        Cell source = new Cell(3, 4);
        Cell dest = new Cell(8, 8);
        board.setCurrentFigure(new Bishop(source));
        try {
            board.move(source, dest);
        } catch (ImpossibleMoveException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }

    /**
     * Тест, проверяющий невозможность хода для слона, если на его пути стоит другая фигура.
     *
     * @throws OccupiedWayException исключение бросается, если ход невозможен.
     */
    @Test
    public void whenMoveBishopWithValidOccupiedWayThenMove() throws OccupiedWayException {
        Board board = new Board();
        Cell source = new Cell(3, 4);
        Cell dest = new Cell(6, 7);
        Cell occupiedSource = new Cell(6, 5);
        Cell occupiedDest = new Cell(5, 6);
        board.setCurrentFigure(new Bishop(occupiedSource));
        board.move(occupiedSource, occupiedDest);
        board.setCurrentFigure(new Bishop(source));
        try {
            board.move(source, dest);
        } catch (OccupiedWayException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }
}