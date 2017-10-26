package ru.job4j.loop;

/**
 * Класс Paint, который содержит метод для рисования пирамиды.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Paint {

    /**
     * Метод для рисования пирамиды заданной высоты.
     *
     * @param height высота пирамиды, int.
     * @return пирамида в псевдографике, String.
     */
    public String pyramid(int height) {
        StringBuilder buffer = new StringBuilder();
        String line = System.lineSeparator();
        int width = (height * 2);
        for (int row = 1; row < height + 1; row++) {
            for (int col = 1; col < width; col++) {
                if (col <= height - row | col >= height + row) {
                    buffer.append(" ");
                } else {
                    buffer.append("^");
                }
            }
            buffer.append(line);
        }
        return buffer.toString();
    }
}