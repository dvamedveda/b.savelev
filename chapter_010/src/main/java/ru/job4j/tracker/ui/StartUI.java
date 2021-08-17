package ru.job4j.tracker.ui;

import ru.job4j.tracker.Context;
import ru.job4j.tracker.tracker.Tracker;

/**
 * Класс, содержающий логику взаимодействия с пользователем.
 */
public class StartUI {

    /**
     * Объект для запроса ввода пользователя.
     */
    private Input ui;

    /**
     * Объект, для управления заявками.
     */
    private Tracker tracker;

    /**
     * Конструктор класса StartUI.
     */
    public StartUI(Tracker tracker, Input input) {
        this.tracker = tracker;
        this.ui = input;
    }

    /**
     * Основной цикл работы программы.
     */
    public void startWork() {
        boolean exit;
        MenuTracker menu = new MenuTracker(this.ui, this.tracker);
        menu.fillActions();

        do {
            menu.showMenu();
            int key = this.ui.ask("Введите номер пункта меню для продолжения...", menu.getOptions());
            exit = key == 0;
            menu.select(key);
        } while (!exit);
    }

    /**
     * Точка запуска программы.
     * Это же очевидно.
     *
     * @param args параметры запуска.
     */
    public static void main(String[] args) {
        Context context = new Context();
        context.register(ConsoleInput.class);
        context.register(ValidateInput.class);
        context.register(Tracker.class);
        context.register(StartUI.class);
        StartUI startUI = context.get(StartUI.class);
        startUI.startWork();
    }
}
