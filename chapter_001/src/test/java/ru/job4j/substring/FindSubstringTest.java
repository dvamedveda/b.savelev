package ru.job4j.substring;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса FindSubstring.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class FindSubstringTest {

    /**
     * Тест метода FindSubstring.contains().
     * Проверяет возвращаемое значение для случаев, когда строка содержит подстроку.
     */
    @Test
    public void whenStringContainsSubstringThenReturnTrue() {
        FindSubstring findSubstring = new FindSubstring();
        String original = "original";
        String substring = "rig";
        boolean result = findSubstring.contains(original, substring);
        assertThat(result, is(true));
    }

    /**
     * Тест метода FindSubstring.contains().
     * Проверяет возвращаемое значение для случаев, когда строка не содержит подстроку.
     */
    @Test
    public void whenStringNotContainsSubstringThenReturnFalse() {
        FindSubstring findSubstring = new FindSubstring();
        String original = "original";
        String substring = "rag";
        boolean result = findSubstring.contains(original, substring);
        assertThat(result, is(false));
    }

    /**
     * Тест метода FindSubstring.contains().
     * Проверяет возвращаемое значение для случаев, когда переданная стркоа пуста.
     */
    @Test
    public void whenStringEmptyThenReturnFalse() {
        FindSubstring findSubstring = new FindSubstring();
        String original = "";
        String substring = "dig";
        boolean result = findSubstring.contains(original, substring);
        assertThat(result, is(false));
    }

    /**
     * Тест метода FindSubstring.contains().
     * Проверяет возвращаемое значение для случаев, когда переданная подстрока пуста.
     */
    @Test
    public void whenSubtringEmptyThenReturnFalse() {
        FindSubstring findSubstring = new FindSubstring();
        String original = "original";
        String substring = "";
        boolean result = findSubstring.contains(original, substring);
        assertThat(result, is(false));
    }

    /**
     * Тест метода FindSubstring.contains().
     * Проверяет возвращаемое значение для случаев, когда строка или подстрока переданы в разных регистрах.
     */
    @Test
    public void whenStringAndSubstringInDifferentRegistryThenReturnTrue() {
        FindSubstring findSubstring = new FindSubstring();
        String original = "oRiGiNal";
        String substring = "rIg";
        boolean result = findSubstring.contains(original, substring);
        assertThat(result, is(true));
    }

    /**
     * Тест метода FindSubstring.contains().
     * Проверяет возвращаемое значение для случаев, когда переданная подстрока длиннее переданной строки.
     */
    @Test
    public void whenStringLessThanSubstringThenReturnFalse() {
        FindSubstring findSubstring = new FindSubstring();
        String original = "ri";
        String substring = "rig";
        boolean result = findSubstring.contains(original, substring);
        assertThat(result, is(false));
    }
}