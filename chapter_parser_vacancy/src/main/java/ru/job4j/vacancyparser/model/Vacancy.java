package ru.job4j.vacancyparser.model;

import java.util.Objects;

public class Vacancy {
    private String name;
    private String text;
    private String link;

    public Vacancy(String name, String text, String link) {
        this.name = name;
        this.text = text;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(name, vacancy.name)
                && Objects.equals(text, vacancy.text)
                && Objects.equals(link, vacancy.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, text, link);
    }

    @Override
    public String toString() {
        return "Vacancy{"
                + "name='" + name + '\''
                + ", text='" + text + '\''
                + ", link='" + link + '\''
                + '}';
    }
}
