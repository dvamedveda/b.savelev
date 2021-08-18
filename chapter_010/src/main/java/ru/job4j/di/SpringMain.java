package ru.job4j.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Демонстрация инъекции зависимостей при помощи Spring DI при запуске программы.
 */
public class SpringMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ru.job4j.di");
        context.refresh();
        StartUI ui = context.getBean(StartUI.class);
        ui.askAndAdd();
        ui.askAndAdd();
        ui.print();
        StartUI anotherUi = context.getBean(StartUI.class);
        anotherUi.print();
    }
}