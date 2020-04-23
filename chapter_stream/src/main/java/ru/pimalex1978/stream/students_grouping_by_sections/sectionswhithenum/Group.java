package ru.pimalex1978.stream.students_grouping_by_sections.sectionswhithenum;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Group {
    public static void main(String[] args) {

//        String[] unitsArr = {"Biology", "Robotics", "Aeromodelling", "Psychology",
//                "Philosophy", "Tennis", "Fencing", "Badminton"};
//        Set<String> units = new HashSet<>(Arrays.asList(unitsArr));

//        new Student("Ivanov", units);

        Student ivanov = new Student("Ivanov", Set.of(Section.BIOLOGY, Section.AEROMODELLING));
        Student petrov = new Student("Petrov", Set.of(Section.TENNIS, Section.ROBOTICS, Section.PHILOSOPHY));
        Student sidorov = new Student("Sidorov", Set.of(Section.BADMINTON, Section.FENCING, Section.PSYCHOLOGY));
        Student nikolaev = new Student("Nikolaev", Set.of(Section.PSYCHOLOGY, Section.BADMINTON, Section.BIOLOGY));
        Student orlova = new Student("Orlova", Set.of(Section.PHILOSOPHY, Section.BIOLOGY, Section.TENNIS));
        Student peterson = new Student("Peterson", Set.of(Section.FENCING, Section.PSYCHOLOGY, Section.ROBOTICS));

        List<Student> students = Arrays.asList(ivanov, petrov, sidorov, nikolaev, orlova, peterson);
        students.stream().sorted().forEach(System.out::println);

        Map<Set<Section>, Set<Student>> map = sections(students);
        System.out.println(map);

    }

    public static Map<Set<Section>, Set<Student>> sections(List<Student> students) {
        Map<Set<Section>, Set<Student>> collect = students.stream()
                .collect(Collectors.groupingBy(Student::getUnits, Collectors.toSet()));

        return collect;
    }

}
