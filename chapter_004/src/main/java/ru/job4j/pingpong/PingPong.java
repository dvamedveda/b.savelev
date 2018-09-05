package ru.job4j.pingpong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Приложение Пинг понга.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class PingPong extends Application {
    private static final String JOB4J = "Пинг-понг www.job4j.ru";

    /**
     * Главный поток программы
     * @param stage сцена
     */
    @Override
    public void start(Stage stage) {
        int limitX = 300;
        int limitY = 300;
        Group group = new Group();
        Rectangle rectangle = new Rectangle(50, 100, 10, 10);
        group.getChildren().add(rectangle);
        new Thread(new RectangleMove(rectangle)).start();
        stage.setScene(new Scene(group, limitX, limitY));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
    }
}