package ru.pimalex1978.dom.example3;

public class Student extends Human {
    private String course, specialization;

    public Student(String name, String course, String specialization) {
        super(name);
        this.course = course;
        this.specialization = specialization;
    }

    public String getCourse() {
        return course;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public String toString() {
        return "Голодный Student "
                + getName() + " "
                + course + "-го курса, обучающийся по специальности "
                + specialization;
    }
}
