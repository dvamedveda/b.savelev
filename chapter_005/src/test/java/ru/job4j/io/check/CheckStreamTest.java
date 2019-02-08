package ru.job4j.io.check;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Класс содержит тесты класса CheckStream.
 */
public class CheckStreamTest {

    /**
     * Тест проверяет работу метода isNumber(), когда в него приходят данные без четных чисел.
     */
    @Test
    public void whenWriteNonNumbersThenCheckIsFalse() {
        String text = "text";
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());
        CheckStream cs = new CheckStream();
        Assert.assertFalse(cs.isNumber(inputStream));
    }

    /**
     * Тест проверяет работу метода isNumber(), когда в него приходят данные с числами, но без четных.
     */
    @Test
    public void whenWriteNumbersAndNotOddThenCheckIsFalse() {
        String text = "13579";
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());
        CheckStream cs = new CheckStream();
        Assert.assertFalse(cs.isNumber(inputStream));
    }

    /**
     * Тест проверяет работу метода isNumber(), когда в него приходят данные, в том числе и целые числа.
     */
    @Test
    public void whenWriteNumbersAndIsOddThenCheckIsTrue() {
        String text = "text123";
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());
        CheckStream cs = new CheckStream();
        Assert.assertTrue(cs.isNumber(inputStream));
    }
}