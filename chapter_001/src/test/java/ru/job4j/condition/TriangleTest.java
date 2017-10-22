package ru.job4j.condition;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.number.IsCloseTo.closeTo;

/**
 * Класс с тестами методов класса Triangle.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class TriangleTest {

    /**
     * Тест медода Triangle.distance().
     * Проверяет, что метод правильно возвращает дистанцию между заданными точками.
     */
    @Test
    public void whenDistanceTenThenDistanceTen() {
        Point left = new Point(0, 0);
        Point right = new Point(0, 10);

        Triangle triangle = new Triangle(null, null, null);
        double dst = triangle.distance(left, right);
        assertThat(dst, closeTo(10, 0.1));
    }

    /**
     * Тест метода Triangle.perimeter().
     * Проверяет правильное вычисление полупериметра треугольника (10 при длинах сторон 9, 7, 4)
     */
    @Test
    public void whenPerimeterTenThenPerimeterTen() {
        double ab = 9;
        double ac = 7;
        double bc = 4;
        Triangle triangle = new Triangle(null, null, null);
        double prmtr = triangle.perimeter(ab, ac, bc);
        assertThat(prmtr, closeTo(10, 0.1));
    }

    /**
     * Тест, опосредованно проверяющий работу метода Triangle.exist().
     * Опосредованно, потому что этот метод приватный, и доступа к нему нет.
     * Берем треугольник, который точно можно построить и проверяем, что для него площадь
     * возвращается не равной -1 (как в случае, когда треугольник построить нельзя).
     */
    @Test
    public void whenEachSideMoreThanSumTwoOthersThenTriangleExists() {
        Point a = new Point(1, 1);
        Point b = new Point(1, 5);
        Point c = new Point(5, 1);
        Triangle triangle = new Triangle(a, b, c);
        double area = triangle.area();
        assertThat(area, not(-1));
    }

    /**
     * Тест, проверяющий работу метода Triangle.area().
     * Берем треугольник, для которого известна площадь,
     * и вычисляем ее с помощью метода.
     */
    @Test
    public void whenCalculateAreaThenItCalculate() {
        Point a = new Point(1, 5);
        Point b = new Point(5, 11);
        Point c = new Point(17, 9);
        Triangle triangle = new Triangle(a, b, c);
        double area = triangle.area();
        assertThat(area, closeTo(40, 0.1));
    }
}