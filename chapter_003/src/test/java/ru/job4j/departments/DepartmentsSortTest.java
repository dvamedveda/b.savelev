package ru.job4j.departments;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Класс, содержащий тесты класса DepartmentsSort.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class DepartmentsSortTest {

    /**
     * Тест проверяет восставновление департаментов в исходном списке.
     */
    @Test
    public void whenDepartmentMissingThenSortRecoversIt() {
        String[] notFullArray = {
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2"};
        DepartmentsSort departments = new DepartmentsSort();
        String[] result = departments.sortAsc(notFullArray);
        String[] expected = {
                "K1",
                "K1\\SK1",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K1\\SK2"};
        Assert.assertThat(result, is(expected));
    }

    /**
     * Тест проверяет сортировку департаментов по возрастанию из исходного списка,
     * а также восстанавливает отсутствующие департаменты.
     */
    @Test
    public void whenSortAscThenSortsAscAndRecovers() {
        String[] sourceArray = {
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"};
        DepartmentsSort departments = new DepartmentsSort();
        String[] result = departments.sortAsc(sourceArray);
        String[] expected = {
                "K1",
                "K1\\SK1",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K1\\SK2",
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"};
        Assert.assertThat(result, is(expected));
    }

    /**
     * Тест проверяет сортировку департаментов по убыванию из исходного списка,
     * а также восстанавливает отсутствующие департаменты.
     */
    @Test
    public void whenSortDescThenSortsDescAndRecovers() {
        String[] sourceArray = {
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"};
        DepartmentsSort departments = new DepartmentsSort();
        String[] result = departments.sortDesc(sourceArray);
        String[] expected = {
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK2",
                "K2\\SK1\\SSK1",
                "K1",
                "K1\\SK2",
                "K1\\SK1",
                "K1\\SK1\\SSK2",
                "K1\\SK1\\SSK1"};
        Assert.assertThat(result, is(expected));
    }
}