package ru.pimalex1978.streamepam.beans;

public class Employee {
    private int age;
    private String name;
    private String skill;
    private int level;
    private Department department;

    public Employee(int age, String name, String skill, int level, Department department) {
        this.age = age;
        this.name = name;
        this.skill = skill;
        this.level = level;
        this.department = department;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", skill='" + skill + '\'' +
                ", level=" + level +
                ", department=" + department +
                '}';
    }
}
