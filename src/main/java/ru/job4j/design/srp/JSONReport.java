package ru.job4j.design.srp;

import org.json.JSONObject;

import java.util.function.Predicate;

public class JSONReport implements Report {
    private Store store;

    public JSONReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder sb = new StringBuilder();
        JSONObject jsonObject;
        for (Employee employee : store.findBy(filter)) {
            jsonObject = new JSONObject();
            jsonObject.put("Name", employee.getName());
            jsonObject.put("Salary", employee.getSalary());
            sb.append(jsonObject.toString());
        }
        return sb.toString();
    }
}
