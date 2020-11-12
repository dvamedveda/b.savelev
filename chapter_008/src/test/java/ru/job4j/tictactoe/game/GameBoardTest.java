package ru.job4j.tictactoe.game;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * Тесты класса ru.job4j.tictactoe.game.GameBoard.
 */
public class GameBoardTest {

    /**
     * Здесь проверяется создание игрового поля.
     */
    @Test
    public void whenCreateBoardThenCreateSuccess() {
        Board board = new GameBoard(2, 2);
        Board expected = board.getBoard();
        Assert.assertThat(expected.getLength(), is(2));
        Assert.assertThat(expected.getHeight(), is(2));
        Assert.assertThat(expected.getField(new GamePoint(0, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(0, 1)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(1, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(1, 1)), is(""));
    }

    /**
     * Здесь проверяется установка фишки на игровое поле.
     */
    @Test
    public void whenDoMoveThenBoardMarksField() {
        Board board = new GameBoard(2, 2);
        board.setField(new GamePoint(0, 1), new OMark());
        board.setField(new GamePoint(1, 0), new XMark());
        Board expected = board.getBoard();
        Assert.assertThat(expected.getField(new GamePoint(0, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(0, 1)), is("O"));
        Assert.assertThat(expected.getField(new GamePoint(1, 0)), is("X"));
        Assert.assertThat(expected.getField(new GamePoint(1, 1)), is(""));
    }

    /**
     * Здесь проверяется установка фишки за пределы игрового поля.
     */
    @Test
    public void whenDoMoveOutFromBoardThenBoardNotMarksField() {
        Board board = new GameBoard(2, 2);
        boolean result = board.setField(new GamePoint(3, 3), new OMark());
        Board expected = board.getBoard();
        Assert.assertThat(expected.getField(new GamePoint(0, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(0, 1)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(1, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(1, 1)), is(""));
        Assert.assertFalse(result);
    }

    /**
     * Здесь проверяется установка фишки на уже занятую ячейку игрового поля.
     */
    @Test
    public void whenDoMoveToBusyThenBoardNotSetField() {
        Board board = new GameBoard(2, 2);
        boolean result = board.setField(new GamePoint(0, 1), new OMark());
        Board expected = board.getBoard();
        Assert.assertThat(expected.getField(new GamePoint(0, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(0, 1)), is("O"));
        Assert.assertThat(expected.getField(new GamePoint(1, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(1, 1)), is(""));
        Assert.assertTrue(result);
        result = board.setField(new GamePoint(0, 1), new XMark());
        Assert.assertThat(expected.getField(new GamePoint(0, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(0, 1)), is("O"));
        Assert.assertThat(expected.getField(new GamePoint(1, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(1, 1)), is(""));
        Assert.assertFalse(result);
    }

    /**
     * Здесь проверяется наличие на игровом поле линии, заполненной одними фишками по горизонтали.
     */
    @Test
    public void whenHorizontalLineWithOneMarkThenHaveFilled() {
        Board board = new GameBoard(3, 3);
        board.setField(new GamePoint(0, 1), new OMark());
        board.setField(new GamePoint(1, 1), new OMark());
        board.setField(new GamePoint(2, 1), new OMark());
        Board expected = board.getBoard();
        Assert.assertThat(expected.getLength(), is(3));
        Assert.assertThat(expected.getHeight(), is(3));
        Assert.assertThat(expected.getField(new GamePoint(0, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(1, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(2, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(0, 1)), is("O"));
        Assert.assertThat(expected.getField(new GamePoint(1, 1)), is("O"));
        Assert.assertThat(expected.getField(new GamePoint(2, 1)), is("O"));
        Assert.assertThat(expected.getField(new GamePoint(0, 2)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(1, 2)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(2, 2)), is(""));
        Assert.assertThat(expected.haveFilledLine("O"), is(true));
    }

    /**
     * Здесь проверяется наличие на игровом поле линии, заполненной разными фишками по горизонтали.
     */
    @Test
    public void whenHorizontalLineWithFewMarkThenNotHaveFilled() {
        Board board = new GameBoard(3, 3);
        board.setField(new GamePoint(0, 1), new OMark());
        board.setField(new GamePoint(1, 1), new XMark());
        board.setField(new GamePoint(2, 1), new OMark());
        Board expected = board.getBoard();
        Assert.assertThat(expected.getLength(), is(3));
        Assert.assertThat(expected.getHeight(), is(3));
        Assert.assertThat(expected.getField(new GamePoint(0, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(1, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(2, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(0, 1)), is("O"));
        Assert.assertThat(expected.getField(new GamePoint(1, 1)), is("X"));
        Assert.assertThat(expected.getField(new GamePoint(2, 1)), is("O"));
        Assert.assertThat(expected.getField(new GamePoint(0, 2)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(1, 2)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(2, 2)), is(""));
        Assert.assertThat(expected.haveFilledLine("O"), is(false));
    }

    /**
     * Здесь проверяется наличие на игровом поле линии, заполненной одними фишками по вертикали.
     */
    @Test
    public void whenVerticalLineWithOneMarkThenHaveFilled() {
        Board board = new GameBoard(3, 3);
        board.setField(new GamePoint(0, 0), new XMark());
        board.setField(new GamePoint(0, 1), new XMark());
        board.setField(new GamePoint(0, 2), new XMark());
        Board expected = board.getBoard();
        Assert.assertThat(expected.getLength(), is(3));
        Assert.assertThat(expected.getHeight(), is(3));
        Assert.assertThat(expected.getField(new GamePoint(0, 0)), is("X"));
        Assert.assertThat(expected.getField(new GamePoint(1, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(2, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(0, 1)), is("X"));
        Assert.assertThat(expected.getField(new GamePoint(1, 1)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(2, 1)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(0, 2)), is("X"));
        Assert.assertThat(expected.getField(new GamePoint(1, 2)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(2, 2)), is(""));
        Assert.assertThat(expected.haveFilledLine("X"), is(true));
    }

    /**
     * Здесь проверяется наличие на игровом поле линии, заполненной разными фишками по горизонтали.
     */
    @Test
    public void whenVerticalLineWithFewMarkThenNotHaveFilled() {
        Board board = new GameBoard(3, 3);
        board.setField(new GamePoint(0, 0), new XMark());
        board.setField(new GamePoint(0, 1), new OMark());
        board.setField(new GamePoint(0, 2), new XMark());
        Board expected = board.getBoard();
        Assert.assertThat(expected.getLength(), is(3));
        Assert.assertThat(expected.getHeight(), is(3));
        Assert.assertThat(expected.getField(new GamePoint(0, 0)), is("X"));
        Assert.assertThat(expected.getField(new GamePoint(1, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(2, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(0, 1)), is("O"));
        Assert.assertThat(expected.getField(new GamePoint(1, 1)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(2, 1)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(0, 2)), is("X"));
        Assert.assertThat(expected.getField(new GamePoint(1, 2)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(2, 2)), is(""));
        Assert.assertThat(expected.haveFilledLine("X"), is(false));
    }

    /**
     * Здесь проверяется наличие на игровом поле линии, заполненной одними фишками по главной диагонали.
     */
    @Test
    public void whenDiagonalLineFromTopWithOneMarkThenHaveFilled() {
        Board board = new GameBoard(3, 3);
        board.setField(new GamePoint(0, 0), new XMark());
        board.setField(new GamePoint(1, 1), new XMark());
        board.setField(new GamePoint(2, 2), new XMark());
        Board expected = board.getBoard();
        Assert.assertThat(expected.getLength(), is(3));
        Assert.assertThat(expected.getHeight(), is(3));
        Assert.assertThat(expected.getField(new GamePoint(0, 0)), is("X"));
        Assert.assertThat(expected.getField(new GamePoint(1, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(2, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(0, 1)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(1, 1)), is("X"));
        Assert.assertThat(expected.getField(new GamePoint(2, 1)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(0, 2)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(1, 2)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(2, 2)), is("X"));
        Assert.assertThat(expected.haveFilledLine("X"), is(true));
    }

    /**
     * Здесь проверяется наличие на игровом поле линии, заполненной одними фишками по вспомогательной диагонали.
     */
    @Test
    public void whenDiagonalLineFromBottomWithOneMarkThenHaveFilled() {
        Board board = new GameBoard(3, 3);
        board.setField(new GamePoint(0, 2), new XMark());
        board.setField(new GamePoint(1, 1), new XMark());
        board.setField(new GamePoint(2, 0), new XMark());
        Board expected = board.getBoard();
        Assert.assertThat(expected.getLength(), is(3));
        Assert.assertThat(expected.getHeight(), is(3));
        Assert.assertThat(expected.getField(new GamePoint(0, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(1, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(2, 0)), is("X"));
        Assert.assertThat(expected.getField(new GamePoint(0, 1)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(1, 1)), is("X"));
        Assert.assertThat(expected.getField(new GamePoint(2, 1)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(0, 2)), is("X"));
        Assert.assertThat(expected.getField(new GamePoint(1, 2)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(2, 2)), is(""));
        Assert.assertThat(expected.haveFilledLine("X"), is(true));
    }

    /**
     * Здесь проверяется наличие на игровом поле линии, заполненной разными фишками по диагонали.
     */
    @Test
    public void whenAnyDiagonalLineWithFewMarkThenNotHaveFilled() {
        Board board = new GameBoard(3, 3);
        board.setField(new GamePoint(0, 2), new XMark());
        board.setField(new GamePoint(0, 0), new XMark());
        board.setField(new GamePoint(1, 1), new OMark());
        board.setField(new GamePoint(2, 0), new XMark());
        board.setField(new GamePoint(2, 2), new XMark());
        Board expected = board.getBoard();
        Assert.assertThat(expected.getLength(), is(3));
        Assert.assertThat(expected.getHeight(), is(3));
        Assert.assertThat(expected.getField(new GamePoint(0, 0)), is("X"));
        Assert.assertThat(expected.getField(new GamePoint(1, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(2, 0)), is("X"));
        Assert.assertThat(expected.getField(new GamePoint(0, 1)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(1, 1)), is("O"));
        Assert.assertThat(expected.getField(new GamePoint(2, 1)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(0, 2)), is("X"));
        Assert.assertThat(expected.getField(new GamePoint(1, 2)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(2, 2)), is("X"));
        Assert.assertThat(expected.haveFilledLine("X"), is(false));
    }

    /**
     * Здесь проверяется отсутствие заполненных линий на пустом поле.
     */
    @Test
    public void whenEmptyBoardThenNotHaveFilled() {
        Board board = new GameBoard(3, 3);
        Board expected = board.getBoard();
        Assert.assertThat(expected.getLength(), is(3));
        Assert.assertThat(expected.getHeight(), is(3));
        Assert.assertThat(expected.getField(new GamePoint(0, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(1, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(2, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(0, 1)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(1, 1)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(2, 1)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(0, 2)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(1, 2)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(2, 2)), is(""));
        Assert.assertThat(expected.haveFilledLine("X"), is(false));
        Assert.assertThat(expected.haveFilledLine("O"), is(false));
    }

    /**
     * Здесь проверяется заполненность игрового поля на полном поле.
     */
    @Test
    public void whenFullBoardThenIsNoPlace() {
        Board board = new GameBoard(3, 3);
        board.setField(new GamePoint(0, 0), new OMark());
        board.setField(new GamePoint(1, 0), new OMark());
        board.setField(new GamePoint(2, 0), new XMark());
        board.setField(new GamePoint(0, 1), new XMark());
        board.setField(new GamePoint(1, 1), new XMark());
        board.setField(new GamePoint(2, 1), new OMark());
        board.setField(new GamePoint(0, 2), new OMark());
        board.setField(new GamePoint(1, 2), new XMark());
        board.setField(new GamePoint(2, 2), new XMark());
        Board expected = board.getBoard();
        Assert.assertThat(expected.getLength(), is(3));
        Assert.assertThat(expected.getHeight(), is(3));
        Assert.assertThat(expected.getField(new GamePoint(0, 0)), is("O"));
        Assert.assertThat(expected.getField(new GamePoint(1, 0)), is("O"));
        Assert.assertThat(expected.getField(new GamePoint(2, 0)), is("X"));
        Assert.assertThat(expected.getField(new GamePoint(0, 1)), is("X"));
        Assert.assertThat(expected.getField(new GamePoint(1, 1)), is("X"));
        Assert.assertThat(expected.getField(new GamePoint(2, 1)), is("O"));
        Assert.assertThat(expected.getField(new GamePoint(0, 2)), is("O"));
        Assert.assertThat(expected.getField(new GamePoint(1, 2)), is("X"));
        Assert.assertThat(expected.getField(new GamePoint(2, 2)), is("X"));
        Assert.assertThat(expected.isNoPlace(), is(true));
    }

    /**
     * Здесь проверяется заполненность игрового поля на неполном поле.
     */
    @Test
    public void whenNotFullBoardThenHavePlace() {
        Board board = new GameBoard(3, 3);
        board.setField(new GamePoint(0, 0), new OMark());
        Board expected = board.getBoard();
        Assert.assertThat(expected.getLength(), is(3));
        Assert.assertThat(expected.getHeight(), is(3));
        Assert.assertThat(expected.getField(new GamePoint(0, 0)), is("O"));
        Assert.assertThat(expected.getField(new GamePoint(1, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(2, 0)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(0, 1)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(1, 1)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(2, 1)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(0, 2)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(1, 2)), is(""));
        Assert.assertThat(expected.getField(new GamePoint(2, 2)), is(""));
        Assert.assertThat(expected.isNoPlace(), is(false));
    }
}