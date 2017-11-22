package ru.job4j.substring;

/**
 * Класс FindSubstring, который содержит метод для работы со строкой contains().
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class FindSubstring {

    /**
     * Метод, который определяет, возвращая булево значение, содержит ли переданная строка переданную подстроку.
     * Этот метод не использует методы String.contains(), String.indexOf().
     *
     * @param original Оригинальная строка, которую нужно проверить на содержание подстроки.
     * @param substring Подстрока, которую требуется найти.
     * @return возвращает истину, если подстроку удалось найти в строке, иначе ложь.
     */
    public boolean contains(String original, String substring) {
        boolean result = false;

        if (original.isEmpty() || substring.isEmpty() || original.length() < substring.length()) {
            return result;
        } else {
            char[] originalChars = original.toLowerCase().toCharArray();
            char[] subChars = substring.toLowerCase().toCharArray();
            boolean matches = false;

            for (int originalCount = 0; originalCount < originalChars.length; originalCount++) {
                if (originalChars[originalCount] == subChars[0]) {
                    matches = true;
                    for (int subCount = 0, matchPairs = originalCount; subCount < subChars.length; subCount++, matchPairs++) {
                        if (subChars[subCount] != originalChars[matchPairs]) {
                            matches = false;
                        }
                    }
                }
                if (matches) {
                    result = matches;
                    break;
                }
            }
        }

        return result;
    }
}