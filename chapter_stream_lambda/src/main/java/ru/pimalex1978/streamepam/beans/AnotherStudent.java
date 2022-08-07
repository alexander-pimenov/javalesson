package ru.pimalex1978.streamepam.beans;

public class AnotherStudent {
    public static enum Grade {
        A, B, C, D, F
    }

    private String name;
    private int age;
    private Grade grade;

    public AnotherStudent(String name, int age, Grade grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String toString() {
        return name + " (" + age + "):" + grade;
    }

}
