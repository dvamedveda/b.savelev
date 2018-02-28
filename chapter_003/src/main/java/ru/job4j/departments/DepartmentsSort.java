package ru.job4j.departments;

import java.util.*;

/**
 * Класс содержащий методы для сортировки департаментов.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class DepartmentsSort {

    /**
     * Отсортировать департаменты по возрастанию.
     *
     * @param departments исходный список департаментов.
     * @return отсортированный список департаментов.
     */
    public String[] sortAsc(String[] departments) {
        String[] toSort = this.recoverHierarchy(departments);
        Set<String> sortedSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        sortedSet.addAll(Arrays.asList(toSort));
        String[] resultArray = new String[sortedSet.size()];
        return sortedSet.toArray(resultArray);
    }

    /**
     * Отсортировать департаменты по убыванию.
     *
     * @param departments исходный список департаментов.
     * @return отсортированный список департаментов.
     */
    public String[] sortDesc(String[] departments) {
        String[] toSort = this.recoverHierarchy(departments);
        Set<String> sortedSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int minimal = Math.min(o1.length(), o2.length());
                for (int index = 0; index < minimal; index++) {
                    Character leftChar = o1.charAt(index);
                    Character rightChar = o2.charAt(index);
                    if (rightChar.compareTo(leftChar) != 0) {
                        return rightChar - leftChar;
                    }
                }
                return o1.length() - o2.length();
            }
        });
        sortedSet.addAll(Arrays.asList(toSort));
        String[] resultArray = new String[sortedSet.size()];
        return sortedSet.toArray(resultArray);
    }

    /**
     * Восстановить недостающие в исходном массиве департаменты.
     *
     * @param departments исходный список департаментов.
     * @return восстановленный список департаментов.
     */
    private String[] recoverHierarchy(String[] departments) {
        Set<String> departmentsSet = new TreeSet<>();
        departmentsSet.addAll(Arrays.asList(departments));
        for (String department : departments) {
            ArrayList<Integer> separatorPositions = new ArrayList<>();
            for (int separatorPosition = 0; separatorPosition < department.length(); separatorPosition++) {
                if (department.charAt(separatorPosition) == '\\') {
                    separatorPositions.add(separatorPosition);
                }
            }
            for (Integer position : separatorPositions) {
                char[] departmentAsChar = department.toCharArray();
                String code = new String(departmentAsChar, 0, position);
                departmentsSet.add(code);
            }
        }
        return departmentsSet.toArray(new String[departmentsSet.size()]);
    }
}