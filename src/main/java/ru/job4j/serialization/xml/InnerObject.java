package ru.job4j.serialization.xml;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAttribute;

@XmlElement
public class InnerObject {
    @XmlAttribute
    private int someNumber;

    public InnerObject() { }

    public InnerObject(int someNumber) {
        this.someNumber = someNumber;
    }

    @Override
    public String toString() {
        return "InnerObject{"
                + "someNumber="
                + someNumber + '}';
    }
}
