package ru.job4j.srp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;

/**
 * Тесты класса ReportEngine
 */
public class ReportEngineTest {

    /**
     * Проверка правильной генерации отчетов
     * в обычной реализации движка генерации.
     */
    @Test
    public void whenOldGeneratedThenCorrect() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Engine engine = new ReportEngine(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        Assert.assertThat(engine.generate(employee -> true), is(expected.toString()));
    }
}