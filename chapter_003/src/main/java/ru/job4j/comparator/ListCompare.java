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
        int leftIndex = 0, rightIndex = 0;
        while (leftIndex < left.size() && rightIndex < right.size()) {
            result = Integer.compare(left.get(leftIndex), right.get(rightIndex));
            if (result != 0) {
                break;
            } else if (leftIndex == left.size() - 1 && rightIndex < right.size() - 1) {
                result = -1;
                break;
            } else if (leftIndex < left.size() - 1 && rightIndex == right.size() - 1) {
                result = 1;
                break;
            }
            leftIndex++;
            rightIndex++;
        }
        return result;
    }
}