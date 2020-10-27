package ru.job4j.isp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс для демонстрации работы меню.
 */
public class Demo {

    /**
     * При запуске печатает меню, позволяя выбрать пункты меню и выполнить их, написав имя пункта,
     * или завершить работу программы, напечатав "exit".
     *
     * @param args аргументы запуска программы.
     * @throws IOException бросается при ошибках работы потоков ввода-вывода.
     */
    public static void main(String[] args) throws IOException {
        MenuItem one = new SimpleMenuItem("1", "Action: Item #1 has been selected!");
        MenuItem two = new SimpleMenuItem("2", "Action: Item #2 has been selected!");
        MenuItem twoOne = new SimpleMenuItem("2.1", "Action: Item #2.1 has been selected!");
        MenuItem twoOneOne = new SimpleMenuItem("2.1.1", "Action: Item #2.1.1 has been selected!");
        twoOne.add(twoOneOne);
        two.add(twoOne);
        MenuItem three = new SimpleMenuItem("3", "Action: Item #3 has been selected!");
        MenuItem four = new SimpleMenuItem("4", "Action: Item #4 has been selected!");
        MenuItem five = new SimpleMenuItem("5", "Action: Item #5 has been selected!");
        five.add(new SimpleMenuItem("5.1", "Action: Item #5.1 has been selected!"));
        five.add(new SimpleMenuItem("5.2", "Action: Item #5.2 has been selected!"));
        Menu menu = new Menu(one, two, three, four, five);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Hello!");
            menu.printMenu();
            System.out.println("Please select one menu item or type 'exit' for finish.");
            String answer;
            do {
                answer = reader.readLine();
                menu.select(answer);
                if (answer.equals("exit")) {
                    System.out.println("Program finished. Bye!");
                }
            } while (!answer.equals("exit"));
        }
    }
}