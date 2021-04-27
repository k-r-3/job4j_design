package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportCoders implements Report {
    private Store store;

    public ReportCoders(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body><h1>Employees</h1>");
        sb.append("<html><body><h2>Name Salary</h2>");
        sb.append("<textarea cols=30 rows=10>");
        for (Employee employee : store.findBy(filter)) {
            sb.append(employee.getName())
                    .append(" ").append(employee.getSalary())
                    .append(" ");
        }
        return sb.toString();
    }
}
