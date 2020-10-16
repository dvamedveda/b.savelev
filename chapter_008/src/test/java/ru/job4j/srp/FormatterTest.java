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

    /**
     * Здесь проверяется форматирование отчета со всеми полями в json.
     */
    @Test
    public void whenDevFormatWithJsonThenCorrect() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee first = new Employee("Ivan", now, now, 100_000);
        Employee second = new Employee("Vasiliy", now, now, 120_000);
        store.add(first);
        store.add(second);
        Engine engine = new DevReportEngine(store);
        String result = Formatter.format(engine.generate(employee -> true), Format.JSON);
        StringBuilder expected = new StringBuilder()
                .append("{")
                .append("\"").append(first.getName()).append("\": {")
                .append("\"Hired\": \"").append(first.getHired()).append("\",")
                .append("\"Fired\": \"").append(first.getFired()).append("\",")
                .append("\"Salary\": \"").append(first.getSalary()).append("\"")
                .append("},")
                .append("\"").append(second.getName()).append("\": {")
                .append("\"Hired\": \"").append(second.getHired()).append("\",")
                .append("\"Fired\": \"").append(second.getFired()).append("\",")
                .append("\"Salary\": \"").append(second.getSalary()).append("\"")
                .append("}")
                .append("}");
        Assert.assertThat(result, is(expected.toString()));
    }

    /**
     * Здесь проверяется форматирование отчета не со всеми полями в json.
     */
    @Test
    public void whenHrFormatWithJsonThenCorrect() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee first = new Employee("Ivan", now, now, 100_000);
        Employee second = new Employee("Vasiliy", now, now, 120_000);
        Employee third = new Employee("Leonid", now, now, 111_000);
        store.add(first);
        store.add(second);
        store.add(third);
        Engine engine = new HrReportEngine(store);
        String result = Formatter.format(engine.generate(employee -> true), Format.JSON);
        StringBuilder expected = new StringBuilder()
                .append("{")
                .append("\"").append(second.getName()).append("\": {")
                .append("\"Salary\": \"").append(second.getSalary()).append("\"")
                .append("},")
                .append("\"").append(third.getName()).append("\": {")
                .append("\"Salary\": \"").append(third.getSalary()).append("\"")
                .append("},")
                .append("\"").append(first.getName()).append("\": {")
                .append("\"Salary\": \"").append(first.getSalary()).append("\"")
                .append("}")
                .append("}");
        Assert.assertThat(result, is(expected.toString()));
    }

    /**
     * Здесь проверяется форматирование отчета со всеми полями в xml.
     */
    @Test
    public void whenDevFormatWithXmlThenCorrect() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee first = new Employee("Ivan", now, now, 100_000);
        Employee second = new Employee("Vasiliy", now, now, 120_000);
        store.add(first);
        store.add(second);
        Engine engine = new DevReportEngine(store);
        String result = Formatter.format(engine.generate(employee -> true), Format.XML);
        StringBuilder expected = new StringBuilder()
                .append("<employees>")
                .append("<employee>")
                .append("<name>").append(first.getName()).append("</name>")
                .append("<hired>").append(first.getHired()).append("</hired>")
                .append("<fired>").append(first.getFired()).append("</fired>")
                .append("<salary>").append(first.getSalary()).append("</salary>")
                .append("</employee><employee>")
                .append("<name>").append(second.getName()).append("</name>")
                .append("<hired>").append(second.getHired()).append("</hired>")
                .append("<fired>").append(second.getFired()).append("</fired>")
                .append("<salary>").append(second.getSalary()).append("</salary>")
                .append("</employee>")
                .append("</employees>");
        Assert.assertThat(result, is(expected.toString()));
    }

    /**
     * Здесь проверяется форматирование отчета не всеми полями в xml.
     */
    @Test
    public void whenHrFormatWithXMLThenCorrect() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee first = new Employee("Ivan", now, now, 100_000);
        Employee second = new Employee("Vasiliy", now, now, 120_000);
        Employee third = new Employee("Leonid", now, now, 111_000);
        store.add(first);
        store.add(second);
        store.add(third);
        Engine engine = new HrReportEngine(store);
        String result = Formatter.format(engine.generate(employee -> true), Format.XML);
        StringBuilder expected = new StringBuilder()
                .append("<employees>")
                .append("<employee>")
                .append("<name>").append(second.getName()).append("</name>")
                .append("<salary>").append(second.getSalary()).append("</salary>")
                .append("</employee><employee>")
                .append("<name>").append(third.getName()).append("</name>")
                .append("<salary>").append(third.getSalary()).append("</salary>")
                .append("</employee><employee>")
                .append("<name>").append(first.getName()).append("</name>")
                .append("<salary>").append(first.getSalary()).append("</salary>")
                .append("</employee>")
                .append("</employees>");
        Assert.assertThat(result, is(expected.toString()));
    }
}