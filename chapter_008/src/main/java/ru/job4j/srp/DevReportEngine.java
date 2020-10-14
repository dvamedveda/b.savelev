package ru.job4j.srp;

import java.util.function.Predicate;

/**
 * Реализация движка генерации отчета для программистов.
 * Программисты потребовали выводить изначальный отчет в виде html.
 */
public class DevReportEngine implements Engine {
    private Store store;

    public DevReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;" + System.lineSeparator());
        for (Employee employee : store.findBy(filter, Sort.withoutSort())) {
            text.append(employee.getName()).append(";").append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";").append(employee.getSalary()).append(";");
            text.append(System.lineSeparator());
        }
        return text.toString();
    }
}