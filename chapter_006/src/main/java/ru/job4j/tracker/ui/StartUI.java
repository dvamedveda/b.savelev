package ru.job4j.tracker.ui;

import ru.job4j.tracker.tracker.SqlTracker;

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
    private SqlTracker tracker;

    /**
     * Конструктор класса StartUI.
     */
    public StartUI(SqlTracker tracker, Input input) {
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
     *
     * @param args параметры запуска.
     */
    public static void main(String[] args) {
        SqlTracker sqlTracker = new SqlTracker();
        sqlTracker.init();
        Input input = new ValidateInput(new ConsoleInput());
        StartUI startUI = new StartUI(sqlTracker, input);
        startUI.startWork();
    }
}
