package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса ArrayDuplicate.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class ArrayDuplicateTest {

    /**
     * Тест, который проверяет, удаляет ли метод ArrayDuplicate.remove() дубликаты из массива строк.
     */
    @Test
    public void whenArrayHaveDuplicatesThenRemoveIt() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] withDuplicates = {"Vasya", "Kolya", "Dima", "Vasya", "Lesha", "Kolya"};
        String[] result = arrayDuplicate.remove(withDuplicates);
        String[] expected = {"Vasya", "Kolya", "Dima", "Lesha"};
        assertThat(expected, arrayContainingInAnyOrder(result));
    }
}