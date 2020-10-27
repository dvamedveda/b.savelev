package ru.job4j.isp;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;

/**
 * Тесты класса ru.job4j.isp.Menu.
 */
public class MenuTest {

    /**
     * Проверка печати полного меню.
     */
    @Test
    public void whenPrintMenuThenPrintsCorrect() {
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
        Menu menu = new Menu(one, two, three, four);
        menu.add(five);
        OutputStream newOut = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(newOut));
        String line = System.lineSeparator();
        String expected = new StringBuilder()
                .append("1").append(line)
                .append("2").append(line)
                .append("--2.1").append(line)
                .append("----2.1.1").append(line)
                .append("3").append(line)
                .append("4").append(line)
                .append("5").append(line)
                .append("--5.1").append(line)
                .append("--5.2").append(line)
                .toString();
        menu.printMenu();
        Assert.assertThat(newOut.toString(), is(expected));
        System.setOut(oldOut);
    }

    /**
     * Проверка выполнения пункта меню при выборе его в меню.
     */
    @Test
    public void whenSelectItemThenExecutes() {
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
        Menu menu = new Menu(one, two, three, four);
        menu.add(five);
        OutputStream newOut = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(newOut));
        String line = System.lineSeparator();
        String expectedOne = new StringBuilder()
                .append("Action: Item #2.1.1 has been selected!").append(line)
                .toString();
        menu.select("2.1.1");
        Assert.assertThat(newOut.toString(), is(expectedOne));
        newOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOut));
        String expectedTwo = new StringBuilder()
                .append("Action: Item #5.2 has been selected!").append(line)
                .toString();
        menu.select("5.2");
        Assert.assertThat(newOut.toString(), is(expectedTwo));
        System.setOut(oldOut);
    }

    /**
     * Проверка поведения меню, если выбран несуществующий пункт.
     */
    @Test
    public void whenSelectNonExistentItemThenPrintsNotFound() {
        MenuItem one = new SimpleMenuItem("1", "Action: Item #1 has been selected!");
        MenuItem two = new SimpleMenuItem("2", "Action: Item #2 has been selected!");
        MenuItem twoOne = new SimpleMenuItem("2.1", "Action: Item #2.1 has been selected!");
        MenuItem twoOneOne = new SimpleMenuItem("2.1.1", "Action: Item #2.1.1 has been selected!");
        twoOne.add(twoOneOne);
        two.add(twoOne);
        Menu menu = new Menu(one, two);
        OutputStream newOut = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(newOut));
        String line = System.lineSeparator();
        String expectedOne = new StringBuilder()
                .append("Menu item with name 6.11.1 not found!").append(line)
                .toString();
        menu.select("6.11.1");
        Assert.assertThat(newOut.toString(), is(expectedOne));
        System.setOut(oldOut);
    }
}