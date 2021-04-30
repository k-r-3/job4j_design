package ru.job4j.design.srp;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@XmlRootElement
public class Employees implements Store {

    @XmlElement
    private List<Employee> files = new ArrayList<>();

    public Employees(List<Employee> files) {
        this.files = files;
    }

    public Employees() { }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return files.stream().
                filter(filter).
                collect(Collectors.toList());
    }
}
