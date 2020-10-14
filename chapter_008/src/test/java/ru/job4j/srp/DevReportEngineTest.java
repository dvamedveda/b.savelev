package ru.job4j.srp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;

/**
 * Тесты класса DevReportEngine.
 */
public class DevReportEngineTest {

    /**
     * Проверка правильной генерации отчетов
     * в реализации движка генерации для программистов.
     */
    @Test
    public void whenGeneratedDevThenCorrectReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee first = new Employee("Ivan", now, now, 100_000);
        Employee second = new Employee("Vasiliy", now, now, 120_000);
        Employee third = new Employee("Nikolai", now, now, 111_000);
        store.add(first);
        store.add(second);
        store.add(third);
        Engine engine = new DevReportEngine(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(first.getName()).append(";").append(first.getHired()).append(";")
                .append(first.getFired()).append(";").append(first.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(second.getName()).append(";").append(second.getHired()).append(";")
                .append(second.getFired()).append(";").append(second.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(third.getName()).append(";").append(third.getHired()).append(";")
                .append(third.getFired()).append(";").append(third.getSalary()).append(";")
                .append(System.lineSeparator());
        Assert.assertThat(engine.generate(employee -> true), is(expected.toString()));
    }
}
