package ru.job4j.di.ui;

import ru.job4j.di.tracker.Item;
import ru.job4j.di.tracker.Tracker;

import java.util.ArrayList;

/**
 * "Внешний" внутренний класс.
 * Описывает действие программы в случае выбора пункта меню "Выход из программы"
 */
class Exit extends BaseAction {

    public Exit(int key, String name) {
        super(key, name);
    }

    /**
     * Реализация действия Выход
     * @param input объект ввода
     * @param tracker объект трекера
     */
    public void execute(Input input, Tracker tracker) {
        System.out.println("Выбран пункт меню 0. Выход из программы. До свидания! -=^_^=-");
    }
}

/**
 * Класс, описывающий работу меню
 */
public class MenuTracker {

    private Input input;
    private Tracker tracker;
    private ArrayList<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор класса меню.
     * @param input объект ввода.
     * @param tracker объект трекера.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Геттер для получения диапазона допустимых для выбора пользователем пунктов меню.
     * @return диапазон допустимых пунктов меню.
     */
    public ArrayList<Integer> getOptions() {
        ArrayList<Integer> result = new ArrayList<>();
        for (UserAction ua : this.actions) {
            result.add(ua.key());
        }
        return result;
    }

    /**
     * Наполнение списка действий доступными действиями.
     */
    public void fillActions() {
        this.actions.add(new Exit(0, "Выход из программы."));
        this.actions.add(this.new AddItem(1, "Создание новой заявки."));
        this.actions.add(this.new UpdateItem(2, "Обновление существующей заявки."));
        this.actions.add(this.new DeleteItem(3, "Удаление заявки по ее ID."));
        this.actions.add(this.new FindAllItems(4, "Поиск всех заявок."));
        this.actions.add(this.new FindByName(5, "Поиск заявки по названию."));
        this.actions.add(this.new FindById(6, "Поиск заявки по ID."));
    }

    public void select(int key) {
        for (UserAction action : this.actions) {
            if (action.key() == key) {
                action.execute(this.input, this.tracker);
            }
        }
    }

    /**
     * Показать меню.
     */
    public void showMenu() {
        System.out.println();
        System.out.println("+++++++++++++++++++++++++++++++++");
        System.out.println("Меню программы :");
        for (UserAction action : this.actions) {
            System.out.println(action.info());
        }
        System.out.println("+++++++++++++++++++++++++++++++++");
        System.out.println();
    }

    /**
     * В этом классе реализуется действие пользователя "Добавление заявки".
     */
    private class AddItem extends BaseAction {

        public AddItem(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println(String.format("Выбран пункт меню %s", this.info()));
            System.out.println("Перед добавлением заявку нужно заполнить!");
            String summary = input.ask("Введите название заявки :");
            String description = input.ask("Введите описание заявки :");
            long createdTime = System.currentTimeMillis();
            Item addedItem = tracker.add(new Item(summary, description, createdTime));
            System.out.println(String.format("Заявка с идентификатором %s успешно добавлена.",  addedItem.getId()));
        }
    }

    /**
     * В этом классе реализуется действие пользователя "Обновление заявки".
     */
    private class UpdateItem extends BaseAction {

        public UpdateItem(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println(String.format("Выбран пункт меню %s", this.info()));
            String updateId = input.ask("Введите ID заявки : ");
            if (tracker.findById(updateId) != null) {
                System.out.println("Найдена заявка : ");
                PrettyPrint.printItem(tracker.findById(updateId));
                String newName = input.ask("Введите новое имя заявки : ");
                String newDesc = input.ask("Введите новое описание заявки : ");
                long newCreated = System.currentTimeMillis();
                Item updateItem = new Item(newName, newDesc, newCreated);
                updateItem.setId(updateId);
                tracker.update(updateItem);
                System.out.println("Заявка успешно изменена!");
            } else {
                System.out.println("Заявки с таким ID не найдено!");
            }
        }
    }

    /**
     * В этом классе реализуется действие пользователя "Удаление заявки".
     */
    private class DeleteItem extends BaseAction {

        public DeleteItem(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println(String.format("Выбран пункт меню %s", this.info()));
            String deleteId = input.ask("Введите ID заявки : ");
            if (tracker.findById(deleteId) != null) {
                tracker.delete(tracker.findById(deleteId));
                System.out.println(String.format("Заявка с идентификатором %s удалена.", deleteId));
            } else {
                System.out.println("Заявки с таким ID не найдено!");
            }
        }
    }

    /**
     * В этом классе реализуется действие пользователя "Поиск всех заявок".
     */
    private class FindAllItems extends BaseAction {

        public FindAllItems(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println(String.format("Выбран пункт меню %s", this.info()));
            if (tracker.findAll().size() > 0) {
                ArrayList<Item> allItems = tracker.findAll();
                System.out.println("Найдены следующие заявки : ");
                for (Item nextFoundItem : allItems) {
                    PrettyPrint.printItem(nextFoundItem);
                }
            } else {
                System.out.println("На данный момент не существует никаких заявок.");
            }
        }
    }

    /**
     * В этом классе реализуется действие пользователя "Поиск по названию".
     */
    private class FindByName extends BaseAction {

        public FindByName(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println(String.format("Выбран пункт меню %s", this.info()));
            String itemName = input.ask("Введите название заявки : ");
            if (tracker.findByName(itemName) != null) {
                ArrayList<Item> foundedItems = tracker.findByName(itemName);
                System.out.println("Найдены следующие заявки : ");
                for (Item nextFoundItem : foundedItems) {
                    PrettyPrint.printItem(nextFoundItem);
                }
            } else {
                System.out.println("Заявки с таким названием не найдено!");
            }
        }
    }

    /**
     * В этом классе реализуется действие пользователя "Поиск по Id".
     */
    private class FindById extends BaseAction {

        public FindById(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println(String.format("Выбран пункт меню %s", this.info()));
            String searchId = input.ask("Введите ID заявки : ");
            if (tracker.findById(searchId) != null) {
                Item foundedItem = tracker.findById(searchId);
                System.out.println("Найдена заявка : ");
                PrettyPrint.printItem(foundedItem);
            } else {
                System.out.println("Заявки с таким ID не найдено!");
            }
        }
    }

    /**
     * Статический класс для вывода заявок.
     */
    static class PrettyPrint {
        /**
         * Распечатка заявки.
         * Используется для показа содержимого заявки пользователю.
         *
         * @param item заявка, содержмиое которой нужно отобразить.
         */
        public static void printItem(Item item) {
            if (item != null) {
                System.out.println("---------------------------------");
                System.out.println(String.format("Заявка с идентификатором %s", item.getId()));
                System.out.println(String.format("Название заявки: %s", item.getSummary()));
                System.out.println("---------------------------------");
                System.out.println("Описание заявки:");
                System.out.println(item.getDescription());
                System.out.println("---------------------------------");
                System.out.println();
            }
        }
    }
}