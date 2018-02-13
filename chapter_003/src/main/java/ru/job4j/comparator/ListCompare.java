package ru.job4j.comparator;

import java.util.Comparator;
import java.util.List;

/**
 * Класс, содержащий методы для сравнения списков.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class ListCompare implements Comparator<List<Integer>> {

    /**
     * Сравниваем два списка по парам значений на одной и той же позиции.
     * Если списки не равны - то по длине списков.
     *
     * @param left  левый список.
     * @param right правый список.
     * @return результат сравнения.
     */
    @Override
    public int compare(List<Integer> left, List<Integer> right) {
        int result = 0;
        if (left.size() == right.size()) {
            for (int index = 0; index < left.size(); index++) {
                int pair = Integer.compare(left.get(index), right.get(index));
                if (pair != 0) {
                    result = pair;
                    break;
                }
            }
        } else {
            result = Integer.compare(left.size(), right.size());
        }
        return result;
    }
}