package ru.pimalex1978.function_interface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * В этом пример видно, что запись Consumer<T> возможна различными
 * способами.
 * В Java 8 у коллекций появился метод forEach(), который позволяет
 * для каждого элемента коллекции выполнить какое-нибудь действие.
 * И вот для передачи действия в метод forEach() как раз и
 * используется функциональный интерфейс Consumer<T>.
 */

public class ConsumerDemo2 {

    public static void main(String[] args) {

        Consumer<Student> c1 = (stu) -> System.out.println(stu);
        //forEach принимает в параметр consumer
//        getListOfStudent().forEach(c1);
        getListOfStudent().forEach((stu) -> System.out.println(stu));

        /*Можно и так получить список студентов и потом вывести на экран*/

        Student student1 = new Student("John", "Mick");
        Student student2 = new Student("Jonatha", "Phill");
        Student student3 = new Student("Ben", "Gun");
        Student student4 = new Student("Nick", "Cross");

        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(student1);
        studentList1.add(student2);
        studentList1.add(student3);
        studentList1.add(student4);

        studentList1.forEach(new Consumer<Student>() {
            @Override
            public void accept(Student st) {
                System.out.println(st);
            }
        });

        /*А можно и так получить список студентов и потом вывести на экран*/
        List<Student> studentList2 = new ArrayList<>();
        Collections.addAll(studentList2, student1, student2, student3, student4);
        studentList2.forEach(st -> System.out.println(st));


    }

    static List<Student> getListOfStudent() {
        return Arrays.asList(
                new Student("John", "Mick"),
                new Student("Jonatha", "Phill"),
                new Student("Ben", "Gun"),
                new Student("Nick", "Cross"));
    }
}

class Student {
    private String firstName, lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Student["
                + "firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ']';
    }
}
