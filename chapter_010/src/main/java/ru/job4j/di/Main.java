package ru.job4j.di;

/**
 * Демонстрация инъекции зависимостей при запуске программы.
 */
public class Main {

    public static void main(String[] args) {
        Context context = new Context();
        context.register(Store.class);
        context.register(ConsoleInput.class);
        context.register(StartUI.class);
        StartUI ui = context.get(StartUI.class);
        ui.askAndAdd();
        ui.askAndAdd();
        ui.print();
    }
}