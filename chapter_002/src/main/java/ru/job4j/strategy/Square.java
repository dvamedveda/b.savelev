package ru.job4j.strategy;

/**
 * Класс, реализующий интерфейс Shape, для рисования квадрата.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Square implements Shape {

    /**
     * Метод рисующий квадрат.
     *
     * @return рисунок фигуры.
     */
    @Override
    public String draw() {
        String delimiter = System.lineSeparator();
        StringBuilder pic = new StringBuilder();
        pic
                .append("#########").append(delimiter)
                .append("#########").append(delimiter)
                .append("#########").append(delimiter)
                .append("#########").append(delimiter)
                .append("#########").append(delimiter);
        return pic.toString();
    }
}
