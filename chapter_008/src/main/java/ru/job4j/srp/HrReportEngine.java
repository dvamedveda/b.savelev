package ru.job4j.srp;

import java.util.function.Predicate;

/**
 * Реализация движка генерации отчета для кадровиков.
 * Кадровики потребовали выводить изначальный список сотрудников
 * в порядке убывания зарплат и скрыть поля найма и увольнения.
 */
public class HrReportEngine implements Engine {
    private Store store;

    public HrReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;" + System.lineSeparator());
        for (Employee employee : store.findBy(filter, Sort.bySalaryDesc())) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";");
            text.append(System.lineSeparator());
        }
        return text.toString();
    }
}