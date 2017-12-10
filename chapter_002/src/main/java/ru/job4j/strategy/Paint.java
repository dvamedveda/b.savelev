package ru.job4j.strategy;

/**
 * Класс, реализующий паттерн Strategy, для рисования фигур.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class Paint {
    /**
     * Переменная-фигура, задающая контекст.
     */
    private Shape shape;

    /**
     * Конструктор, принимающий различные фигуры, задающие контекст.
     *
     * @param shape фигура, реализующая интерфейс Shape
     */
    public Paint(Shape shape) {
        this.shape = shape;
    }

    /**
     * Метод для рисования фигуры в зависимости от контекста.
     */
    public void drawShape() {
        System.out.println(shape.draw());
    }
}