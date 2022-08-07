package ru.pimalex1978.stream;

import java.util.*;
import java.util.stream.Collectors;

public class StudentProcessing {

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Alex", Speciality.Physics, 1),
                new Student("Rika", Speciality.Biology, 4),
                new Student("Julia", Speciality.Biology, 2),
                new Student("Steve", Speciality.History, 4),
                new Student("Mike", Speciality.Finance, 1),
                new Student("Hinata", Speciality.Biology, 2),
                new Student("Richard", Speciality.History, 1),
                new Student("Kate", Speciality.Psychology, 2),
                new Student("Sergey", Speciality.ComputerScience, 4),
                new Student("Maximilian", Speciality.ComputerScience, 3),
                new Student("Tim", Speciality.ComputerScience, 5),
                new Student("Ann", Speciality.Psychology, 1),
                new Student("Ben", Speciality.Psychology, 1),
                new Student("Nike", Speciality.Psychology, 2)
        );

        //Если в списке студентов есть null значения, то фильтруем их:
        List<Student> collectWithoutNull = students.stream()
                .filter(elt -> elt != null)//или так .filter(Objects::notNull)
                .collect(Collectors.toList());
        System.out.println(collectWithoutNull);
        System.out.println("==========");


        /*Задачи:*/
        //Вывести в алфавитном порядке список специальностей, на которых учатся перечисленные в списке студенты.
        students.stream()
                .map(Student::getSpeciality)
                .distinct()
                .sorted(Comparator.comparing(Enum::name))
                .forEach(System.out::println);
        //Biology
        //ComputerScience
        //Finance
        //History
        //Physics
        //Psychology
        System.out.println("==========");

        //Вывести количество учащихся на каждой из специальностей. Группируем специальности и считаем их кол-во.
        students.stream()
                .collect(Collectors.groupingBy(
                        Student::getSpeciality, Collectors.counting()))
                .forEach((s, count) -> System.out.println(s + ": " + count));
        //Finance: 1
        //Biology: 3
        //Psychology: 4
        //ComputerScience: 3
        //History: 2
        //Physics: 1
        System.out.println("==========");

        //Сгруппировать студентов по специальностям, сохраняя алфавитный порядок
        //специальности, а затем сгруппировать по курсу (по году).
        Map<Speciality, Map<Integer, List<Student>>> result = students.stream()
                .sorted(Comparator.comparing(Student::getSpeciality,
                        Comparator.comparing(Enum::name))
                        .thenComparing(Student::getYear))
                .collect(Collectors.groupingBy(
                        Student::getSpeciality,
                        LinkedHashMap::new,
                        Collectors.groupingBy(Student::getYear)
                ));
        //Теперь красиво все выводим.
        result.forEach((s, map) -> {
            System.out.println("-= " + s + " =-");
            map.forEach((year, list) -> System.out.format("%d: %s%n", year,
                    list.stream()
                            .map(Student::getName)
                            .sorted()
                            .collect(Collectors.joining(", "))));
            System.out.println();
        });
        //-= Biology =-
        //2: Hinata, Julia
        //4: Rika
        //
        //-= ComputerScience =-
        //3: Maximilian
        //4: Sergey
        //5: Tim
        //
        //-= Finance =-
        //1: Mike
        //
        //-= History =-
        //1: Richard
        //4: Steve
        //
        //-= Physics =-
        //1: Alex
        //
        //-= Psychology =-
        //1: Ann, Ben
        //2: Kate, Nike
        System.out.println("==========");

        //Проверить, есть ли третьекурсники среди учащихся всех специальностей кроме физики и CS.
        boolean b = students.stream()
                .filter(s -> !EnumSet.of(Speciality.ComputerScience,
                        Speciality.Physics)
                        .contains(s.getSpeciality()))
                .anyMatch(s -> s.getYear() == 3);
        System.out.println(b);
        //false
    }
}
