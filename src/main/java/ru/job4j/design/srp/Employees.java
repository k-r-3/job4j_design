package ru.job4j.design.srp;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
public class Employees {

    @XmlElement
    private List<Employee> files;

    public Employees(List<Employee> files) {
        this.files = files;
    }

    public Employees() { }

    public List<Employee> getFiles() {
        return files;
    }
}
