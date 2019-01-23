package ru.job4j.threads.bomber;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Creature {
    ReentrantLock[][] board;
    Cell position;

    public boolean move(Cell source, Cell dest) throws InterruptedException {
        boolean result = true;
        if (board[dest.getX()][dest.getY()].tryLock(500, TimeUnit.MILLISECONDS)) {
            board[source.getX()][source.getY()].unlock();
            position.setX(dest.getX());
            position.setY(dest.getY());
        } else {
            result = false;
        }
        return result;
    }

    public Cell getPosition() {
        return position;
    }

    public Cell next() {
        int x = position.getX();
        int y = position.getY();
        Cell result;
        if (x + 1 < board.length - 1 && !board[x + 1][y].isLocked()) {
            result = new Cell(x + 1, y);
        } else if (x - 1 >= 0 && !board[x - 1][y].isLocked()) {
            result = new Cell(x - 1, y);
        } else if (y + 1 < board[x].length - 1 && !board[x][y + 1].isLocked()) {
            result = new Cell(x, y + 1);
        } else if (y - 1 >= 0 && !board[x][y - 1].isLocked()) {
            result = new Cell(x, y - 1);
        } else {
            result = new Cell(x, y);
        }
        return result;
    }
}
