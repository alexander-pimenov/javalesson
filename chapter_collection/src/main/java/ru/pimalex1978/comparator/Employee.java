package ru.pimalex1978.comparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Руководство по Java 8 Comparator.comparing ()
 * https://www.baeldung.com/java-8-comparator-comparing
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Comparable<Employee> {
    String name;
    int age;
    double salary;
    long mobile;

    @Override
    public int compareTo(Employee o) {
        return name.compareTo(o.getName());
    }
}
