package ru.job4j.design.srp;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class JSONReport implements Report {
    private Store store;

    public JSONReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder sb = new StringBuilder();
        List<JSONObject> jsons = new ArrayList<>();
        JSONObject jsonObject;
        for (Employee employee : store.findBy(filter)) {
            jsonObject = new JSONObject();
            jsonObject.put("Name", employee.getName());
            jsonObject.put("Salary", employee.getSalary());
            jsons.add(jsonObject);
        }
        return jsons.toString();
    }
}
