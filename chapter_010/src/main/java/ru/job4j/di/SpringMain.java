package ru.job4j.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Демонстрация инъекции зависимостей при помощи Spring DI при запуске программы.
 */
public class SpringMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ConsoleInput.class);
        context.register(Store.class);
        context.register(StartUI.class);
        context.refresh();
        StartUI ui = context.getBean(StartUI.class);
        ui.askAndAdd();
        ui.askAndAdd();
        ui.print();
    }
}