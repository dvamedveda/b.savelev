package ru.job4j.srp;

import java.util.function.Predicate;

/**
 * Обычная реалиазация движка генерации отчетов.
 */
public class ReportEngine implements Engine {
    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

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