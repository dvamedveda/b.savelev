package ru.job4j.di;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;

/**
 * Тесты класса ConsoleInput.
 */
public class ConsoleInputTest {

    /**
     * Проверка регистрации в контексте и работы класса для ввода.
     */
    @Test
    public void whenContextRegThenSuccess() {
        InputStream stdIn = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("Hello!".getBytes());
        System.setIn(in);
        Context context = new Context();
        context.register(ConsoleInput.class);
        ConsoleInput consoleInput = context.get(ConsoleInput.class);
        String result = consoleInput.ask();
        Assert.assertThat(result, is("Hello!"));
        System.setIn(stdIn);
    }
}