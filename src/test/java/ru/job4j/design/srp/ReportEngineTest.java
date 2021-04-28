package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

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

    @Test
    public void whenCodersReportGenerated() {
        MemStore store = new MemStore();
        Employee worker1 = new Employee("Ivan", 300);
        Employee worker2 = new Employee("Boris", 200);
        Employee worker3 = new Employee("Nikolay", 100);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report reportCoders = new ReportCoders(store);
        String expect = "<html><body><h1>Employees</h1><html><body><h2>Name Salary</h2>"
                + "<textarea cols=30 rows=10>"
                + "Ivan 300.0 "
                + "Boris 200.0 "
                + "Nikolay 100.0 ";
        assertThat(reportCoders.generate(em -> true), is(expect));
    }

    @Test
    public void whenHRReportGenerated() {
        StringBuilder expect = new StringBuilder();
        MemStore store = new MemStore();
        Employee worker3 = new Employee("Nikolay", 100);
        Employee worker2 = new Employee("Boris", 200);
        Employee worker1 = new Employee("Ivan", 300);
        store.add(worker3);
        store.add(worker2);
        store.add(worker1);
        Report reportHR = new ReportHR(store);
        expect.append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(worker1.getSalary())
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(worker2.getSalary())
                .append(System.lineSeparator())
                .append(worker3.getName()).append(" ")
                .append(worker3.getSalary())
                .append(System.lineSeparator());
        assertThat(reportHR.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenCountingReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100.50);
        store.add(worker);
        Report report = new ReportCounting(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Integer Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append("100").append(";")
                .append(System.lineSeparator());
        assertThat(report.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenJSONGenerated() {
        MemStore store = new MemStore();
        Employee worker1 = new Employee("Ivan", 300);
        Employee worker2 = new Employee("Boris", 200);
        Employee worker3 = new Employee("Nikolay", 100);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Employees employees = new Employees(store.findBy(em -> true));
        Report report = new JSONReport(employees);
        String expect = "{\"Employee\":[{\"name\":\"Ivan\",\"salary\":300},"
                +"{\"name\":\"Boris\",\"salary\":200},"
                +"{\"name\":\"Nikolay\",\"salary\":100}]}";
        String actual = report.generate(em -> true);
        assertThat(actual, is(expect));
    }

    @Test
    public void whenXMLGenerated() {
        MemStore store = new MemStore();
        Employee worker1 = new Employee("Ivan", 300);
        Employee worker2 = new Employee("Boris", 200);
        Employee worker3 = new Employee("Nikolay", 100);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Employees employees = new Employees(store.findBy(em -> true));
        Report report = new XMLReport(employees);
        String expect = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<employees>\n"
                + "    <files>\n"
                + "        <name>Ivan</name>\n"
                + "        <salary>300.0</salary>\n"
                + "    </files>\n"
                + "    <files>\n"
                + "        <name>Boris</name>\n"
                + "        <salary>200.0</salary>\n"
                + "    </files>\n"
                + "    <files>\n"
                + "        <name>Nikolay</name>\n"
                + "        <salary>100.0</salary>\n"
                + "    </files>\n"
                + "</employees>\n";
        String actual = report.generate(em -> true);
        assertThat(actual, is(expect));
    }
}