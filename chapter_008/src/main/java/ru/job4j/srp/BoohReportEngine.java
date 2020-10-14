package ru.job4j.srp;

import java.util.function.Predicate;

/**
 * Реализация движка генерации отчета для бухгалтеров.
 * Бухгалтеры попросили изменить вид выводимой зарплаты для сотрудников в изначальном отчете.
 */
public class BoohReportEngine implements Engine {
    private Store store;

    public BoohReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;" + System.lineSeparator());
        for (Employee employee : store.findBy(filter, Sort.withoutSort())) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(Formatter.toThousands(employee.getSalary())).append("k").append(";");
            text.append(System.lineSeparator());
        }
        return text.toString();
    }
}