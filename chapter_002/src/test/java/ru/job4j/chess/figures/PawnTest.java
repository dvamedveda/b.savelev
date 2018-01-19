package ru.job4j.chess.figures;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.chess.Board;
import ru.job4j.chess.Cell;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;

/**
 * Класс, содержащий тесты для класса Pawn(Пешка).
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class PawnTest {

    /**
     * Тест, проверяющий возможность хода для пешки на две клетки вначале.
     */
    @Test
    public void whenMovePawnWithValidWayToTwoCellThenMove() {
        Board board = new Board();
        Cell source = new Cell(6, 6);
        Cell dest = new Cell(6, 4);
        board.setCurrentFigure(new Pawn(source));
        board.move(source, dest);
        Figure expected = new Pawn(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий возможность хода для пешки вперед.
     */
    @Test
    public void whenMovePawnWithValidWayThenMove() {
        Board board = new Board();
        Cell source = new Cell(6, 6);
        Cell dest = new Cell(6, 5);
        board.setCurrentFigure(new Pawn(source));
        board.move(source, dest);
        Figure expected = new Pawn(dest);
        Figure result = board.getFields()[dest.getX()][dest.getY()];
        Assert.assertTrue(expected.equals(result));
    }

    /**
     * Тест, проверяющий невозможность хода для пешки, если это не разрешено правилами хода фигуры.
     *
     * @throws ImpossibleMoveException исключение бросается, если ход невозможен.
     */
    @Test
    public void whenMovePawnWithInvalidWayThenGetException() throws ImpossibleMoveException {
        Board board = new Board();
        Cell source = new Cell(6, 6);
        Cell dest = new Cell(6, 3);
        board.setCurrentFigure(new Pawn(source));
        try {
            board.move(source, dest);
        } catch (ImpossibleMoveException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }

    /**
     * Тест, проверяющий невозможность хода для пешки, если конечная ячейка расположена за пределами доски.
     *
     * @throws ImpossibleMoveException исключение бросается, если ход невозможен.
     */
    @Test
    public void whenMovePawnWithInvalidWayOutOfBoardThenGetException() throws ImpossibleMoveException {
        Board board = new Board();
        Cell source = new Cell(6, 6);
        Cell dest = new Cell(6, -1);
        board.setCurrentFigure(new Pawn(source));
        try {
            board.move(source, dest);
        } catch (ImpossibleMoveException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }

    /**
     * Тест, проверяющий невозможность хода для пешки, если на ее пути стоит другая фигура.
     *
     * @throws ImpossibleMoveException исключение бросается, если ход невозможен.
     */
    @Test
    public void whenMovePawnWithValidOccupiedWayThenMove() throws OccupiedWayException {
        Board board = new Board();
        Cell source = new Cell(6, 6);
        Cell dest = new Cell(6, 5);
        Cell occupiedSource = new Cell(7, 4);
        Cell occupiedDest = new Cell(6, 5);
        board.setCurrentFigure(new Bishop(occupiedSource));
        board.move(occupiedSource, occupiedDest);
        board.setCurrentFigure(new Pawn(source));
        try {
            board.move(source, dest);
        } catch (OccupiedWayException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }
}