package ru.job4j.design.srp;

import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReportHR implements Report {
    private Store store;

    public ReportHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Comparator<Employee> comp = Comparator.comparingDouble(Employee::getSalary).reversed();
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;");
        text.append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)
                .stream()
                .sorted(comp)
                .collect(Collectors.toList())
        ) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
