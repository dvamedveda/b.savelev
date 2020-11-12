package ru.job4j.tictactoe.game;

/**
 * Класс, реализующий координаты ячейки игрового поля.
 */
public class GamePoint implements Point {

    /**
     * Координаты ячейки по оси X.
     */
    private int x;

    /**
     * Координаты ячейки по оси Y.
     */
    private int y;

    /**
     * Создать новые координаты.
     *
     * @param x координаты ячейки по оси X.
     * @param y координаты ячейки по оси Y.
     */
    public GamePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Получить координату X из текущих координат.
     *
     * @return координата X.
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * Получить координату Y из текущий координат.
     *
     * @return координата Y.
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * Получить новый объект координат из текстового представления координат.
     *
     * @param string координаты в тексте.
     * @return новый объект координат.
     */
    public static Point toPoint(String string) {
        String[] parts = string.split(",");
        int x = Integer.parseInt(parts[0].trim());
        int y = Integer.parseInt(parts[1].trim());
        return new GamePoint(x, y);
    }
}