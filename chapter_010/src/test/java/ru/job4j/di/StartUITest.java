package ru.job4j.di;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;

/**
 * Тесты класса StartUI.
 */
public class StartUITest {

    /**
     * Проверка регистрации в контексте и работы главного класса.
     */
    @Test
    public void whenContextRegThenSuccess() {
        InputStream stdIn = System.in;
        PrintStream stdOut = System.out;
        ByteArrayInputStream in = new ByteArrayInputStream("This is a test!".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ru.job4j.di");
        context.refresh();
        StartUI ui = context.getBean(StartUI.class);
        ui.askAndAdd();
        ui.print();
        String actual = out.toString();
        String expected = new StringBuilder()
                .append("Enter some string:").append(System.lineSeparator())
                .append("String added: This is a test!").append(System.lineSeparator())
                .append("All added strings:").append(System.lineSeparator())
                .append("This is a test!").append(System.lineSeparator())
                .toString();
        Assert.assertEquals(actual, expected);
        System.setOut(stdOut);
        System.setIn(stdIn);
    }
}