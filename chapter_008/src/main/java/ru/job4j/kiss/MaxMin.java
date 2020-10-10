package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

/**
 * Класс для поиска минимального и максимакльного значения из списка.
 * При помощи передаваемого компаратора.
 */
public class MaxMin {

    /**
     * Вернуть при помощи компаратора максимальное значение из списка.
     *
     * @param value      список значений.
     * @param comparator компаратор.
     * @param <T>        тип значений.
     * @return максимальное значение.
     */
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return getLastSorted(value, comparator, true);
    }

    /**
     * Вернуть при помощи компаратора минимальное значение из списка.
     *
     * @param value      список значений.
     * @param comparator компаратор.
     * @param <T>        тип значений.
     * @return минимальное значение.
     */
    public <T> T min(List<T> value, Comparator<T> comparator) {
        return getLastSorted(value, comparator, false);
    }

    /**
     * Общий метод для получения последнего сортированного значения.
     *
     * @param value      список значений.
     * @param comparator компаратор для сортировки.
     * @param direction  направление сортировки.
     * @param <T>        тип значений.
     * @return последнее отсортированное значение.
     */
    private <T> T getLastSorted(List<T> value, Comparator<T> comparator, boolean direction) {
        T result = value.size() == 0 ? null : value.get(0);
        if (value.size() > 1) {
            for (int i = 1; i < value.size(); i++) {
                if (direction) {
                    if (comparator.compare(value.get(i), result) > 0) {
                        result = value.get(i);
                    }
                } else {
                    if (comparator.compare(value.get(i), result) < 0) {
                        result = value.get(i);
                    }
                }
            }
        }
        return result;
    }
}