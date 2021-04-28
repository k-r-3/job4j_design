package ru.job4j.design.srp;

import org.json.JSONObject;

import java.util.function.Predicate;

public class JSONReport implements Report {
    private Employees employees;

    public JSONReport(Employees employees) {
        this.employees = employees;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder sb = new StringBuilder();
        JSONObject jsonObject = new JSONObject();
            jsonObject.put("Employee", employees.getFiles());
            sb.append(jsonObject.toString());
        return sb.toString();
    }
}
