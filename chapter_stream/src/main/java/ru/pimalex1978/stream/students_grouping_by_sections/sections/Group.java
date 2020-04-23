package ru.pimalex1978.stream.students_grouping_by_sections.sections;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * В этом задании нужно произвести группировку студентов по интересам.
 * У каждого студента есть список секций, которые он посещает.
 */

public class Group {

    public static void main(String[] args) {

        //Наши студенты
        Student ivanov = new Student("Ivanov", Set.of("Biology", "Aeromodelling", "Tennis")); //, "Aeromodelling"
        Student petrov = new Student("Petrov", Set.of("Tennis", "Aeromodelling", "Badminton")); //, "Aeromodelling"
        Student sidorov = new Student("Sidorov", Set.of("Badminton", "Robotics", "Biology")); //, "Robotics"
        Student nikolaev = new Student("Nikolaev", Set.of("Biology", "Fencing", "Philosophy")); //, "Fencing"
        Student orlova = new Student("Orlova", Set.of("Philosophy", "Tennis", "Robotics")); //, "Tennis"
        Student peterson = new Student("Peterson", Set.of("Philosophy", "Badminton")); //, "Badminton"
        Student proctor = new Student("Proctor", Set.of("Tennis", "Biology", "Fencing")); //, "Biology"

        //Наши студенты у которых только 1 кружок (1 секция)
        Student ivanov1 = new Student("Ivanov", Set.of("Biology"));
        Student petrov1 = new Student("Petrov", Set.of("Tennis"));
        Student sidorov1 = new Student("Sidorov", Set.of("Badminton"));
        Student nikolaev1 = new Student("Nikolaev", Set.of("Biology"));
        Student orlova1 = new Student("Orlova", Set.of("Philosophy"));
        Student peterson1 = new Student("Peterson", Set.of("Philosophy"));
        Student proctor1 = new Student("Proctor", Set.of("Tennis"));

        //Заполним список студентов
        List<Student> students = Arrays.asList(ivanov, petrov, sidorov, nikolaev, orlova, peterson, proctor);

        //Заполним список студентов у которых одна секция
        List<Student> studentsOneSection = Arrays.asList(ivanov1, petrov1, sidorov1, nikolaev1, orlova1, peterson1, proctor1);

        //Вывели список студентов в ASC
        //students.stream().sorted().forEach(System.out::println);

        System.out.println("**********************");

        //Получим сет секций из списка студентов
        System.out.println("##### Set секций #####");

        var collect = students.stream()
                .map(Student::getUnits)
                .flatMap(units -> units.stream())
                //.distinct()
                //.collect(Collectors.toList());
                .collect(Collectors.toSet());
        System.out.println(collect);

        System.out.println("**********************");

        //Получим сет секций из списка студентов с 1 секцией
        var set1 = studentsOneSection.stream()
                .map(Student::getUnits)
                .flatMap(units -> units.stream())
                //.distinct()
                //.collect(Collectors.toList());
                .collect(Collectors.toSet());
        System.out.println("===Set1 списк секций===");
        System.out.println(set1);
        System.out.println("===Set1 списк студентов при одной секции=======");
        Map<Set<String>, String> collect1 = studentsOneSection.stream()
                .collect(Collectors.toMap(
                        Student::getUnits,
                        Student::getName,
                        (s, a) -> s + ", " + a));
        System.out.println(collect1);

        System.out.println("**********************");

        System.out.println("##### Спсок секций #####");

        var listUnits = students.stream()
                .flatMap(student -> student.getUnits().stream())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(" Список Units : " + listUnits);
        System.out.println("**********************");

        System.out.println("##### Группировка студентов по секциям #####");
        /*Используя mapping мы можем сначала выполнить группировку на основе всего объекта Student,
        а затем сопоставить его с именами.*/
        /*Но это подходит, если в списке секций указана только по одной секциидля каждого студента.
         * При нескольких секция у одного студента, ЭТОТ КОД НЕ РАБОТАЕТ!!!
         * НУЖНО ИСПОЛЬЗОВАТЬ ПРОМЕЖУТОЧНЫЙ КЛАСС Holder, для хранения в нем названия секции и имени студента!!!
         * Это сделано в методе sections*/

        Map<String, List<Student>> groupBy = students.stream()
                .collect(Collectors.groupingBy(Student::getName));

        Map<String, Set<String>> mappingBy = students.stream().collect(
                Collectors.groupingBy(Student::getName, Collectors.mapping(Student::getName, Collectors.toSet())));


        System.out.println("##### method group by sections #####");
        Map<String, Set<String>> groupBySections = sections(students);
        groupBySections.entrySet().forEach(System.out::println);
        System.out.println("*****Красивый вывод*****");
        //Или так красиво выводим результат
        for (Map.Entry<String, Set<String>> itemGroup : groupBySections.entrySet()) {
            System.out.println("-= " + itemGroup.getKey() + " =-");
            for (String names : itemGroup.getValue()) {
                System.out.println(names);
            }
        }

        System.out.println("##### method group by sections, when we have only one section #####");
        Map<String, Set<String>> sectionsOneSection = sections(studentsOneSection);
        sectionsOneSection.entrySet().forEach(System.out::println);
    }

    /**
     * Метод, который группирует студентов по секциям.
     * Создали доп.класс Holder для хранения в нем названий секций и имен студентов.
     * Создаем стрим из Студентов, и преобразуем его в поток Holder, используя метод flatMap.
     * Вытаскиваем из Student название секций, преобразуем их в stream(),
     * далее с помощью distinct() убираем дубликаты, и упаковываем с помощью map() в Holder название секции в
     * String key и имя студента в String value. Имеем на выходе Stream<Holder>.
     * Далее весь поток нужно собрать в карту - для этого используйте метод collect.
     * Внутри метода используем метод groupingBy. Этот метод позволяет сделать
     * группировку по ключу. В нашем случае Holder::getKey.
     * Далее используя mapping(), мы можем сначала выполнить группировку на основе всего объекта Holder,
     * а затем сопоставить его с именами студентов. Ну и далее эти имена собираем в Сет.
     * <p>
     * Примечание:
     * (Как вариант, мы можем внутри самого метода groupingBy создать свой аккумулятор через
     * метод Collector.of. Это нужно сделать, так как метод groupingBy
     * собирает группы с типом входной коллекции.
     * Например, если тип List<User>, то groupingBy будет собирать элементы по User.
     * В нашем случае там нужны только имена студентов.)
     */
    public static Map<String, Set<String>> sections(List<Student> students) {
        Map<String, Set<String>> collect = students.stream()
                .flatMap(student -> student.getUnits().stream()
                        .map(b -> new Holder(b, student.getName()))) // собираем объект Holder с unit и name
                //.peek(System.out::println) //нужно лишь, чтобы видеть поток Holder
                .collect(       //собираем карту
                        Collectors.groupingBy(Holder::getKey, //определяем группировку - группируем по названию секции
                                Collectors.mapping((Holder::getValue), //собираем имена студентов в Сет.
                                        Collectors.toSet())));
        return collect;
    }

    //                                Collector.of(
//                                        HashSet::new, //поставщик, supplier()
//                                        (set, el) -> set.add(el),//аккумулятор accumulator(), накопление, как добавлять данные или через HashSet::add;
//                                        (left, right) -> {
//                                            left.addAll(right);
//                                            return left;
//                                        } //для агрегации, combiner(), сумматор
//                                )));


    /*Используя mapping мы можем сначала выполнить группировку на основе всего объекта Student, а затем сопоставить его с именами.*/
    /*Но это подходит, если в списке секций указана только по одной секциидля каждого студента.*/
    public static Map<Set<String>, Set<String>> groupingBySectionsSet(List<Student> students) {
        return students.stream()
                .collect(Collectors.groupingBy(
                        Student::getUnits,
                        Collectors.mapping(Student::getName, Collectors.toSet())));

    }
}
