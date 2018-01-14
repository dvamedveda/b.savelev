package ru.job4j.chess;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.figures.Bishop;

public class BoardTest {
    @Test
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

    @Test
    public void whenMoveFigureWithValidPositiveUpWayThenMove() throws ImpossibleMoveException{
        Board board = new Board();
        Cell source = new Cell(3, 4);
        Cell dest = new Cell(6, 1);
        board.setCurrentFigure(new Bishop(source));
        try {
            board.move(source, dest);
        } catch (ImpossibleMoveException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }

    @Test
    public void whenMoveFigureWithValidPositiveDownWayThenMove() throws ImpossibleMoveException{
        Board board = new Board();
        Cell source = new Cell(3, 4);
        Cell dest = new Cell(6, 7);
        board.setCurrentFigure(new Bishop(source));
        try {
            board.move(source, dest);
        } catch (ImpossibleMoveException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }

    @Test
    public void whenMoveFigureWithValidNegativeUpWayThenMove() throws ImpossibleMoveException{
        Board board = new Board();
        Cell source = new Cell(3, 4);
        Cell dest = new Cell(0, 1);
        board.setCurrentFigure(new Bishop(source));
        try {
            board.move(source, dest);
        } catch (ImpossibleMoveException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }

    @Test
    public void whenMoveFigureWithValidNegativeDownWayThenMove() throws ImpossibleMoveException{
        Board board = new Board();
        Cell source = new Cell(3, 4);
        Cell dest = new Cell(0, 7);
        board.setCurrentFigure(new Bishop(source));
        try {
            board.move(source, dest);
        } catch (ImpossibleMoveException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }

    @Test
    public void whenMoveFigureWithInvalidWayThenGetException() throws ImpossibleMoveException{
        Board board = new Board();
        Cell source = new Cell(3, 4);
        Cell dest = new Cell(10, 10);
        board.setCurrentFigure(new Bishop(source));
        try {
            board.move(source, dest);
        } catch (ImpossibleMoveException ex) {
            Assert.assertNotEquals("", ex.getMessage());
        }
    }

    @Test
    public void whenMoveFigureWithValidOccupiedWayThenMove() throws OccupiedWayException{
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