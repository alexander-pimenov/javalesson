package ru.pimalex1978.dom.example3;

public class Professor extends Human {

    private String experience, discipline;

    public Professor(String name, String experience, String discipline) {
        super(name);
        this.experience = experience;
        this.discipline = discipline;
    }

    public String getExperience() {
        return experience;
    }

    public String getDiscipline() {
        return discipline;
    }

    @Override
    public String toString() {
        return "Professor "
                + getName()
                + ", обладающий опытом: "
                + experience + ", выкладывает дисциплину "
                + discipline;
    }
}
