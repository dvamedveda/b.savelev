package ru.job4j.threads.bomber;

import java.util.concurrent.locks.ReentrantLock;

public class Game {
    private final ReentrantLock[][] board = new ReentrantLock[11][11];
    private final Hero hero;
    private final Cell heroPosition = new Cell(0, 0);

    public Game() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        this.hero = new Hero(board, heroPosition);
    }

    public void start() {
        Thread heroMove = new Thread(hero);
        heroMove.start();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
