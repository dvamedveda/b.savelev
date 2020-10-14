package ru.job4j.srp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;

/**
 * Тесты класса HrReportEngine.
 */
public class HrReportEngineTest {

    /**
     * Проверка правильной генерации отчетов
     * в реализации движка генерации для кадровиков.
     */
    @Test
    public void whenGeneratedHRThenCorrectReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee first = new Employee("Ivan", now, now, 100_000);
        Employee second = new Employee("Vasiliy", now, now, 120_000);
        Employee third = new Employee("Nikolai", now, now, 111_000);
        store.add(first);
        store.add(second);
        store.add(third);
        Engine engine = new HrReportEngine(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(second.getName()).append(";").append(second.getSalary()).append(";").append(System.lineSeparator())
                .append(third.getName()).append(";").append(third.getSalary()).append(";").append(System.lineSeparator())
                .append(first.getName()).append(";").append(first.getSalary()).append(";").append(System.lineSeparator());
        Assert.assertThat(engine.generate(employee -> true), is(expected.toString()));
    }
}