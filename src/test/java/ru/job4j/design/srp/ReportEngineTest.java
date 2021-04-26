package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenNewGenerated() throws IOException {
        Path root = folder.newFolder().toPath();
        Path file = Files.createTempFile(root, "temp", ".html");
        MemStore store = new MemStore();
        Employee worker1 = new Employee("Ivan", 300);
        Employee worker2 = new Employee("Boris", 200);
        Employee worker3 = new Employee("Nikolay", 100);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportEngine(store);
        String expect = "<html><body><h1>Emploeeys</h1><html><body><h2>Name Salary"
                + "</h2><textarea cols=16 rows=10>"
                + "Ivan300.0"
                + "Boris200.0"
                + "Nikolay100.0";
        assertThat(engine.generateHTML(em -> true, file), is(expect));

    }
}