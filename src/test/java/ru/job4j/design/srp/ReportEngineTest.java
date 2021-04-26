package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.Calendar;
import java.util.Comparator;

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
    public void whenNewGenerated() {
        MemStore store = new MemStore();
        Employee worker1 = new Employee("Ivan", 300);
        Employee worker2 = new Employee("Boris", 200);
        Employee worker3 = new Employee("Nikolay", 100);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportEngine(store);
        String expect = "<html><body><h1>Employees</h1><html><body><h2>Name Salary</h2>"
                + "<textarea cols=16 rows=10>"
                + "Ivan 300.0"
                + "Boris 200.0"
                + "Nikolay 100.0";
        Comparator<Employee> comp = Comparator.comparingDouble(Employee::getSalary).reversed();
        assertThat(engine.generateHTML(comp), is(expect));
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
        Report engine = new ReportEngine(store);
        String expect = "{\"Salary\":300,\"Name\":\"Ivan\"}"
                + "{\"Salary\":200,\"Name\":\"Boris\"}"
                + "{\"Salary\":100,\"Name\":\"Nikolay\"}";
        Comparator<Employee> comp = Comparator.comparingDouble(Employee::getSalary).reversed();
        String actual = engine.generateJSON(comp);
        assertThat(actual, is(expect));
    }

    @Test
    public void whenXMLGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Employee worker1 = new Employee("Ivan", 300);
        Employee worker2 = new Employee("Boris", 200);
        Employee worker3 = new Employee("Nikolay", 100);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportEngine(store);
        String expect = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<employee>\n"
                + "    <name>Ivan</name>\n"
                + "    <salary>300.0</salary>\n"
                + "</employee>\n"
                + "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<employee>\n"
                + "    <name>Boris</name>\n"
                + "    <salary>200.0</salary>\n"
                + "</employee>\n"
                + "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<employee>\n"
                + "    <name>Nikolay</name>\n"
                + "    <salary>100.0</salary>\n"
                + "</employee>\n";
        Comparator<Employee> comp = Comparator.comparingDouble(Employee::getSalary).reversed();
        String actual = engine.generateXML(comp);
        assertThat(actual, is(expect));
    }
}