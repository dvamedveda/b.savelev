package ru.job4j.soft;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;

/**
 * Здесь проверяется поведение кэша, реализованного на мягких ссылках в классе SoftCache.
 */
public class SoftCacheTest {

    /**
     * В этом тесте проверяется загрузка и отдача содержимого файла по ключу
     */
    @Test
    public void whenLoadResourceThenLoadsSuccess() {
        Cache softCache = new SoftCache("./src/test/resources");
        String actual = softCache.get("test_file_one.txt");
        String expected = new String("This is a just text file, which need for test in SoftCache tests.\n"
                + "This is the first file.\n");
        Assert.assertThat(expected, is(actual));
    }

    /**
     * Здесь проверяется попытка загрузки несуществующего в системе файла.
     */
    @Test
    public void whenLoadUnexistFileThenError() {
        String line = System.lineSeparator();
        PrintStream stdOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Cache softCache = new SoftCache("./src/test/resources");
        softCache.get("test_file_three.txt");
        String result = new String(out.toByteArray());
        String expected = "File with that name not found! Please, try another name..." + line;
        Assert.assertThat(result, is(expected));
        System.setOut(stdOut);
    }
}