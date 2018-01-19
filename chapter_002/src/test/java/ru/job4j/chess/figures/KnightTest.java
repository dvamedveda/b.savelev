package ru.job4j.chess.figures;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.chess.Board;
import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;

/**
 * Класс, содержащий тесты для класса Knight(Конь).
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class KnightTest {

    /**
     * Тест, проверяющий возможность хода для коня вправо, затем вверх.
     */
    @Test
    public void whenMoveKnightWithValidRightAndUpWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(5, 2);
        board.setCurrentFigure(new Knight(source));
        board.move(source, dest);
        Figure expected = new Knight(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для коня вправо, затем вниз.
     */
    @Test
    public void whenMoveKnightWithValidRightAndDownWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(5, 4);
        board.setCurrentFigure(new Knight(source));
        board.move(source, dest);
        Figure expected = new Knight(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для коня вниз, затем вправо.
     */
    @Test
    public void whenMoveKnightWithValidDownAndRightWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(4, 5);
        board.setCurrentFigure(new Knight(source));
        board.move(source, dest);
        Figure expected = new Knight(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для коня вниз, затем влево.
     */
    @Test
    public void whenMoveKnightWithValidDownAndLeftWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(2, 5);
        board.setCurrentFigure(new Knight(source));
        board.move(source, dest);
        Figure expected = new Knight(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для коня влево, затем вниз.
     */
    @Test
    public void whenMoveKnightWithValidLeftAndDownWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(1, 4);
        board.setCurrentFigure(new Knight(source));
        board.move(source, dest);
        Figure expected = new Knight(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для коня влево, затем вверх.
     */
    @Test
    public void whenMoveKnightWithValidLeftAndUpWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(1, 2);
        board.setCurrentFigure(new Knight(source));
        board.move(source, dest);
        Figure expected = new Knight(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для коня вверх, затем влево.
     */
    @Test
    public void whenMoveKnightWithValidUpAndLeftWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(2, 1);
        board.setCurrentFigure(new Knight(source));
        board.move(source, dest);
        Figure expected = new Knight(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для коня вверх, затем вправо.
     */
    @Test
    public void whenMoveKnightWithValidUpAndRightWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(4, 1);
        board.setCurrentFigure(new Knight(source));
        board.move(source, dest);
        Figure expected = new Knight(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий невозможность хода для коня, если это не разрешено правилами хода фигуры.
     *
     * @throws ImpossibleMoveException исключение бросается, если ход невозможен.
     */
    @Test
    public void whenMoveKnightWithInvalidWayThenGetException() throws ImpossibleMoveException {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(5, 5);
        board.setCurrentFigure(new Knight(source));
        try {
            board.move(source, dest);
        } catch (ImpossibleMoveException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }

    /**
     * Тест, проверяющий невозможность хода фигурой за пределы доски.
     *
     * @throws ImpossibleMoveException исключение бросается, если ход невозможен.
     */
    @Test
    public void whenMoveKnightWithInvalidWayOutOfBoardThenGetException() throws ImpossibleMoveException {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(10, 10);
        board.setCurrentFigure(new Knight(source));
        try {
            board.move(source, dest);
        } catch (ImpossibleMoveException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }

    /**
     * Тест, проверяющий возможность хода для коня, если на его пути стоит другая фигура.
     *
     * @throws OccupiedWayException исключение бросается, если ход невозможен.
     */
    @Test
    public void whenMoveKnightWithValidOccupiedWayThenMove() throws OccupiedWayException {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(2, 1);
        Cell occupiedFirstSource = new Cell(4, 1);
        Cell occupiedFirstDest = new Cell(3, 2);
        Cell occupiedSecondSource = new Cell(3, 1);
        Cell occupiedSecondDest = new Cell(2, 2);
        board.setCurrentFigure(new Bishop(occupiedFirstSource));
        board.move(occupiedFirstSource, occupiedFirstDest);
        board.setCurrentFigure(new Bishop(occupiedSecondSource));
        board.move(occupiedSecondSource, occupiedSecondDest);
        board.setCurrentFigure(new Knight(source));
        try {
            board.move(source, dest);
        } catch (OccupiedWayException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }

    /**
     * Тест, проверяющий возможность хода для коня, если на его пути стоит другая фигура.
     *
     * @throws ImpossibleMoveException исключение бросается, если ход невозможен.
     */
    @Test
    public void whenMoveKnightWithInvalidOccupiedWayThenGetException() throws OccupiedWayException {
        Board board = new Board();
        Cell source = new Cell(3, 3);
        Cell dest = new Cell(2, 1);
        Cell occupiedFirstSource = new Cell(4, 1);
        Cell occupiedFirstDest = new Cell(3, 2);
        Cell occupiedSecondSource = new Cell(3, 0);
        Cell occupiedSecondDest = new Cell(2, 1);
        board.setCurrentFigure(new Bishop(occupiedFirstSource));
        board.move(occupiedFirstSource, occupiedFirstDest);
        board.setCurrentFigure(new Bishop(occupiedSecondSource));
        board.move(occupiedSecondSource, occupiedSecondDest);
        board.setCurrentFigure(new Knight(source));
        try {
            board.move(source, dest);
        } catch (OccupiedWayException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }
}