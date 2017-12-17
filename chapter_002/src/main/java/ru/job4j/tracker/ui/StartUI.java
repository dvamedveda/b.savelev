package ru.job4j.tracker.ui;

import ru.job4j.tracker.tracker.*;

/**
 * Класс, содержающий логику взаимодействия с пользователем.
 */
public class StartUI {

    /**
     * Флажок для выхода.
     * Программа работает до тех пор, пока значение истинно.
     */
    static volatile boolean working = true;

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

        MenuTracker menu = new MenuTracker(this.ui, this.tracker);
        menu.fillActions();

        do {
            menu.showMenu();
            String answer = this.ui.ask("Введите номер пункта меню для продолжения...");
            int key = Integer.valueOf(answer);
            menu.select(key);
        } while (working);
    }

    /**
     * Точка запуска программы.
     * Это же очевидно.
     *
     * @param args параметры запуска.
     */
    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        Input input = new ConsoleInput();
        StartUI startUI = new StartUI(tracker, input);
        startUI.startWork();
    }
}
