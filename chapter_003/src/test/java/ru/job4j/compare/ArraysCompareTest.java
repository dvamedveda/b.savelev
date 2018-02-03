package ru.job4j.compare;

import org.junit.Test;

import java.util.*;

/**
 * Класс содержащий тесты класса ArraysCompare.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class ArraysCompareTest {

    /**
     * Тест, проверяющий скорость заполнения ArrayList.
     */
    @Test
    public void compareArrayListAdd() {
        ArraysCompare ac = new ArraysCompare();
        List<String> arrayList = new ArrayList<>();
        int iterations = 10000000;
        long result = ac.add(arrayList, iterations);
        System.out.println(String.format("ArrayList adding, ms: %s", result));
    }

    /**
     * Тест, проверяющий скорость заполнения LinkedList.
     */
    @Test
    public void compareLinkedListAdd() {
        ArraysCompare ac = new ArraysCompare();
        List<String> linkedList = new LinkedList<>();
        int iterations = 10000000;
        long result = ac.add(linkedList, iterations);
        System.out.println(String.format("LinkedList adding, ms: %s", result));
    }

    /**
     * Тест, проверяющий скорость заполнения TreeSet.
     */
    @Test
    public void compareTreeSetAdd() {
        ArraysCompare ac = new ArraysCompare();
        Set<String> treeSet = new TreeSet<>();
        int iterations = 10000000;
        long result = ac.add(treeSet, iterations);
        System.out.println(String.format("TreeSet adding, ms: %s", result));
    }

    /**
     * Тест, проверяющий скорость удаления первых N элементов из ArrayList.
     */
    @Test
    public void compareArrayListDelete() {
        ArraysCompare ac = new ArraysCompare();
        List<String> arrayList = new ArrayList<>();
        int iterations = 10000000;
        ac.add(arrayList, iterations);
        long result = ac.delete(arrayList, 10000);
        System.out.println(String.format("ArrayList deleting, ms: %s", result));
    }

    /**
     * Тест, проверяющий скорость удаления первых N элементов из LinkedList.
     */
    @Test
    public void compareLinkedListDelete() {
        ArraysCompare ac = new ArraysCompare();
        List<String> linkedList = new LinkedList<>();
        int iterations = 10000000;
        ac.add(linkedList, iterations);
        long result = ac.delete(linkedList, 10000);
        System.out.println(String.format("LinkedList deleting, ms: %s", result));
    }

    /**
     * Тест, проверяющий скорость удаления первых N элементов из TreeSet.
     */
    @Test
    public void compareTreeSetDelete() {
        ArraysCompare ac = new ArraysCompare();
        Set<String> treeSet = new TreeSet<>();
        int iterations = 10000000;
        ac.add(treeSet, iterations);
        long result = ac.delete(treeSet, 10000);
        System.out.println(String.format("TreeSet deleting, ms: %s", result));
    }
}