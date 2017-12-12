package ru.job4j.tracker.ui;

import ru.job4j.tracker.tracker.*;

/**
 * Класс, содержающий логику взаимодействия с пользователем.
 */
public class StartUI {

    /**
     * Допустимые ответы от пользователя.
     */
    private final static String CREATE = "1";
    private final static String UPDATE = "2";
    private final static String DELETE = "3";
    private final static String FINDALL = "4";
    private final static String FINDBYNAME = "5";
    private final static String FINDBYID = "6";
    private final static String EXIT = "0";

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
        boolean working = true;

        do {
            this.showMenu();
            switch (this.ui.ask("Введите номер пункта меню для продолжения...")) {
                case CREATE:
                    this.createNewItem();
                    break;
                case UPDATE:
                    this.updateItem();
                    break;
                case DELETE:
                    this.deleteItem();
                    break;
                case FINDALL:
                    this.findAllItems();
                    break;
                case FINDBYNAME:
                    this.findByName();
                    break;
                case FINDBYID:
                    this.findByID();
                    break;
                case EXIT:
                    working = this.exit();
                    break;
                default:
                    System.out.println("Пункт меню не найден.");
                    System.out.println("Пожалуйста, введите номер одного из пунктов меню(1-6) или 0 для выхода из программы.");
                    break;
            }

        } while (working);
    }

    /**
     * Метод для отображения меню пользователю.
     */
    private void showMenu() {
        System.out.println();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Меню программы :");
        System.out.println("1. Создание новой заявки.");
        System.out.println("2. Обновление существующей заявки.");
        System.out.println("3. Удаление заявки по ее ID.");
        System.out.println("4. Поиск всех заявок.");
        System.out.println("5. Поиск заявки по названию.");
        System.out.println("6. Поиск заявки по ID.");
        System.out.println("0. Выход из программы");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();
    }

    /**
     * Обработка указания пользователя создать новую заявку.
     */
    private void createNewItem() {
        System.out.println("Выбран пункт меню 1. Создание новой заявки.");
        System.out.println("Перед добавлением заявку нужно заполнить!");
        String summary = this.ui.ask("Введите название заявки :");
        String description = this.ui.ask("Введите описание заявки :");
        long createdTime = System.currentTimeMillis();
        Item addedItem = this.tracker.add(new Item(summary, description, createdTime));
        System.out.println("Заявка с идентификатором " + addedItem.getId() + " успешно добавлена.");
    }

    /**
     * Обработка указания пользователя обновить заявку.
     */
    private void updateItem() {
        System.out.println("Выбран пункт меню 2. Обновление существующей заявки.");
        String updateId = this.ui.ask("Введите ID заявки : ");
        if (this.tracker.findById(updateId) != null) {
            System.out.println("Найдена заявка : ");
            this.printItem(this.tracker.findById(updateId));
            String newName = this.ui.ask("Введите новое имя заявки : ");
            String newDesc = this.ui.ask("Введите новое описание заявки : ");
            long newCreated = System.currentTimeMillis();
            Item updateItem = new Item(newName, newDesc, newCreated);
            updateItem.setId(updateId);
            this.tracker.update(updateItem);
            System.out.println("Заявка успешно изменена!");
        } else {
            System.out.println("Заявки с таким ID не найдено!");
        }
    }

    /**
     * Обработка указания пользователя удалить заявку.
     */
    private void deleteItem() {
        System.out.println("Выбран пункт меню 3. Удаление заявки по ее ID.");
        String deleteId = this.ui.ask("Введите ID заявки : ");
        if (this.tracker.findById(deleteId) != null) {
            this.tracker.delete(this.tracker.findById(deleteId));
            System.out.println("Заявка с идентификатором " + deleteId + " удалена.");
        } else {
            System.out.println("Заявки с таким ID не найдено!");
        }
    }

    /**
     * Обработка запроса пользователя на отображение всех заявок.
     */
    private void findAllItems() {
        System.out.println("Выбран пункт меню 4. Поиск всех заявок.");
        if (this.tracker.findAll().length > 0) {
            Item[] allItems = this.tracker.findAll();
            System.out.println("Найдены следующие заявки : ");
            for (Item nextFoundItem : allItems) {
                this.printItem(nextFoundItem);
            }
        } else {
            System.out.println("На данный момент не существует никаких заявок.");
        }
    }

    /**
     * Обработка запроса пользователя на отображение заявки по указанному названию.
     */
    private void findByName() {
        System.out.println("Выбран пункт меню 5. Поиск заявки по названию.");
        String itemName = this.ui.ask("Введите название заявки : ");
        if (this.tracker.findByName(itemName) != null) {
            Item[] foundedItems = this.tracker.findByName(itemName);
            System.out.println("Найдены следующие заявки : ");
            for (Item nextFoundItem : foundedItems) {
                this.printItem(nextFoundItem);
            }
        } else {
            System.out.println("Заявки с таким названием не найдено!");
        }
    }

    /**
     * Обработка запроса пользователя на отображение заявки по указанному идентификатору.
     */
    private void findByID() {
        System.out.println("Выбран пункт меню 6. Поиск заявки по ID.");
        String searchId = this.ui.ask("Введите ID заявки : ");
        if (this.tracker.findById(searchId) != null) {
            Item foundedItem = this.tracker.findById(searchId);
            System.out.println("Найдена заявка : ");
            this.printItem(foundedItem);
        } else {
            System.out.println("Заявки с таким ID не найдено!");
        }
    }

    /**
     * Обработка указания пользователя выйти из программы.
     *
     * @return флажок для прекращения работы программы.
     */
    private boolean exit() {
        System.out.println("Выбран пункт меню 0. Выход из программы. До свидания! -=^_^=-");
        return false;
    }

    /**
     * Распечатка заявки.
     * Используется для показа содержимого заявки пользователю.
     *
     * @param item заявка, содержмиое которой нужно отобразить.
     */
    private void printItem(Item item) {
        if (item != null) {
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Заявка с идентификатором " + item.getId());
            System.out.println("Название заявки: " + item.getSummary());
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Описание заявки:");
            System.out.println(item.getDescription());
            System.out.println("------------------------------------------------------------------------");
            System.out.println();
        }
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
