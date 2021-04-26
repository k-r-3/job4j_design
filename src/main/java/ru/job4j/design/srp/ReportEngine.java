package ru.job4j.design.srp;

import java.io.*;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

public class ReportEngine implements Report {
    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;\r\n");
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    @Override
    public String generateHTML(Predicate<Employee> filter, Path path) {
        StringBuilder sb = new StringBuilder();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path.toString()))) {
            bw.write("<html><body><h1>Emploeeys</h1>");
            bw.write("<html><body><h2>Name Salary</h2>");
            bw.write("<textarea cols=16 rows=10>");
            for (Employee employee : store.findSort(Comparator
                    .comparing(Employee::getSalary).reversed())) {
                bw.write(employee.getName());
                bw.write(employee.getSalary() + "");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toString()))) {
            reader.lines()
                    .forEach(sb::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
