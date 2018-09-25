package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * Двигающийся квадратик для приложения Пинг понг.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class RectangleMove implements Runnable {
    /**
     * Наш квадратик
     */
    private final Rectangle rectangle;

    /**
     * Конструктор двигающегося квадратика
     * @param rectangle квадратик
     */
    public RectangleMove(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * Задача, выполняемая в потоке.
     * Квадратик перемещается вправо или влево, пока не достигнет края окна.
     */
    public void run() {
        boolean right = true;
        while (!Thread.interrupted()) {
            double x = this.rectangle.getX();
            if (right) {
                this.rectangle.setX(x + 1);
                if (x == 300) {
                    right = false;
                }
            } else {
                this.rectangle.setX(x - 1);
                if (x == 0) {
                    right = true;
                }
            }
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
