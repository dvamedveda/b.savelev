package ru.job4j.srp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;

/**
 * Тесты класса BoohReportEngine.
 */
public class BoohReportEngineTest {

    /**
     * Проверка правильной генерации отчетов
     * в реализации движка генерации для бухгалтеров.
     */
    @Test
    public void whenGeneratedBoohThenCorrectReport() {
        String line = System.lineSeparator();
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee first = new Employee("Ivan", now, now, 100_000);
        Employee second = new Employee("Vasiliy", now, now, 120_000);
        Employee third = new Employee("Nikolai", now, now, 111_000);
        store.add(first);
        store.add(second);
        store.add(third);
        Engine engine = new BoohReportEngine(store);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;").append(line)
                .append(first.getName()).append(";").append(first.getHired()).append(";")
                .append(first.getFired()).append(";").append("100.0k").append(";").append(line)
                .append(second.getName()).append(";").append(second.getHired()).append(";")
                .append(second.getFired()).append(";").append("120.0k").append(";").append(line)
                .append(third.getName()).append(";").append(third.getHired()).append(";")
                .append(third.getFired()).append(";").append("111.0k").append(";").append(line);
        Assert.assertThat(engine.generate(employee -> true), is(expected.toString()));
    }
}
