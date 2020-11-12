package ru.job4j.tictactoe.game;

import ru.job4j.tictactoe.io.Input;
import ru.job4j.tictactoe.io.Printer;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, реализующий правила игры.
 */
public class GameRules implements Rules {

    /**
     * Игровое поле.
     */
    private Board board;

    /**
     * Список игроков.
     */
    private List<Player> players;

    /**
     * Текущий игрок, от которого ожидается ход.
     */
    private int currentPlayer;

    /**
     * Конструктор правил игры.
     *
     * @param board игровое поле.
     */
    public GameRules(Board board) {
        this.board = board;
        this.players = new ArrayList<>();
        this.currentPlayer = 0;
    }

    /**
     * Проверить, закончена ли игра с выигрышем.
     *
     * @return победил кто-то из игроков или нет.
     */
    @Override
    public boolean gameOver() {
        boolean result = false;
        for (Player player : this.players) {
            if (this.board.haveFilledLine(player.getMark().getType())) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Проверить, закончена ли игра вничью:
     * никто не выиграл, поле заполнено.
     *
     * @return ничья или нет.
     */
    @Override
    public boolean drawGame() {
        return !gameOver() && this.board.isNoPlace();
    }

    /**
     * Доступен ли ход по координатам.
     *
     * @param point координаты.
     * @return ход доступен или нет.
     */
    @Override
    public boolean moveAvailable(Point point) {
        boolean result = false;
        if (point.getX() < this.board.getLength() && point.getY() < this.board.getHeight()) {
            if (this.board.getField(point).equals("")) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Получить текущего игрока, от которого ожидается ход.
     *
     * @return текущий игрок.
     */
    @Override
    public Player currentPlayer() {
        return players.get(currentPlayer);
    }

    /**
     * Зарегистрировать игрока в правилах.
     *
     * @param player новый игрок.
     */
    @Override
    public void registerPlayer(Player player) {
        if (!this.players.contains(player)) {
            this.players.add(player);
        } else {
            System.out.println("This player already registered!");
        }
    }

    /**
     * Зафиксировать ход от игрока.
     */
    @Override
    public void moveDone() {
        currentPlayer++;
        if (currentPlayer >= players.size()) {
            currentPlayer = currentPlayer - players.size();
        }
    }

    /**
     * Запустить игру.
     * @param input объект для ввода данных от игрока.
     * @param printer объект для вывода данных игроку.
     */
    public void startGame(Input input, Printer printer) {
        if (this.players.size() < 2) {
            printer.notEnoughPlayers();
            printer.printBye();
            return;
        }
        Player lastPlayer = null;
        Point currentMove;
        printer.printBoard(board);
        while (!this.gameOver() && !this.drawGame()) {
            Player currentPlayer = this.currentPlayer();
            lastPlayer = currentPlayer;
            String answer = input.askMove(currentPlayer);
            if (answer.equals("exit")) {
                break;
            } else {
                currentMove = GamePoint.toPoint(answer);
            }
            currentPlayer.doMove(currentMove);
            printer.printBoard(board);
        }
        if (this.gameOver()) {
            printer.winGame(lastPlayer.getMark().getType());
        } else if (this.drawGame()) {
            printer.drawGame(lastPlayer.getMark().getType());
        }
        printer.printBye();
    }
}