package ru.job4j.design.srp;

import javax.xml.bind.JAXBException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter);

    String generateHTML(Comparator<Employee> comp);

    String generateJSON(Comparator<Employee> comp);

    String generateXML(Comparator<Employee> comp) throws JAXBException;
}
