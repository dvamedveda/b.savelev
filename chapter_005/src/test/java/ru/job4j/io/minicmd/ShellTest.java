package ru.job4j.io.minicmd;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * Класс содержит тесты класса Shell.
 */
public class ShellTest {

    /**
     * Проверяем, что если в пути есть две точки, значит переходим на уровень выше.
     */
    @Test
    public void whenTwoDotThenGoUp() {
        Shell shell = new Shell();
        shell.cd("/home/boris/test/../../test");
        Assert.assertThat(shell.path(), is("/home/test"));
    }

    /**
     * Проверяем, что если в пути есть два слэша, значит игнорируем их.
     */
    @Test
    public void whenTwoSlashThenIgnore() {
        Shell shell = new Shell();
        shell.cd("/home/boris/////test/../////../test");
        Assert.assertThat(shell.path(), is("/home/test"));
    }

    /**
     * Проверяем, что если передан путь со слешем в начале, значит надо снова начинать путь от корневого каталога.
     */
    @Test
    public void whenStartSlashThenGoFromRoot() {
        Shell shell = new Shell();
        shell.cd("/home/boris/test/../../test").cd("/usr/lib/../java");
        Assert.assertThat(shell.path(), is("/usr/java"));
    }

    /**
     * Проверяем, что если в пути есть есть точка(переход в текущую директорию), значит игнорируем ее.
     */
    @Test
    public void whenOneDotThenIgnore() {
        Shell shell = new Shell();
        shell.cd("/home/boris/./test/../../test/.");
        Assert.assertThat(shell.path(), is("/home/test"));
    }

    /**
     * Проверка, что путь по умолчанию является корнем.
     */
    @Test
    public void whenDefaultThenRoot() {
        Shell shell = new Shell();
        Assert.assertThat(shell.path(), is("/"));
    }
}