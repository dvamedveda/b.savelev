package ru.job4j.isp;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;

/**
 * Тесты класса ru.job4j.isp.SimpleMenuItem.
 */
public class SimpleMenuItemTest {

    /**
     * Проверка создания пункта меню.
     */
    @Test
    public void whenCreateMenuItemTnenNameCorrect() {
        MenuItem menuItem = new SimpleMenuItem("First menu item", "Printing first menu item.");
        Assert.assertThat(menuItem.getName(), is("First menu item"));
    }

    /**
     * Проверка получения пункта меню по названию.
     */
    @Test
    public void whenAddSomeSubMenuThenContainsCorrectMenus() {
        MenuItem menuItem = new SimpleMenuItem("First", "Print first");
        MenuItem subMenuItem = new SimpleMenuItem("Submenu item", "Print submenu");
        menuItem.add(subMenuItem);
        Assert.assertThat(menuItem.getIfContains("First"), is(menuItem));
        Assert.assertThat(menuItem.getIfContains("Submenu item"), is(subMenuItem));
    }

    /**
     * Проверка получения несуществующего пункта меню по названию.
     */
    @Test
    public void whenAddSomeSubMenuThenNotContainsOtherMenus() {
        MenuItem menuItem = new SimpleMenuItem("First", "Print first");
        MenuItem subMenuItem = new SimpleMenuItem("Submenu item", "Print submenu");
        menuItem.add(subMenuItem);
        Assert.assertNull(menuItem.getIfContains("Second"));
    }

    /**
     * Проверка получения потомков пункта меню.
     */
    @Test
    public void whenGetChildsThenGetAllChilds() {
        MenuItem menuItem = new SimpleMenuItem("First", "Print first");
        MenuItem subMenuItem = new SimpleMenuItem("Submenu item", "Print submenu");
        MenuItem subSubMenuItem = new SimpleMenuItem("Subsubmenu item", "Print subsubmenu");
        subMenuItem.add(subSubMenuItem);
        menuItem.add(subMenuItem);
        Assert.assertThat(menuItem.getChilds().size(), is(1));
        Assert.assertTrue(menuItem.getChilds().contains(subMenuItem));
        Assert.assertThat(subMenuItem.getChilds().size(), is(1));
        Assert.assertTrue(subMenuItem.getChilds().contains(subSubMenuItem));
    }

    /**
     * Проверка получения потомков пункта меню, если они отсутствуют.
     */
    @Test
    public void whenNoChildsThenGetChildsReturnEmptyList() {
        MenuItem menuItem = new SimpleMenuItem("First", "Print first");
        MenuItem subMenuItem = new SimpleMenuItem("Submenu item", "Print submenu");
        menuItem.add(subMenuItem);
        Assert.assertTrue(subMenuItem.getChilds().isEmpty());
    }

    /**
     * Проверка выполнения пункта меню.
     */
    @Test
    public void whenExecuteMenuThenExecutesCorrect() {
        OutputStream newOut = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(newOut));
        MenuItem menuItem = new SimpleMenuItem("First", "Print first");
        menuItem.execute();
        StringBuilder expected = new StringBuilder()
                .append("Print first")
                .append(System.lineSeparator());
        Assert.assertThat(newOut.toString(), is(expected.toString()));
        System.setOut(oldOut);
    }

    /**
     * Проверка распечатывания потомков пункта меню.
     */
    @Test
    public void whenPrintMenuItemThenPrintsCorrect() {
        OutputStream newOut = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(newOut));
        MenuItem menuItem = new SimpleMenuItem("First", "Print first");
        MenuItem subMenuItemOne = new SimpleMenuItem("SecondOne", "Print second one");
        MenuItem subMenuItemTwo = new SimpleMenuItem("SecondTwo", "Print second two");
        MenuItem subSubMenuItem = new SimpleMenuItem("Third", "Print third");
        menuItem.add(subMenuItemOne);
        menuItem.add(subMenuItemTwo);
        subMenuItemTwo.add(subSubMenuItem);
        menuItem.printChilds("--");
        StringBuilder expected = new StringBuilder()
                .append("--SecondOne").append(System.lineSeparator())
                .append("--SecondTwo").append(System.lineSeparator())
                .append("----Third").append(System.lineSeparator());
        Assert.assertThat(newOut.toString(), is(expected.toString()));
        System.setOut(oldOut);
    }
}