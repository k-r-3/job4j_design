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
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Employee", store.findBy(filter));
        return jsonObject.toString();
    }
}
