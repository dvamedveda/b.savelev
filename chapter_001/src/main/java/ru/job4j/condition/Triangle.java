package ru.job4j.condition;

/**
 * Класс для работы с треугольниками.
 * Содержит методы:
 * вычисления длины сторон между точками,
 * вычисления полупериметра треугольника,
 * вычисления площади,
 * проверки возможности построения треугольника.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Triangle {

    /**
     * Вершина A треугольника.
     */
    private Point a;

    /**
     * Вершина B треугольника.
     */
    private Point b;

    /**
     * Вершина C треугольника.
     */
    private Point c;

    /**
     * Конструктор класса Triangle.
     * Принимает три точки-вершины треугольника, созданные при помощи класса Point.
     *
     * @param a первая вершина.
     * @param b вторая вершина.
     * @param c третья вершина.
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Метод вычисляющий дистанцию (длину стороны треугольника) между двумя точками на плоскости.
     *
     * @param left  первая точка.
     * @param right вторая точка.
     * @return дистанция между точками.
     */
    public double distance(Point left, Point right) {
        return Math.sqrt(Math.pow((left.getX() - right.getX()), 2) + Math.pow((left.getY() - right.getY()), 2));
    }

    /**
     * Метод вычисляет полупериметр треугольника из трех переданных длин сторон.
     *
     * @param ab первая сторона треугольника.
     * @param ac вторая сторона треугольника.
     * @param bc третья сторона треугольника.
     * @return полупериметр треугольника.
     */
    public double perimeter(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2;
    }

    /**
     * Метод для вычисления площади треугольника по формуле Герона.
     *
     * @return площадь треугольника или -1, если треугольник нельзя построить.
     */
    public double area() {
        double result = -1;
        double ab = this.distance(this.a, this.b);
        double ac = this.distance(this.a, this.c);
        double bc = this.distance(this.b, this.c);
        double prmtr = this.perimeter(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            result = Math.sqrt(prmtr * (prmtr - ab) * (prmtr - ac) * (prmtr - bc));
        }
        return result;
    }

    /**
     * Метод, проверяющий, возможно ли построить треугольник по заданным длинам сторон.
     *
     * @param ab первая сторона треугольника.
     * @param ac вторая сторона треугольника.
     * @param bc третья сторона треугольника.
     * @return true, если треугольник возможно построить, иначе - false.
     */
    private boolean exist(double ab, double ac, double bc) {
        return ab < ac + bc && ac < ab + bc && bc < ab + ac;
    }
}