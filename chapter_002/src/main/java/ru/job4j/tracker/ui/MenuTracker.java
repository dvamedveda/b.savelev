package ru.job4j.tracker.ui;

import ru.job4j.tracker.tracker.Item;
import ru.job4j.tracker.tracker.Tracker;

/**
 * "Внешний" внутренний класс.
 * Описывает действие программы в случае выбора пункта меню "Выход из программы"
 */
class Exit implements UserAction {
    /**
     * Действие пользователя в массиве возможных действий.
     * @return номер действия
     */
    public int key() {
        return 0;
    }

    /**
     * Реализация действия Выход
     * @param input объект ввода
     * @param tracker объект трекера
     */
    public void execute(Input input, Tracker tracker) {
        System.out.println("Выбран пункт меню 0. Выход из программы. До свидания! -=^_^=-");
    }

    /**
     * Распечатка информации для меню.
     * @return информация для меню.
     */
    public String info() {
        return String.format("%s. %s", this.key(), "Выход из программы.");
    }
}

/**
 * Класс, описывающий работу меню
 */
public class MenuTracker {

    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[7];

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
     * Наполнение массива действий доступными действиями.
     */
    public void fillActions() {
        this.actions[0] = new Exit();
        this.actions[1] = this.new AddItem();
        this.actions[2] = this.new UpdateItem();
        this.actions[3] = this.new DeleteItem();
        this.actions[4] = this.new FindAllItems();
        this.actions[5] = this.new FindByName();
        this.actions[6] = this.new FindById();
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    /**
     * Показать меню.
     */
    public void showMenu() {
        System.out.println();
        System.out.println("+++++++++++++++++++++++++++++++++");
        System.out.println("Меню программы :");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
        System.out.println("+++++++++++++++++++++++++++++++++");
        System.out.println();
    }

    /**
     * В этом классе описывается действие пользователя "Добавление заявки".
     */
    private class AddItem implements UserAction {

        public int key() {
            return 1;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("Выбран пункт меню 1. Создание новой заявки.");
            System.out.println("Перед добавлением заявку нужно заполнить!");
            String summary = input.ask("Введите название заявки :");
            String description = input.ask("Введите описание заявки :");
            long createdTime = System.currentTimeMillis();
            Item addedItem = tracker.add(new Item(summary, description, createdTime));
            System.out.println("Заявка с идентификатором " + addedItem.getId() + " успешно добавлена.");
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Создание новой заявки.");
        }
    }

    /**
     * В этом классе описывается действие пользователя "Обновление заявки".
     */
    private class UpdateItem implements UserAction {

        public int key() {
            return 2;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("Выбран пункт меню 2. Обновление существующей заявки.");
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

        public String info() {
            return String.format("%s. %s", this.key(), "Обновление существующей заявки.");
        }
    }

    /**
     * В этом классе описывается действие пользователя "Удаление заявки".
     */
    private class DeleteItem implements UserAction {

        public int key() {
            return 3;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("Выбран пункт меню 3. Удаление заявки по ее ID.");
            String deleteId = input.ask("Введите ID заявки : ");
            if (tracker.findById(deleteId) != null) {
                tracker.delete(tracker.findById(deleteId));
                System.out.println("Заявка с идентификатором " + deleteId + " удалена.");
            } else {
                System.out.println("Заявки с таким ID не найдено!");
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Удаление заявки по ее ID.");
        }
    }

    /**
     * В этом классе описывается действие пользователя "Поиск всех заявок".
     */
    private class FindAllItems implements UserAction {

        public int key() {
            return 4;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("Выбран пункт меню 4. Поиск всех заявок.");
            if (tracker.findAll().length > 0) {
                Item[] allItems = tracker.findAll();
                System.out.println("Найдены следующие заявки : ");
                for (Item nextFoundItem : allItems) {
                    PrettyPrint.printItem(nextFoundItem);
                }
            } else {
                System.out.println("На данный момент не существует никаких заявок.");
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Поиск всех заявок.");
        }
    }

    /**
     * В этом классе описывается действие пользователя "Поиск по названию".
     */
    private class FindByName implements UserAction {

        public int key() {
            return 5;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("Выбран пункт меню 5. Поиск заявки по названию.");
            String itemName = input.ask("Введите название заявки : ");
            if (tracker.findByName(itemName) != null) {
                Item[] foundedItems = tracker.findByName(itemName);
                System.out.println("Найдены следующие заявки : ");
                for (Item nextFoundItem : foundedItems) {
                    PrettyPrint.printItem(nextFoundItem);
                }
            } else {
                System.out.println("Заявки с таким названием не найдено!");
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Поиск заявки по названию.");
        }
    }

    /**
     * В этом классе описывается действие пользователя "Поиск по Id".
     */
    private class FindById implements UserAction {

        public int key() {
            return 6;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("Выбран пункт меню 6. Поиск заявки по ID.");
            String searchId = input.ask("Введите ID заявки : ");
            if (tracker.findById(searchId) != null) {
                Item foundedItem = tracker.findById(searchId);
                System.out.println("Найдена заявка : ");
                PrettyPrint.printItem(foundedItem);
            } else {
                System.out.println("Заявки с таким ID не найдено!");
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Поиск заявки по ID.");
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
                System.out.println("Заявка с идентификатором " + item.getId());
                System.out.println("Название заявки: " + item.getSummary());
                System.out.println("---------------------------------");
                System.out.println("Описание заявки:");
                System.out.println(item.getDescription());
                System.out.println("---------------------------------");
                System.out.println();
            }
        }
    }
}