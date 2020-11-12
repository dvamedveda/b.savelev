package ru.job4j.tictactoe.game;

import java.util.Arrays;

/**
 * Класс, реализующий игровое поле.
 */
public class GameBoard implements Board {

    /**
     * Массив для хранения значений ячеек поля.
     */
    private Mark[][] board;

    /**
     * Конструктор, в котором задаются размеры игрового поля.
     *
     * @param length длина поля.
     * @param height высота поля.
     */
    public GameBoard(int length, int height) {
        this.board = new Mark[length][height];
    }

    /**
     * Проверка, есть ли на поле полностью заполненная одной фишкой линия.
     * Проверяются горизонтальные, вертикальные и диагонали.
     *
     * @param mark фишка
     * @return есть заполненная линия или нет.
     */
    @Override
    public boolean haveFilledLine(String mark) {
        boolean result = false;
        if (checkVertical(mark) || (checkHorizontal(mark) || checkDiagonal(mark))) {
            result = true;
        }
        return result;
    }

    /**
     * Проверка, заполнено ли поле.
     *
     * @return поле заполнено или нет.
     */
    @Override
    public boolean isNoPlace() {
        boolean result = true;
        for (int x = 0; x < this.board.length; x++) {
            for (int y = 0; y < this.board[0].length; y++) {
                if (this.board[x][y] == null) {
                    result = false;
                }
            }
        }
        return result;
    }

    /**
     * Установить фишку на определенную позицию поля.
     *
     * @param point координаты ячейки.
     * @param mark  фишка, которая устанавливается.
     * @return фишка была установлена или нет.
     */
    @Override
    public boolean setField(Point point, Mark mark) {
        int x = point.getX();
        int y = point.getY();
        boolean result = false;
        if (x <= this.board.length && y <= this.board[0].length) {
            if (this.board[x][y] == null) {
                this.board[x][y] = mark;
                result = true;
            }
        }
        return result;
    }

    /**
     * Получить содержимое ячейки поля по определенным координатами.
     *
     * @param point координаты ячейки.
     * @return содержимое ячейки.
     */
    @Override
    public String getField(Point point) {
        int x = point.getX();
        int y = point.getY();
        String result = "";
        if (x <= this.board.length && y <= this.board[0].length) {
            if (this.board[x][y] != null) {
                result = this.board[x][y].getType();
            }
        }
        return result;
    }

    /**
     * Получить копию игрового поля.
     *
     * @return неизменяемая копия игрового поля.
     */
    @Override
    public Board getBoard() {
        return this;
    }

    /**
     * Получить длину поля, по оси X.
     *
     * @return длина поля.
     */
    @Override
    public int getLength() {
        return this.board.length;
    }

    /**
     * Получить высоту поля, по оси Y.
     *
     * @return высота поля.
     */
    @Override
    public int getHeight() {
        return this.board[0].length;
    }

    /**
     * Вспомогательный метод, для проверки, есть ли заполненные
     * определенной фишкой линии по вертикали.
     *
     * @param mark фишка.
     * @return есть заполенная фишкой линия или нет.
     */
    private boolean checkVertical(String mark) {
        boolean result = false;
        for (int x = 0; x < this.board.length; x++) {
            if (Arrays.stream(this.board[x]).allMatch(s -> s != null && s.getType().equals(mark))) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Вспомогательный метод, для проверки, есть ли заполненные
     * определенной фишкой линии по горизонтали.
     *
     * @param mark фишка.
     * @return есть заполенная фишкой линия или нет.
     */
    private boolean checkHorizontal(String mark) {
        boolean result = false;
        for (int y = 0; y < this.board[0].length; y++) {
            Mark[] line = new Mark[this.board.length];
            for (int x = 0; x < this.board.length; x++) {
                line[x] = this.board[x][y];
            }
            if (Arrays.stream(line).allMatch(s -> s != null && s.getType().equals(mark))) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Вспомогательный метод, для проверки, есть ли заполненные
     * определенной фишкой линии по диагонали.
     *
     * @param mark фишка.
     * @return есть заполенная фишкой линия или нет.
     */
    private boolean checkDiagonal(String mark) {
        boolean result = false;
        Mark[] line = new Mark[this.board.length];
        for (int x = 0; x < this.board.length; x++) {
            for (int y = 0; y < this.board[0].length; y++) {
                if (x == y) {
                    line[x] = this.board[x][y];
                }
            }
        }
        if (Arrays.stream(line).allMatch(s -> s != null && s.getType().equals(mark))) {
            result = true;
        }
        if (!result) {
            line = new Mark[this.board.length];
            for (int y = this.board[0].length - 1; y >= 0; y--) {
                line[y] = this.board[y][this.board.length - 1 - y];
            }
            if (Arrays.stream(line).allMatch(s -> s != null && s.getType().equals(mark))) {
                result = true;
            }
        }
        return result;
    }
}