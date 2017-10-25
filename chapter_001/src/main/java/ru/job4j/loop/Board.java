package ru.job4j.loop;

/**
 * Класс Board, который содержит метод для рисования шахматной доски.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Board {

    /**
     * Метод, рисующий шахматную доску с заданной шириной и высотой.
     * Всегда начинает рисовать с черной клетки.
     *
     * @param width  ширина доски, int.
     * @param height высота доски, int.
     * @return шахматная доска, String.
     */
    public String paint(int width, int height) {
        StringBuilder buffer = new StringBuilder();
        boolean black = true;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (black) {
                    buffer.append("X");
                    black = false;
                } else {
                    buffer.append(" ");
                    black = true;
                }
            }
            buffer.append(System.getProperty("line.separator"));
        }

        return buffer.toString();
    }
}