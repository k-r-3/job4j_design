package ru.job4j.serialization.xml;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@XmlRootElement(name = "MainClass")
@XmlAccessorType(XmlAccessType.FIELD)
public class MainClass {
    public static final Logger LOG = LoggerFactory.getLogger(MainClass.class.getName());

    @XmlAttribute
    private boolean tumbler;
    @XmlAttribute
    private int amount;
    @XmlAttribute
    private String name;
    private InnerObject in;
    @XmlElementWrapper(name = "types")
    @XmlElement(name = "type")
    private String[] array;

    public MainClass() {
    }

    public MainClass(boolean tumbler, int amount, String name, InnerObject in, String... array) {
        this.tumbler = tumbler;
        this.amount = amount;
        this.name = name;
        this.in = in;
        this.array = array;
    }

    @Override
    public String toString() {
        return "MainClass{"
                + "tumbler=" + tumbler
                + ",\n amount=" + amount
                + ",\n name='" + name + '\''
                + ",\n in=" + in
                + ",\n array=" + Arrays.toString(array)
                + '}';
    }

    public static void main(String[] args) {
        final MainClass mainClass = new MainClass(false, 123,
                "main class", new InnerObject(100),
                "First word", "Second word");
        try {
            JAXBContext context = JAXBContext.newInstance(MainClass.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            String xml = "";
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(mainClass, writer);
                xml = writer.getBuffer().toString();
                System.out.println(xml);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                try (StringReader reader = new StringReader(xml)) {
                    MainClass deserial = (MainClass) unmarshaller.unmarshal(reader);
                    System.out.println(deserial);
                }
            } catch (IOException e) {
                LOG.error("write-read section exception", e);
            }
        } catch (JAXBException e) {
            LOG.error("JAXB exception", e);
        }
    }
}
