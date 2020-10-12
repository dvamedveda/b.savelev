package ru.job4j.template;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;

/**
 * Тесты класса Generator.
 */
public class GeneratorTest {

    /**
     * Проверяем случай, когда переданная строка правильная.
     */
    @Test
    public void whenPutRightStringThenReturnsRightString() {
        String template = "I am a ${name}, Who are ${subject}?";
        HashMap<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        String expected = "I am a Petr Arsentev, Who are you?";
        TemplateGenerator generator = Mockito.mock(TemplateGenerator.class);
        Mockito.when(generator.produce(template, args)).thenReturn(expected);
        String result = generator.produce(template, args);
        Assert.assertThat(result, is(expected));
        Mockito.verify(generator, Mockito.times(1)).produce(template, args);
    }

    /**
     * Проверяем случай, когда в шаблоне больше аргументов, чем в списке.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenPutExtraArgThenThrowException() {
        String template = "I am a ${name}, Who ${extra}are ${subject}?";
        HashMap<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        TemplateGenerator generator = Mockito.mock(TemplateGenerator.class);
        Mockito.when(generator.produce(template, args)).thenThrow(IllegalArgumentException.class);
        String result = generator.produce(template, args);
        Mockito.verify(generator, Mockito.times(1)).produce(template, args);
    }

    /**
     * Проверяем случай, когда в шаблоне меньше аргументов, чем в списке.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenNotEnoughArgThenThrowException() {
        String template = "I am a ${name}, Who are ${subject}?";
        HashMap<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        args.put("extra", "is");
        TemplateGenerator generator = Mockito.mock(TemplateGenerator.class);
        Mockito.when(generator.produce(template, args)).thenThrow(IllegalArgumentException.class);
        String result = generator.produce(template, args);
        Mockito.verify(generator, Mockito.times(1)).produce(template, args);
    }
}