package ru.job4j.condition;

/**
 * Класс Point, содержащий класс, конструктор и метод для работы с точнами.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Point {
    /**
     * Поля класса, обозначающие его координаты.
     */
    private int x, y;

    /**
     * Конструктор класса Point.
     * @param x координата x.
     * @param y координата y.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Возращает координату X точки.
     *
     * @return int x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Возвращает координату Y точки.
     *
     * @return int y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Метод, определящий, находится ли точка на заданной функции.
     *
     * @param a int один из параметров функции.
     * @param b int второй из параметров функции.
     * @return boolean нахождение точки на функции
     */
    public boolean is(int a, int b) {
        return (this.y == a * this.x + b);
    }
}