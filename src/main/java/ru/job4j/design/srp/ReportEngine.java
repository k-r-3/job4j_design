package ru.job4j.design.srp;

import java.io.StringWriter;
import java.util.Comparator;
import java.util.function.Predicate;

import org.json.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class ReportEngine implements Report {
    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;\r\n");
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

//    @Override
//    public String generateHTML(Comparator<Employee> comp) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("<html><body><h1>Employees</h1>");
//        sb.append("<html><body><h2>Name Salary</h2>");
//        sb.append("<textarea cols=16 rows=10>");
//        for (Employee employee : store.findSort(comp)) {
//            sb.append(employee.getName())
//                    .append(" ").append(employee.getSalary());
//        }
//        return sb.toString();
//    }
//
//    @Override
//    public String generateJSON(Comparator<Employee> comp) {
//        StringBuilder sb = new StringBuilder();
//        JSONObject jsonObject;
//        for (Employee employee : store.findSort(comp)) {
//            jsonObject = new JSONObject();
//            jsonObject.put("Name", employee.getName());
//            jsonObject.put("Salary", employee.getSalary());
//            sb.append(jsonObject.toString());
//        }
//        return sb.toString();
//    }
//
//    @Override
//    public String generateXML(Comparator<Employee> comp) throws JAXBException {
//        StringWriter sw = new StringWriter();
//        JAXBContext context = JAXBContext.newInstance(Employee.class);
//        Marshaller marshaller = context.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        for (Employee employee : store.findSort(comp)) {
//            marshaller.marshal(employee, sw);
//        }
//        return sw.toString();
//    }
}
