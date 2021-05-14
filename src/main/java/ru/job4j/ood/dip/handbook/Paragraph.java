package ru.job4j.ood.dip.handbook;

public class Paragraph {
    private String name;
    private String description;
    private int id;
    private int count;

    public Paragraph(String name, String description) {
        this.name = name;
        this.description = description;
        this.id = count++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
