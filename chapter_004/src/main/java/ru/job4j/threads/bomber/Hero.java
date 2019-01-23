package ru.job4j.threads.bomber;

import java.util.concurrent.locks.ReentrantLock;

public class Hero extends Creature implements Runnable {

    public Hero(ReentrantLock[][] board, Cell position){
        this.board = board;
        this.position = position;
    }

    public void run() {
        int x = position.getX();
        int y = position.getY();
        board[x][y].lock();
        while (true) {
            Cell nextCell = next();
            try {
                if (nextCell.equals(getPosition())) {
                    System.out.println("Can't move!");
                } else {
                    boolean moveSuccess = move(getPosition(), nextCell);
                    if (moveSuccess) {
                        System.out.println(String.format("Going to %d %d", nextCell.getX(), nextCell.getY()));
                    } else {
                        System.out.println("Next cell locked! Going to another way");
                    }
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
