package ru.job4j.design.srp;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@XmlRootElement
public class Employees implements Store {

    @XmlElement
    private List<Employee> files = new ArrayList<>();

    public Employees(List<Employee> files) {
        this.files = files;
    }

    public Employees() { }

    public List<Employee> getFiles() {
        return files;
    }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return null;
    }
}
