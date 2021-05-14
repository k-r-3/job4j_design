package ru.job4j.ood.dip.handbook;

import java.util.List;

public class Page {
    private List<Paragraph> paragraphs;
    private int pageNumber;

    public Page(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void addParagraph(Paragraph newParagraph) {
        paragraphs.add(newParagraph);
    }

    public Paragraph getParagraph(int id) {
        return paragraphs.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .get();
    }

    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public String formatOut() {
        StringBuilder sb = new StringBuilder();
        sb.append(pageNumber);
        for (int i = 0; i < paragraphs.size(); i++) {
            if (i % 2 == 0) {
                sb.append("       ");
            }
            sb.append(paragraphs.get(i));
        }
        return sb.toString();
    }
}
