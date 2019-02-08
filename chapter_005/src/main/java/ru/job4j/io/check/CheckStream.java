package ru.job4j.io.check;

import java.io.IOException;
import java.io.InputStream;

/**
 * Класс содержит методы для проверок потока.
 */
public class CheckStream {

    /**
     * Метод проверяет наличие в потоке четного числа.
     *
     * @param in поток данных.
     * @return результат проверки.
     */
    public boolean isNumber(InputStream in) {
        boolean result = false;
        try (InputStream inputStream = in) {
            while (inputStream.available() != 0) {
                char nextChar = (char) inputStream.read();
                if (Character.isDigit(nextChar) && Character.digit(nextChar, 10) % 2 == 0) {
                    result = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}