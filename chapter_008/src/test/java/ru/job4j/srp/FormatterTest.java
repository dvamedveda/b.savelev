package ru.job4j.srp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;

/**
 * Тесты класса ru.job4j.Formatter.
 */
public class FormatterTest {

    /**
     * Здесь проверяется форматирование списка сотрудников в html.
     */
    @Test
    public void whenFormatWithHtmlThenCorrect() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee first = new Employee("Ivan", now, now, 100_000);
        Employee second = new Employee("Vasiliy", now, now, 120_000);
        store.add(first);
        store.add(second);
        Engine engine = new DevReportEngine(store);
        String result = Formatter.format(engine.generate(employee -> true), Format.HTML);
        String line = System.lineSeparator();
        StringBuilder expected = new StringBuilder()
                .append("<html>").append(line)
                .append("<table>").append(line)
                .append("<tr><th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th></tr>")
                .append("<tr>")
                .append("<td>").append(first.getName()).append("</td>")
                .append("<td>").append(first.getHired()).append("</td>")
                .append("<td>").append(first.getFired()).append("</td>")
                .append("<td>").append(first.getSalary()).append("</td>")
                .append("</tr>")
                .append("<tr>")
                .append("<td>").append(second.getName()).append("</td>")
                .append("<td>").append(second.getHired()).append("</td>")
                .append("<td>").append(second.getFired()).append("</td>")
                .append("<td>").append(second.getSalary()).append("</td>")
                .append("</tr>")
                .append("</table>").append(line)
                .append("</html>").append(line);
        Assert.assertThat(result, is(expected.toString()));
    }
}