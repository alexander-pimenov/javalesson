package ru.pimalex1978.stream.students_grouping_by_sections.sectionswhithenum;

import java.util.Objects;
import java.util.Set;

/**
 * В этом задании нужно произвести группировку студентов по интересам.
 * У каждого студента есть список секций, которые он посещает.
 */

public class Student implements Comparable<Student>{

    private String name;
    private Set<Section> units;

    public Student(String name, Set<Section> units) {
        this.name = name;
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public Set<Section> getUnits() {
        return units;
    }

    @Override
    public String toString() {
        return "Student{"
                + "name='" + name + '\''
                + ", units=" + units + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) &&
                Objects.equals(units, student.units);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }

    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.name);
    }
}
